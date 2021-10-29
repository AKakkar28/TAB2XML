package converter.note;

import converter.Instrument;
import models.measure.note.Grace;
import models.measure.note.notations.Notations;
import models.measure.note.notations.Slide;
import models.measure.note.notations.Slur;
import models.measure.note.notations.technical.*;
import utility.DoubleDigitStyle;
import utility.Settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class to extract notes from single notes or combinations of notes
 * e.g. 6h8p6
 */
public class NoteFactory {
	private int stringNumber;
    private String origin, lineName;
    private int distanceFromMeasureStart, position;
    HashMap<String, String> patternPackage;
    Instrument instrument;
    public NoteFactory(int stringNumber, String origin, int position, Instrument instrument, String lineName, int distanceFromMeasureStart) {
        this.origin = origin;
        this.lineName = lineName;
        this.instrument = instrument;
        this.distanceFromMeasureStart = distanceFromMeasureStart;
        this.position = position;
        this.patternPackage = getPatternPackage(origin);
        this.stringNumber = stringNumber;
    }

    public static String FRET = getFret();
    public static String GRACE = getGracePattern();
    public static String HARMONIC = getHarmonicPattern();

    private static String getHarmonicPattern() {
        return "\\["+ FRET +"\\]";
    }
    private static String getFret() {
        return "((?<=^|[^0-9])[0-9]{1,2}(?=$|[^0-9]))";
    }
    private static String getGracePattern() {
        return "(g"+ FRET +"([hps]"+ FRET +")+)";
    }

    public static final String GUITAR_NOTE_PATTERN = getGuitarNotePattern();
    public static final String GUITAR_NOTE_GROUP_PATTERN = getGuitarNoteGroupPattern();
    public static final String GUITAR_NOTE_CONNECTOR = "[hpbsHPBS\\/\\\\]";

    public static final String DRUM_NOTE_PATTERN = "[xXoOdDfF]";
    public static final String DRUM_NOTE_GROUP_PATTERN = getDrumNoteGroupPattern();
    public static final String DRUM_NOTE_CONNECTOR = "$a";//there are no connectors, so this is a regex that never matches anything. an a after the end of the string

    private static String getDrumNoteGroupPattern() {
        return DRUM_NOTE_PATTERN +"+";
    }

    private static String getGuitarNoteGroupPattern() {
        return "("+GUITAR_NOTE_PATTERN+"(-*"+GUITAR_NOTE_CONNECTOR+GUITAR_NOTE_PATTERN+")*)";
    }
    public static String getGuitarNotePattern() {
        return "("+GRACE+"|"+HARMONIC+"|"+ FRET +")";
    }

    public List<Note> getNotes() {
        List<Note> noteList = new ArrayList<>();
        if (patternPackage==null) {
            noteList.add(new InvalidNote(stringNumber, origin, position, lineName, distanceFromMeasureStart));
            return noteList;
        }

        Matcher noteGroupMatcher = Pattern.compile(patternPackage.get("note-group-pattern")).matcher(origin);
        if (!noteGroupMatcher.find()) {
            noteList.add(new InvalidNote(stringNumber, origin, position, lineName, distanceFromMeasureStart));
            return noteList;
        }
        
        int[] groupIdx = {noteGroupMatcher.start(), noteGroupMatcher.end()};
        
        if (groupIdx[0]>0) {
            noteList.add(new InvalidNote(stringNumber, origin.substring(0, groupIdx[0]), position, lineName, distanceFromMeasureStart));
        }
        
        if (groupIdx[1]<origin.length()) {
            noteList.add(new InvalidNote(stringNumber, origin.substring(groupIdx[1]), position, lineName, distanceFromMeasureStart+groupIdx[1]));
        }
        
        noteList.addAll(getNotes(origin, groupIdx[0], groupIdx[1]));
        return noteList;
    }

    private List<Note> getNotes(String origin, int idx, int endIdx) {
        List<Note> noteList = new ArrayList<>();

        if (idx>=endIdx)
            return noteList;
        Matcher noteMatcher = Pattern.compile(patternPackage.get("note-pattern")).matcher(origin.substring(idx, endIdx));
        Note note1;
        if (!noteMatcher.find()) {
            noteList.add(new InvalidNote(stringNumber, origin.substring(idx, endIdx), position+idx, lineName, distanceFromMeasureStart+idx));
            return noteList;
        }else {
            List<Note> notes = createNote(noteMatcher.group(), position+idx+noteMatcher.start(), distanceFromMeasureStart+idx+noteMatcher.start());
            noteList.addAll(notes);
            note1 = notes.get(notes.size()-1);  //It is always the last note that builds a relationship. e.g you dont wanna get the grace note. you wanna get the grace pair because it is what will be creating a relationship with other notes
        }

        Matcher connectorMatcher = Pattern.compile(patternPackage.get("connector-pattern")).matcher(origin.substring(idx+noteMatcher.end(), endIdx));
        String connector;
        int connectorMatcherEnd;
        if (!connectorMatcher.find()) {
            connector = "";
            connectorMatcherEnd = 0;
             if (patternPackage.get("instrument").equalsIgnoreCase("guitar")) { //only guitar-like instrument notes have to be separated by connectors. drum notes can stay right next to each other, so no need to return if we don't see a connector.
                 if (idx + noteMatcher.end() < endIdx)
                     noteList.add(new InvalidNote(stringNumber, origin.substring(noteMatcher.end() + idx, endIdx), position + idx + noteMatcher.end(), lineName, distanceFromMeasureStart + idx));
                 return noteList;
             }
        }else {
            connector = connectorMatcher.group();
            connectorMatcherEnd = connectorMatcher.end();
        }
        List<Note> remainingNotes = getNotes(origin, idx+noteMatcher.end()+connectorMatcherEnd, endIdx);
        if (remainingNotes.isEmpty())
            return noteList;
        Note note2 = remainingNotes.get(0);
        noteList.addAll(remainingNotes);

        if (!connector.isBlank())
            addRelationship(note1, note2, connector);
        return noteList;
    }

    private HashMap<String, String> getPatternPackage(String origin) {
        HashMap<String, String> patternPackage = new HashMap<>();
//        Matcher guitarMatcher = Pattern.compile("^"+GUITAR_NOTE_GROUP_PATTERN+"$").matcher(origin);
//        Matcher drumMatcher = Pattern.compile("^"+DRUM_NOTE_GROUP_PATTERN+"$").matcher(origin);
        if (this.instrument == Instrument.GUITAR || this.instrument == Instrument.BASS) {
            patternPackage.put("instrument", "guitar");
            patternPackage.put("note-group-pattern", GUITAR_NOTE_GROUP_PATTERN);
            patternPackage.put("note-pattern", GUITAR_NOTE_PATTERN);
            patternPackage.put("connector-pattern", GUITAR_NOTE_CONNECTOR);
            return patternPackage;
        }else if (this.instrument == Instrument.DRUMS) {
            patternPackage.put("instrument", "drum");
            patternPackage.put("note-group-pattern", DRUM_NOTE_GROUP_PATTERN);
            patternPackage.put("note-pattern", DRUM_NOTE_PATTERN);
            patternPackage.put("connector-pattern", DRUM_NOTE_CONNECTOR);
            return patternPackage;
        }
        return null;
    }

    private void addRelationship(Note note1, Note note2, String relationship) {
        switch (relationship.toLowerCase()) {
            case "h" -> hammerOn((GuitarNote) note1, (GuitarNote) note2, false);
            case "p" -> pullOff((GuitarNote) note1, (GuitarNote) note2, false);
            case "/", "\\", "s" -> slide((GuitarNote) note1, (GuitarNote) note2, relationship, false);
        }
    }

    private List<Note> createNote(String origin, int position, int distanceFromMeasureStart) {
        List<Note> noteList = new ArrayList<>();
        if (patternPackage.get("instrument").equalsIgnoreCase("drum")) {
            if (origin.strip().equalsIgnoreCase("x")||origin.strip().equalsIgnoreCase("o"))
                noteList.add(createDrumNote(origin, position, distanceFromMeasureStart));
            else if (origin.strip().equalsIgnoreCase("f"))
                noteList.addAll(createFlam(origin, position, distanceFromMeasureStart));
            else if (origin.strip().equalsIgnoreCase("d"))
                noteList.addAll(createDrag(origin, position, distanceFromMeasureStart));
            else
                noteList.add(new InvalidNote(stringNumber, origin, position, lineName, distanceFromMeasureStart));
            return noteList;
        }
        noteList.addAll(createGrace(origin, position, distanceFromMeasureStart));
        if (!noteList.isEmpty()) return noteList;
        Note harmonic = createHarmonic(origin, position, distanceFromMeasureStart);
        if (harmonic!=null) {
        	harmonic.distance ++; //So the distance is to the fret number, not the square bracket
            noteList.add(harmonic);
            return noteList;
        }
        Note fret = createFret(origin, position, distanceFromMeasureStart);
        if (fret!=null) {
            noteList.add(fret);
            return noteList;
        }
        noteList.add(createInvalidNote(origin, position, distanceFromMeasureStart));
        return noteList;
    }

    private List<Note> createFlam(String origin, int position, int distanceFromMeasureStart) {
        Note graceNote = createDrumNote(origin, position, distanceFromMeasureStart);
        Note gracePair = createDrumNote(origin, position, distanceFromMeasureStart);
        grace(graceNote, gracePair);
        List<Note> notes = new ArrayList<>();
        notes.add(graceNote);
        notes.add(gracePair);
        return notes;
    }

    private List<Note> createDrag(String origin, int position, int distanceFromMeasureStart) {
//        Note note1 = createDrumNote(origin, position, distanceFromMeasureStart);
//        Note note2 = createDrumNote(origin, position, distanceFromMeasureStart);
//        note2.addDecor((noteModel) -> {
//            noteModel.setChord(null);
//            return true;
//        }, "success");
//        List<Note> notes = new ArrayList<>();
//        notes.add(note1);
//        notes.add(note2);
//        //slur(note1, note2);
//        return notes;
    	return createFlam(origin, position, distanceFromMeasureStart);
    }

    private Note createDrumNote(String origin, int position, int distanceFromMeasureStart) {
        return new DrumNote(stringNumber, origin, position, this.lineName, distanceFromMeasureStart);
    }

    private Note createInvalidNote(String origin, int position, int distanceFromMeasureStart) {
        return new InvalidNote(stringNumber, origin, position, lineName, distanceFromMeasureStart);
    }

    private Note createHarmonic(String origin, int position, int distanceFromMeasureStart) {
        Note note;
        if (!origin.matches(HARMONIC)) return null;

        Matcher fretMatcher = Pattern.compile("(?<=\\[)[0-9]+(?=\\])").matcher(origin);
        fretMatcher.find();
        note = createFret(fretMatcher.group(), position+fretMatcher.start(), distanceFromMeasureStart);
        note.addDecor((noteModel) -> {
            Technical technical = noteModel.getNotations().getTechnical();
            technical.setHarmonic(new Harmonic(new Natural()));
            return true;
        }, "success");

        return note;
    }

    private boolean slide(GuitarNote note1, GuitarNote note2, String symbol, boolean onlyMessage) {
        String message = "success";
        int startIdx = note1.position;
        int endIdx = note2.position+note2.origin.length();
        if (symbol.equals("/") && note1.getFret()>note2.getFret()) {
            //first bracket is the message priority, second is position to be highlighted. dont add a second bracket if you only want the individual notes to be highlighted
            message = "[2]["+startIdx+","+endIdx+"]Slide up \"/\" should go from a lower to a higher note.";
        }else if (symbol.equals("\\") && note1.getFret()<note2.getFret()) {
            message = "[2]["+startIdx+","+endIdx+"]Slide down \"/\" should go from a higher to a lower note.";
        }

        if (onlyMessage) {
            note1.addDecor((noteModel) -> true, message);
            note2.addDecor((noteModel) -> true, message);
            return true;
        }


        AtomicInteger slideNum = new AtomicInteger();
        note1.addDecor((noteModel) -> {
            Notations notations = noteModel.getNotations();
            Slide slide = new Slide("start");
            slideNum.set(slide.getNumber());
            if (notations.getSlides()==null) notations.setSlides(new ArrayList<>());
            notations.getSlides().add(slide);
            return true;
        }, message);
        note2.addDecor((noteModel) -> {
            Notations notations = noteModel.getNotations();
            Slide slide = new Slide("stop", slideNum.get());
            if (notations.getSlides()==null) notations.setSlides(new ArrayList<>());
            notations.getSlides().add(slide);
            return true;
        }, message);
        return true;
    }

    private boolean slur(Note note1, Note note2) {
        String message = "success";

        AtomicInteger slurNum = new AtomicInteger();
        note1.addDecor((noteModel) -> {
            if (noteModel.getNotations()==null)
                noteModel.setNotations(new Notations());
            Notations notations = noteModel.getNotations();
            Slur slur = new Slur("start");
            slurNum.set(slur.getNumber());
            if (notations.getSlurs()==null) notations.setSlurs(new ArrayList<>());
            notations.getSlurs().add(slur);
            return true;
        }, message);
        note2.addDecor((noteModel) -> {
            if (noteModel.getNotations()==null)
                noteModel.setNotations(new Notations());
            Notations notations = noteModel.getNotations();
            Slur slur = new Slur("stop", slurNum.get());
            if (notations.getSlurs()==null) notations.setSlurs(new ArrayList<>());
            notations.getSlurs().add(slur);
            return true;
        }, message);
        return true;
    }

    private boolean hammerOn(GuitarNote note1, GuitarNote note2, boolean onlyMessage) {
        String message = "success";
        boolean success = true;
        if (note1.getFret()>note2.getFret()) {
            int startIdx = note1.position;
            int endIdx = note2.position+note2.origin.length();
            message = "[2]["+startIdx+","+endIdx+"]Hammer on \"h\" should go from a lower to a higher note.";
            success = false;
        }

        if (onlyMessage) {
            note1.addDecor((noteModel) -> true, message);
            note2.addDecor((noteModel) -> true, message);
            return true;
        }

        AtomicInteger hammerOnNum = new AtomicInteger();
        note1.addDecor((noteModel) -> {
            Technical technical = noteModel.getNotations().getTechnical();
            HammerOn hammerOn = new HammerOn("start");
            hammerOnNum.set(hammerOn.getNumber());
            if (technical.getHammerOns()==null) technical.setHammerOns(new ArrayList<>());
            technical.getHammerOns().add(hammerOn);
            return true;
        }, message);
        note2.addDecor((noteModel) -> {
            Technical technical = noteModel.getNotations().getTechnical();
            HammerOn hammerOn = new HammerOn("stop", hammerOnNum.get());
            if (technical.getHammerOns()==null) technical.setHammerOns(new ArrayList<>());
            technical.getHammerOns().add(hammerOn);
            return true;
        }, message);
        if (success)
            success = slur(note1, note2);
        return success;
    }

    private boolean pullOff(GuitarNote note1, GuitarNote note2, boolean onlyMessage) {
        String message = "success";
        boolean success = true;
        if (note1.getFret()<note2.getFret()) {
            int startIdx = note1.position;
            int endIdx = note2.position+note2.origin.length();
            message = "[2]["+startIdx+","+endIdx+"]Pull off \"p\" should go from a higher to a lower note.";
            success = false;
        }

        if (onlyMessage) {
            note1.addDecor((noteModel) -> true, message);
            note2.addDecor((noteModel) -> true, message);
            return true;
        }

        AtomicInteger pullOffNum = new AtomicInteger();
        note1.addDecor((noteModel) -> {
            Technical technical = noteModel.getNotations().getTechnical();
            PullOff pullOff = new PullOff("start");
            pullOffNum.set(pullOff.getNumber());
            if (technical.getPullOffs()==null) technical.setPullOffs(new ArrayList<>());
            technical.getPullOffs().add(pullOff);
            return true;
        }, message);
        note2.addDecor((noteModel) -> {
            Technical technical = noteModel.getNotations().getTechnical();
            PullOff pullOff = new PullOff("stop", pullOffNum.get());
            if (technical.getPullOffs()==null) technical.setPullOffs(new ArrayList<>());
            technical.getPullOffs().add(pullOff);
            return true;
        }, message);
        if (success)
            success = slur(note1, note2);
        return success;
    }

    private List<Note> createGrace(String origin, int position, int distanceFromMeasureStart) {
        List<Note> noteList = new ArrayList<>();
        if (!origin.matches(GRACE)) return noteList;
        Matcher graceNoteMatcher = Pattern.compile("(?<=g)"+FRET+"(?![0-9])").matcher(origin);
        Matcher gracePairMatcher = Pattern.compile("(?<!g])"+FRET+"$").matcher(origin);
        Matcher relationshipMatcher = Pattern.compile("(?<=[0-9])[^0-9](?=[0-9])").matcher(origin);
        graceNoteMatcher.find();
        gracePairMatcher.find();
        relationshipMatcher.find();
        GuitarNote graceNote = createFret(graceNoteMatcher.group(), position+graceNoteMatcher.start(), distanceFromMeasureStart+graceNoteMatcher.start());
        GuitarNote gracePair = createFret(gracePairMatcher.group(), position+gracePairMatcher.start(), distanceFromMeasureStart+gracePairMatcher.start());
        String relationship = relationshipMatcher.group();
        if (relationship.equals("h"))
            hammerOn(graceNote, gracePair, true);
        else if (relationship.equals("p"))
            pullOff(graceNote, gracePair, true);
        grace(graceNote, gracePair);
        noteList.add(graceNote);
        noteList.add(gracePair);
        return noteList;
    }

	private GuitarNote createFret(String origin, int position, int distanceFromMeasureStart) {
		if (!origin.matches(FRET))
			return null;
		if ((Settings.getInstance().ddStyle == DoubleDigitStyle.NOTE_ON_SECOND_DIGIT_STRETCH)
				|| (Settings.getInstance().ddStyle == DoubleDigitStyle.NOTE_ON_SECOND_DIGIT_NO_STRETCH))
			if (origin.length() == 2)
				distanceFromMeasureStart++;
		if (this.instrument == Instrument.GUITAR)
			return new GuitarNote(stringNumber, origin, position, lineName, distanceFromMeasureStart);
		else if (this.instrument == Instrument.BASS)
			return new BassNote(stringNumber, origin, position, lineName, distanceFromMeasureStart);
		else
			return null;
	}

    private boolean grace(Note graceNote, Note gracePair) {
        boolean success;
        success = slur(graceNote, gracePair);
        if (success) {
            grace(graceNote);
            graceNote.isGrace = true;
        }
        return success;
    }

    private boolean grace(Note note) {
        note.addDecor((noteModel) -> {
            noteModel.setGrace(Note.SLASHED_GRACE ? new Grace("yes") : new Grace());
            noteModel.setDuration(null);
            noteModel.setChord(null);
            return true;
        }, "success");
        return true;
    }

    public interface NoteDecor {
        boolean applyTo(models.measure.note.Note noteModel);
    }
}
