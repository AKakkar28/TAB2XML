package converter.measure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import converter.Score;
import converter.measure_line.TabDrumString;
import converter.measure_line.TabString;
import converter.note.Note;
import models.measure.Backup;
import models.measure.attributes.Attributes;
import models.measure.attributes.Clef;
import models.measure.attributes.Key;
import models.measure.attributes.Time;
import models.measure.barline.BarLine;
import models.measure.barline.Repeat;
import models.measure.direction.Direction;
import models.measure.direction.DirectionType;
import models.measure.direction.Words;
import utility.Settings;
import utility.ValidationError;

public class DrumMeasure extends TabMeasure {

    private static final int MIN_LINE_COUNT = 1;
    private static final int MAX_LINE_COUNT = 6;

    public DrumMeasure(List<String> lines, List<String[]> lineNamesAndPositions, List<Integer> linePositions, boolean isFirstMeasureInGroup) {
        super(lines, lineNamesAndPositions, linePositions, isFirstMeasureInGroup);
    }
    
    @Override
	protected int adjustDivisionsForDoubleCharacterNotes(int usefulMeasureLength) {
    	// Divisions is doubled for drums to account for double notes "d"
		return 2 * usefulMeasureLength;
	}
    
	@Override
	protected int adjustDurationForDoubleCharacterNotes(int duration, List<Note> chord, List<Note> nextChord) {
		// Divisions is doubled for drums to account for double notes "d"
		return duration * 2;
	}

	private Attributes getAttributesModel() {
        Attributes attributes = new Attributes();
        attributes.setDivisions(this.divisions);
        attributes.setKey(new Key(0));
        if (this.changesTimeSignature)
            attributes.setTime(new Time(this.beatCount, this.beatType));

        if (this.measureCount == 1) {
            attributes.setClef(new Clef("percussion", 2));
            
        }
        return attributes;
    }
    
	@Override
	public models.measure.Measure getModel() {
	    models.measure.Measure measureModel = new models.measure.Measure();
	    measureModel.setNumber(this.measureCount);
	    measureModel.setAttributes(this.getAttributesModel());
	
	    List<models.measure.note.Note> noteBeforeBackupModels = new ArrayList<>();
	    List<models.measure.note.Note> noteAfterBackupModels = new ArrayList<>();
	    for (int i=0; i<this.voiceSortedNoteList.size(); i++) {
	        List<Note> voice = this.voiceSortedNoteList.get(i);
	        double backupDuration = 0;
	        double currentChordDuration = 0;
	        for (Note note : voice) {
	            if (note.voice==1)
	                noteBeforeBackupModels.add(note.getModel());
	            if (note.voice==2)
	                noteAfterBackupModels.add(note.getModel());
	            if (note.startsWithPreviousNote)
	                currentChordDuration = Math.max(currentChordDuration, note.duration);
	            else {
	                backupDuration += currentChordDuration;
	                currentChordDuration = note.duration;
	            }
	        }
	        backupDuration += currentChordDuration;
	        if (voice.get(0).voice==1)
	            measureModel.setNotesBeforeBackup(noteBeforeBackupModels);
	        if (voice.get(0).voice==2)
	            measureModel.setNotesAfterBackup(noteAfterBackupModels);
	        if (i+1<this.voiceSortedNoteList.size()) {
	            measureModel.setBackup(new Backup((int)backupDuration));
	        }
	    }
	
	    List<BarLine> barLines = new ArrayList<>();
	    if (this.isRepeatStart()) {
	        BarLine barLine = new BarLine();
	        barLines.add(barLine);
	        barLine.setLocation("left");
	        barLine.setBarStyle("heavy-light");
	
	        Repeat repeat = new Repeat();
	        repeat.setDirection("forward");
	        barLine.setRepeat(repeat);
	
	        Direction direction = new Direction();
	        direction.setPlacement("above");
	        measureModel.setDirection(direction);
	
	        DirectionType directionType = new DirectionType();
	        direction.setDirectionType(directionType);
	
	        Words words = new Words();
	        words.setRelativeX(256.17);
	        words.setRelativeX(16.01);
	        words.setRepeatText("Repeat "+this.repeatCount+" times");
	        directionType.setWords(words);
	    }
	
	    if (this.isRepeatEnd()) {
	        BarLine barLine = new BarLine();
	        barLines.add(barLine);
	        barLine.setLocation("right");
	        barLine.setBarStyle("light-heavy");
	
	        Repeat repeat = new Repeat();
	        repeat.setDirection("backward");
	        barLine.setRepeat(repeat);
	    }
	
	    if (!barLines.isEmpty())
	        measureModel.setBarlines(barLines);
	    return measureModel;
	}

	/**
	 * Validates that all MeasureLine objects in this GuitarMeasure are GuitarMeasureLine objects, and validates its
	 * aggregated MeasureLine objects. It stops evaluation at the first aggregated object which fails validation.
	 * TODO it might be better to not have it stop when one aggregated object fails validation, but instead have it
	 *      validate all of them and return a List of all aggregated objects that failed validation, so the user knows
	 *      all what is wrong with their tablature file, instead of having to fix one problem before being able to see
	 *      what the other problems with their text file is.
	 * @return a HashMap<String, String> that maps the value "success" to "true" if validation is successful and "false"
	 * if not. If not successful, the HashMap also contains mappings "message" -> the error message, "priority" -> the
	 * priority level of the error, and "positions" -> the indices at which each line pertaining to the error can be
	 * found in the root string from which it was derived (i.e Score.ROOT_STRING).
	 * This value is formatted as such: "[startIndex,endIndex];[startIndex,endIndex];[startInde..."
	 */
	@Override
	public List<ValidationError> validate() {
	
	    //-----------------Validate yourself-------------------------
	    List<ValidationError> result = new ArrayList<>(super.validate()); //this validates if all MeasureLine objects in this measure are of the same type
	    int ERROR_SENSITIVITY = Settings.getInstance().errorSensitivity;
	
	    //if we are here, all MeasureLine objects are of the same type. Now, all we need to do is check if they are actually guitar measures
	    if (!(this.tabStringList.get(0) instanceof TabDrumString)) {
	        ValidationError error = new ValidationError(
	                "All measure lines in this measure must be Drum measure lines.",
	                1,
	                this.getLinePositions()
	        );
	        if (ERROR_SENSITIVITY>= error.getPriority())
	            result.add(error);
	    }
	
	    if (this.tabStringList.size()<MIN_LINE_COUNT || this.tabStringList.size()>MAX_LINE_COUNT) {
	        HashMap<String, String> response = new HashMap<>();
	        String rangeMsg;
	        if (MIN_LINE_COUNT==MAX_LINE_COUNT)
	            rangeMsg = ""+MIN_LINE_COUNT;
	        else
	            rangeMsg = "between "+MIN_LINE_COUNT+" and "+MAX_LINE_COUNT;
	        ValidationError error = new ValidationError(
	                "A Drum measure should have "+rangeMsg+" lines.",
	                1,
	                this.getLinePositions()
	        );
	        if (ERROR_SENSITIVITY>= error.getPriority())
	            result.add(error);
	    }
	
	    //-----------------Validate Aggregates (only if you dont have critical errors)------------------
	
	    for (ValidationError error : result) {
	        if (error.getPriority() <= Score.CRITICAL_ERROR_CUTOFF) {
	            return result;
	        }
	    }
	
	    for (TabString measureLine : this.tabStringList) {
	        result.addAll(measureLine.validate());
	    }
	
	    return result;
	}
}
