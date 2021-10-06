package converter.measure;

import GUI.MainView;
import converter.Score;
import converter.measure_line.BassMeasureLine;
import converter.measure_line.DrumMeasureLine;
import converter.measure_line.GuitarMeasureLine;
import converter.measure_line.MeasureLine;
import models.measure.attributes.*;
import utility.ValidationError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BassMeasure extends GuitarMeasure {
    public static final int MIN_LINE_COUNT = 4;
    public static final int MAX_LINE_COUNT = 4;

    public BassMeasure(List<String> lines, List<String[]> lineNamesAndPositions, List<Integer> linePositions, boolean isFirstMeasure) {
        super(lines, lineNamesAndPositions, linePositions, isFirstMeasure);
    }

    @Override
    public Attributes getAttributesModel() {
        Attributes attributes = new Attributes();
        attributes.setKey(new Key(0));
        if (this.isTimeSigOverridden())
            attributes.setTime(new Time(this.beatCount, this.beatType));

        if (this.measureCount == 1) {
            attributes.setClef(new Clef("TAB", 5));
            attributes.setDivisions(Score.GLOBAL_DIVISIONS);
            List<StaffTuning> staffTunings = new ArrayList<>();
            staffTunings.add(new StaffTuning(1, "E", 1));
            staffTunings.add(new StaffTuning(2, "A", 1));
            staffTunings.add(new StaffTuning(3, "D", 2));
            staffTunings.add(new StaffTuning(4, "G", 2));

            attributes.setStaffDetails(new StaffDetails(4, staffTunings));
        }


        return attributes;
    }

    @Override
    public List<ValidationError> validate() {
        //------------------the below is copy paste of Measure.validate()------------------------------------------------
        List<ValidationError> result = new ArrayList<>();

        boolean hasGuitarMeasureLines = true;
        boolean hasDrumMeasureLines = true;
        boolean lineSizeEqual = true;

        int previousLineLength = -1;
        for (MeasureLine measureLine : this.measureLineList) {
            hasGuitarMeasureLines &= measureLine instanceof GuitarMeasureLine;
            hasDrumMeasureLines &= measureLine instanceof DrumMeasureLine;

            int currentLineLength = measureLine.line.replace("\s", "").length();
            lineSizeEqual &= (previousLineLength<0) || previousLineLength==currentLineLength;
            previousLineLength = currentLineLength;
        }
        if (!(hasGuitarMeasureLines || hasDrumMeasureLines)) {
            ValidationError error = new ValidationError(
                    "All measure lines in a measure must be of the same type (i.e. all guitar measure lines or all drum measure lines)",
                    1,
                    this.getLinePositions()
            );
            if (MainView.ERROR_SENSITIVITY>= error.getPriority())
                result.add(error);
        }

        if (!lineSizeEqual) {
            ValidationError error = new ValidationError(
                    "Unequal measure line lengths may lead to incorrect note durations.",
                    2,
                    this.getLinePositions()
            );
            if (MainView.ERROR_SENSITIVITY>= error.getPriority())
                result.add(error);
        }
        //------------------the above is copy paste of Measure.validate()------------------------------------------------

        // Now, all we need to do is check if they are actually bass measures
        if (!(this.measureLineList.get(0) instanceof BassMeasureLine)) {
            ValidationError error = new ValidationError(
                    "All measure lines in this measure must be Bass measure lines.",
                    1,
                    this.getLinePositions()
            );
            if (MainView.ERROR_SENSITIVITY>= error.getPriority())
                result.add(error);
        }

        if (this.measureLineList.size()<MIN_LINE_COUNT || this.measureLineList.size()>MAX_LINE_COUNT) {
            HashMap<String, String> response = new HashMap<>();
            String rangeMsg;
            if (MIN_LINE_COUNT==MAX_LINE_COUNT)
                rangeMsg = ""+MIN_LINE_COUNT;
            else
                rangeMsg = "between "+MIN_LINE_COUNT+" and "+MAX_LINE_COUNT;
            ValidationError error = new ValidationError(
                    "A Bass measure should have "+rangeMsg+" lines.",
                    2,
                    this.getLinePositions()
            );
            if (MainView.ERROR_SENSITIVITY>= error.getPriority())
                result.add(error);
        }


        //-----------------Validate Aggregates (only if you don't have critical errors)------------------

        for (ValidationError error : result) {
            if (error.getPriority() <= Score.CRITICAL_ERROR_CUTOFF) {
                return result;
            }
        }

        for (MeasureLine measureLine : this.measureLineList) {
            result.addAll(measureLine.validate());
        }

        return result;
    }
}
