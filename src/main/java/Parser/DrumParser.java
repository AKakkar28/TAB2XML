package Parser;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import PlayNotes.JfugueForDrum;
import PlayNotes.MidiDrum;

import GUI.PreviewSheetMusicController;

public class DrumParser {

	public ArrayList<String> instrumentNameList = new ArrayList<>();
	public ArrayList<String> instrumentIDList = new ArrayList<>();
	public ArrayList<Integer> midiChannelList = new ArrayList<>();
	public ArrayList<Integer> midiProgramList = new ArrayList<>();
	public ArrayList<Integer> midiUnpitchList = new ArrayList<>();
	public ArrayList<Double> midiVolumeList = new ArrayList<>();
	public ArrayList<Integer> midiPanlist = new ArrayList<>();

	public   ArrayList<String> notesList = new ArrayList<>();
	public static ArrayList<Integer> chordList = new ArrayList<>();
	public static ArrayList<String> noteHeadList = new ArrayList<>();
	public static ArrayList<Integer> noteLengthList = new ArrayList<>();
	public static ArrayList<String> stemList = new ArrayList<>();
	public static  ArrayList<String> noteInstrumentIDList = new ArrayList<>();
	public static MidiDrum midiTester = new MidiDrum();
	public static JfugueForDrum drumTest = new JfugueForDrum();
	
	public ArrayList<Integer> barlineList = new ArrayList<Integer>();
	public ArrayList<Integer> directionList = new ArrayList<Integer>();

	public void parseDrums(ArrayList<String> details,NodeList measures, ArrayList<Integer> nNPM, Document doc) {
		
		 notesList = new ArrayList<>();
		 chordList = new ArrayList<>();
		 noteHeadList = new ArrayList<>();
		 noteLengthList = new ArrayList<>();
		 stemList = new ArrayList<>();
		 noteInstrumentIDList = new ArrayList<>();
		
		boolean hasBarline = false;
		boolean HasDirection = false;
		
		// Factor for SLUR and DOT
		NodeList barlines =  doc.getElementsByTagName("barline");
		NodeList barStyles =  doc.getElementsByTagName("bar-style");
		NodeList repeats =  doc.getElementsByTagName("repeat");
		
		NodeList directions=  doc.getElementsByTagName("direction");
		NodeList words =  doc.getElementsByTagName("words");

		
		for(int i=0; i < measures.getLength(); i++) {
			
			NodeList measure = (NodeList) measures.item(i); 
			
			for(int s = 0; s<measure.getLength(); s++) {

				Node notesMeasure = (Node) measure.item(s); // Note of First Measure
				//details.add("Measure " + i + " Attribute: " + notesMeasure);

				if(notesMeasure.getNodeName().equals("barline")) {
					//details.add("Has Barline!");
					hasBarline = true;
				}

				if(notesMeasure.getNodeName().equals("direction")) {
					//details.add("Has Direction!");
					HasDirection = true;
				}		
			}
			
			if(barlines.item(i) != null && hasBarline) {

				Element barLine = (Element) barlines.item(i);    
				String bar = barLine.getAttribute("location");
				details.add("Bar location of measure " + (i+1) + ": " + bar);
			}
			
			
			if(repeats.item(i) != null && hasBarline) {

				Element repeat = (Element) repeats.item(i);    
				String repeatDirection = repeat.getAttribute("direction");
				
				if(repeatDirection.equals("backward")) {
					String repeatValue = repeat.getAttribute("times");
					details.add("Repeat Direction of measure " + (i+1) + ": " + repeatDirection);
					details.add("Repeat Value of measure " + (i+1) + ": " + repeatValue);
				}
				else {
					details.add("Repeat Direction of measure " + (i+1) + ": " + repeatDirection);
				}
				
			}
			
			if(barStyles.item(i) != null && hasBarline) {

				Element barStyle = (Element) barStyles.item(i);    
				String style = barStyle.getTextContent();
				details.add("Barstyle of measure " + (i+1) + ": " + style);
			}
			
			hasBarline = false;
			HasDirection = false;
		}


		NodeList instrumentNames =  doc.getElementsByTagName("instrument-name");

		for (int j = 0; j<instrumentNames.getLength(); j++) {

			Element instrumentName = (Element) instrumentNames.item(j);    
			String  NOI = instrumentName.getTextContent();
			details.add("Instrument " + (j+1) + ": " + NOI);
			instrumentNameList.add(NOI);
		}


		details.add("==================================");

		NodeList midiChannels =  doc.getElementsByTagName("midi-channel");
		NodeList midiPrograms =  doc.getElementsByTagName("midi-program");
		NodeList midiUnpitcheds =  doc.getElementsByTagName("midi-unpitched");
		NodeList volumes =  doc.getElementsByTagName("volume");
		NodeList pans =  doc.getElementsByTagName("pan");

		for (int k = 0; k<midiChannels.getLength(); k++) {

			String instrumentID = "P1-I";

			Element midiChannel = (Element) midiChannels.item(k);    
			String  NOC = midiChannel.getTextContent();
			midiChannelList.add(Integer.parseInt(NOC));
			details.add("midi-Channel: " + NOC);

			Element midiProgram = (Element) midiPrograms.item(k);    
			String  NOP = midiProgram.getTextContent();
			midiProgramList.add(Integer.parseInt(NOP));
			details.add("midi-Program: " + NOP);


			Element midiUnpitched = (Element) midiUnpitcheds.item(k);    
			String  NOU = midiUnpitched.getTextContent();
			instrumentID += NOU;
			instrumentIDList.add(instrumentID);
			midiUnpitchList.add(Integer.parseInt(NOU));
			details.add("midi-Unpitched: " + NOU);

			Element volume= (Element) volumes.item(k);    
			String  NOV = volume.getTextContent();
			midiVolumeList.add(Double.parseDouble(NOV));
			details.add("Volume: " + NOV);

			Element pan = (Element) pans.item(k);    
			String  NOPA = pan.getTextContent();
			details.add("Pan: " + NOPA);
			midiPanlist.add(Integer.parseInt(NOPA));
			


			details.add("==================================");

		}

		details.add("Midi Channels: " + midiChannelList);
		details.add("Midi Program: " + midiProgramList);
		details.add("Midi Unpitched: " + midiUnpitchList);
		details.add("Midi Volumes: " + midiVolumeList);
		details.add("Midi Pans: " + midiPanlist);
		details.add("instrument ID List: " + instrumentIDList);
		details.add("");

		for (int i=0; i<measures.getLength(); i++) {

			NodeList divisions  =  doc.getElementsByTagName("divisions");
			Element division = (Element) divisions.item(i);    
			String  NOD = division.getTextContent();
			details.add("Number of divisions in measure " + (i + 1) + ": " + NOD);


			NodeList fifths =  doc.getElementsByTagName("fifths");
			String  NOF = "";

			if(fifths.item(i) != null) {

				Element fifth = (Element) fifths.item(i);    
				NOF = fifth.getTextContent();
				details.add("Fifth of measure " + ( i+1) + ": " + NOF);
			}

			NodeList signs =  doc.getElementsByTagName("sign");
			String  NOS = "";

			if ( signs.item(i) != null ) {
				Element sign = (Element) signs.item(i);    
				NOS = sign.getTextContent();
				details.add("Sign: " + NOS);
			}

			NodeList Lines =  doc.getElementsByTagName("line");


			if ( Lines.item(i) != null ) {
				Element Line = (Element) Lines.item(i);    
				String NOL = Line.getTextContent();
				details.add("Line: " + NOL);
			}

			if(barStyles.item(i) != null) {

				Element barStyle = (Element) barStyles.item(i);    
				String	NOB = barStyle.getTextContent();
				details.add("Bar styles in measure " + (i+1)  + NOB);
			}
			
			
			if(words.item(i) != null) {

				Element word = (Element) words.item(i);    
				String	NOW = word.getTextContent();
				details.add("words in measure " + (i+1)  + NOW);
			}

		}

		NodeList notes = doc.getElementsByTagName("note");
		details.add("");
		details.add("Amount of notes is: " + notes.getLength());
		details.add("--------------------");


		NodeList steps =  doc.getElementsByTagName("display-step");
		NodeList octaves = doc.getElementsByTagName("display-octave");
		NodeList durations = doc.getElementsByTagName("duration");
		NodeList noteInstrumID = doc.getElementsByTagName("instrument");
		NodeList voices = doc.getElementsByTagName("voice");
		NodeList types = doc.getElementsByTagName("type");
		NodeList stems= doc.getElementsByTagName("stem");
		NodeList noteHeads = doc.getElementsByTagName("notehead");


		String[] noteHeadExistList = new String[noteHeads.getLength()];
		int noteHeadExistsCounter = 0;

		for(int i = 0; i < noteHeadExistList.length; i++) {

			Element noteHead = (Element) noteHeads.item(i);    
			String noteHeadValue= noteHead.getTextContent();
			noteHeadExistList[i] = noteHeadValue;
		}


		for(int j = 0; j < notes.getLength(); j++) {
			
			boolean hasDuration = false;


			NodeList singleNote = (NodeList) notes.item(j);		

			boolean hasChord = false;
			boolean hasNoteHead = false;

			for(int k = 0; k < singleNote.getLength(); k++) {

				Node singleNoteElement = (Node) singleNote.item(k);				

				if(singleNoteElement.getNodeName().equals("chord")) {
					hasChord = true;
				}

				if(singleNoteElement.getNodeName().equals("notehead")) {
					hasNoteHead = true;
				}
				
				if(singleNoteElement.getNodeName().equals("duration")) {
					hasDuration =true;
				}


			}

			String note = "";

			details.add("Note: " + (j+1));


			if(hasChord) {
				details.add("Chord: 0");
				chordList.add(0);

			}

			else {
				details.add("Chord: 1");
				chordList.add(1);
			}


			if(octaves.item(j) != null) {

				Element octave = (Element) octaves.item(j);    
				String  octaveValue = octave.getTextContent();
				details.add("Octave: " +  octaveValue);
				note += octaveValue;
			}

			if(steps.item(j) != null) {

				Element step = (Element) steps.item(j);    
				String  stepValue= step.getTextContent();
				details.add("Step: " +  stepValue);
				note += stepValue;
			}


			if(durations.item(j) != null) {

				Element duration = (Element) durations.item(j);    
				String  durationValue = duration.getTextContent();

				details.add("Duration: " +  durationValue);
				

				
	

				if (hasDuration) {
				noteLengthList.add(Integer.parseInt(durationValue));
				}
				
				else {
					noteLengthList.add(0);
					
				}
			}

			if(noteInstrumID.item(j) != null) {

				Element noteinstrumIDElement = (Element) noteInstrumID.item(j);    
				String  instrumIDText = noteinstrumIDElement.getAttribute("id");
				details.add("Instrument ID: " +  instrumIDText);
				noteInstrumentIDList.add(instrumIDText);
			}

			if(voices.item(j) != null) {

				Element voice = (Element) voices.item(j);    
				String  voiceValue = voice.getTextContent();
				details.add("Voice: " +  voiceValue);

			}

			if(types.item(j) != null) {

				Element type = (Element) types.item(j);    
				String  typeValue = type.getTextContent();
				details.add("Type: " +  typeValue);
			}

			if(stems.item(j) != null) {

				Element stem = (Element) stems.item(j);    
				String  stemValue = stem.getTextContent();
				details.add("Stem: " +  stemValue);
				stemList.add(stemValue);
			}

			if(hasNoteHead) {
				details.add("Note Head: " +  noteHeadExistList[noteHeadExistsCounter]);
				noteHeadList.add(noteHeadExistList[noteHeadExistsCounter]);
				noteHeadExistsCounter++;
			}
			else {
				details.add("Note Head: non" );
				noteHeadList.add("non");


			}
			
			details.add("Note: " + note);
			notesList.add(note);
			details.add("--------------------");		
		}


		details.add("");
		details.add("Notes: " + notesList);
		details.add("Chords: " + chordList);
		details.add("Note Heads: " + noteHeadList);
		details.add("Notes Length: " + noteLengthList);
		details.add("Stems: " + stemList);
		details.add("Note Instrument ID: " + noteInstrumentIDList);
		details.add("");
		

		System.out.println("");
		System.out.println("Notes: " + notesList);
		System.out.println("Chords: " + chordList);
		System.out.println("Note Heads: " + noteHeadList);
		System.out.println("Notes Length: " + noteLengthList);
		System.out.println("Stems: " + stemList);
		System.out.println("Note Instrument ID: " + noteInstrumentIDList);
		System.out.println("");


		for(int i = 0; i< nNPM.size(); i++) {
			if(i != 0) {
				nNPM.set(i, (nNPM.get(i) + nNPM.get(i-1)));
			}
		}

		drumTest.getNotes(notesList, chordList, noteHeadList, noteLengthList, stemList, noteInstrumentIDList, nNPM);
		PreviewSheetMusicController.canvasNote.getNotesDrums("Drumset", notesList, chordList, noteHeadList, noteLengthList, stemList, noteInstrumentIDList, nNPM);


	}
}


