package models.measure.note;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class Unpitched {
    @JacksonXmlProperty(localName = "display-step")
    String displayStep;
    @JacksonXmlProperty(localName = "display-octave")
    String displayOctave;

    public Unpitched (String displayStep, int displayOctave){
        this.displayStep = displayStep;
        this.displayOctave = String.valueOf(displayOctave);
    }
}

