package GUI;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

import org.fxmisc.richtext.CodeArea;

import Parser.XMLParser;

public class MusicInfoController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private CodeArea musicText;
    
    public CodeArea getCodeArea() {
    	return musicText;
    }
    
    @FXML
    void initialize() {
    	musicText.clear();
    	ArrayList<String> details = XMLParser.details;
    	for(int i=0; i<details.size(); i++) {
    		musicText.appendText(details.get(i));
    		musicText.appendText("\n");
    	}
	}

}
