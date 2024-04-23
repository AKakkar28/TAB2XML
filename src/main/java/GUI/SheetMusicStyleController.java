package GUI;

import PlayNotes.JfugueForDrum;
import PlayNotes.JfugueForGuitar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.measure.note.Note;


public class SheetMusicStyleController {

	//private MainViewController mvc;

	ObservableList<String> fontsList = FXCollections.observableArrayList("Arial","Arial Black","Arial MT","Arial Narrow","Bazooka","Book Antiqua","Bookman Old Style","Boulder","Calisto MT","Calligrapher","Century Gothic","Century Schoolbook","Cezanne","CG Omega","CG Times","Charlesworth","Chaucer","Clarendon Condensed","Comic Sans MS","Copperplate Gothic Bold","Copperplate Gothic Light","Cornerstone","Coronet","Courier","Courier New","Cuckoo","Dauphin","Denmark","Fransiscan","Garamond","Geneva","Haettenschweiler","Heather","Helvetica","Herald","Impact","Jester","Letter Gothic","Lithograph","Lithograph Light","Long Island","Lucida Console","Lucida Handwriting","Lucida Sans","Lucida Sans Unicode","Marigold","Market","Matisse ITC","MS LineDraw","News GothicMT","OCR A Extended","Old Century","Papyrus","Pegasus","Pickwick","Poster","Pythagoras","Sceptre","Sherwood","Signboard","Socket","Steamer","Storybook","Subway","Tahoma","Technical","Teletype","Tempus Sans ITC","Times","Times New Roman","Times New Roman PS","Trebuchet MS","Tristan","Tubular","Unicorn","Univers","Univers Condensed","Vagabond","Verdana","Westminster");

	@FXML
	private Button Apply;

	@FXML
	private Button Close;

	@FXML
	private Slider LineSpacing;
	
	@FXML
	private Slider NoteSpacing;

	@FXML
	private AnchorPane settingsPane;

	@FXML
	private Slider sliderTempo;

	@FXML
	private ChoiceBox<String> fontsChoice;

	@FXML
	private TextField LineSpacingBox;

	@FXML
	private TextField NoteSpacingBox;

	@FXML
	private TextField tempBox;
	
	@FXML
    private TextField noteSizeBox;

    @FXML
    private Slider noteSizeSlider;


	public void setMainViewController(MainViewController mvcInput) {
		//	mvc = mvcInput;
		//    	noteSpacing.setTextFill(Color.web("#000000"));
		//		noteSpacing.setText(NoteSpacing.getMin()+"");
		//		lineSpacingLabel.setTextFill(Color.web("#000000"));
		//		lineSpacingLabel.setText(LineSpacing.getMin()+"");
		//		tempoLabel.setTextFill(Color.web("#000000"));
		//		tempoLabel.setText(sliderTempo.getMin()+"");
	}

	@FXML
	void initialize() {
		fontsChoice.setItems(fontsList);
		fontsChoice.setValue(PreviewSheetMusicController.canvasNote.fontRecieved);

		tempBox.setText(JfugueForGuitar.tempo);
		sliderTempo.setValue(Double.parseDouble(JfugueForDrum.tempo));

		NoteSpacingBox.setText(PreviewSheetMusicController.canvasNote.noteSpacing+"");
		NoteSpacing.setValue(PreviewSheetMusicController.canvasNote.noteSpacing);

		LineSpacingBox.setText(PreviewSheetMusicController.canvasNote.lineSpacing+"");
		LineSpacing.setValue(PreviewSheetMusicController.canvasNote.lineSpacing);
		
		noteSizeBox.setText(PreviewSheetMusicController.canvasNote.noteSize+"");
		noteSizeSlider.setValue(PreviewSheetMusicController.canvasNote.noteSize);
	}

	@FXML
	void handleApply(ActionEvent event) {
		PreviewSheetMusicController.canvasNote.setLineSpacing((int) LineSpacing.getValue());
		PreviewSheetMusicController.canvasNote.setNoteSpacing((int) NoteSpacing.getValue());
		PreviewSheetMusicController.canvasNote.fontRecieved = fontsChoice.getValue();
		PreviewSheetMusicController.canvasNote.noteSize = ((int)noteSizeSlider.getValue());
		JfugueForGuitar.tempo = tempBox.getText();
		JfugueForDrum.tempo = tempBox.getText();
		
//		if(XMLParser.instrument.equals("Guitar")) {
//			PreviewSheetMusicController.canvasNote.getNotesGuitar("Guitar",GuitarParser.stringList, GuitarParser.fretList, XMLParser.nNPM, GuitarParser.alterList, GuitarParser.noteLengthList, GuitarParser.chordList);
//			PreviewSheetMusicController.canvasNote.clearCanvas();
//			PreviewSheetMusicController.canvasNote.printNotes();
//		}
//		else {
//			PreviewSheetMusicController.canvasNote.getNotesDrums("Drumset", DrumParser.notesList,  DrumParser.chordList,  DrumParser.noteHeadList,  DrumParser.noteLengthList,  DrumParser.stemList,  DrumParser.noteInstrumentIDList,  XMLParser.nNPM);
//			
//		}
		
		//Clear Canvas and redraw with new settings
		PreviewSheetMusicController.canvasNote.clearCanvas();
		PreviewSheetMusicController.canvasNote.printNotes();
		
		//Close Window
		Stage stage = (Stage) Apply.getScene().getWindow();
		stage.close();

	}

	@FXML
	void handleClose(ActionEvent event) {
		PreviewSheetMusicController.convertWindow.hide();
	}

	@FXML
	void handleDragLineSpacing(MouseEvent event) {
		LineSpacingBox.setText((int)(LineSpacing.getValue())+"");
	}



	@FXML
	void handleDragNoteSpacing(MouseEvent event) {
		NoteSpacingBox.setText((int)(NoteSpacing.getValue())+"");
	}

	@FXML
	void handleDragTempo(MouseEvent event) {
		tempBox.setText((int)(sliderTempo.getValue())+"");
	}


	@FXML
	void handleLineSpacingBox(ActionEvent event) {
		LineSpacing.setValue( Double.parseDouble(tempBox.getText()) );
	}

	@FXML
	void handleNoteSpacingBox(ActionEvent event) {
		NoteSpacing.setValue( Double.parseDouble(tempBox.getText()) );
	}

	@FXML
	void handleTempoBox(ActionEvent event) {
		sliderTempo.setValue( Double.parseDouble(tempBox.getText()) );
	}
	
	
	@FXML
    void handleDragSize(MouseEvent event) {
		noteSizeBox.setText((int)noteSizeSlider.getValue()+"");
    }
	
    @FXML
    void handlenoteSizeBox(ActionEvent event) {
    	noteSizeSlider.setValue(Double.parseDouble(noteSizeBox.getText()));
    }


}
