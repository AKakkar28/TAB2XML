package GUI;


import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

import org.jfugue.player.ManagedPlayer;
import org.jfugue.player.Player;

import javafx.scene.control.TextField;
import MusicNotes.CanvasNotes;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;

import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;




public class PreviewSheetMusicController extends Application{

	private MainViewController mvc;
	public Highlighter highlighter;

	public static Window convertWindow;

	@FXML public  Canvas canvas;
	@FXML private Button Save;
	@FXML private Button editStyle;
	@FXML private ImageView image ;
	@FXML private AnchorPane musicPane;
	@FXML private Button playMusic;
	@FXML private Button Edit;
	@FXML private Button pauseMusic;
	@FXML private AnchorPane anchor;
	@FXML private ScrollPane scroll;
	@FXML private Button musicInfo;
	@FXML private Button gotoMeasureButton;
	@FXML private TextField gotoMeasureField;
	public static CanvasNotes canvasNote = new CanvasNotes();
	public boolean playing = false;
	public boolean havePlayed = false;
	public static Thread t1;
	ManagedPlayer mplayer;
	public static Sequencer seqMang = null;
	public String musicgoto="";

	public PreviewSheetMusicController() {}

	public void setMainViewController(MainViewController mvcInput) {
		mvc = mvcInput;
		scroll.setFitToWidth(true);
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		canvasNote.printNotes();
	}

	@FXML 
	public void initialize() {
		canvasNote.getCanvas(canvas, scroll, anchor);
		musicgoto = "";
		playing=false;
		try {
			seqMang= MidiSystem.getSequencer();
			seqMang.open();

		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}


	}

	@FXML
	void handleInfo(ActionEvent event) {

		Parent root;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("GUI/musicInfo.fxml"));
			root = loader.load();
			convertWindow = openNewWindow(root, "Sheet Music Details");
		} catch (IOException e) {
			Logger logger = Logger.getLogger(getClass().getName());
			logger.log(Level.SEVERE, "Failed to create new Window.", e);
		}
	}

	@FXML
	void handleMusic(ActionEvent event) {


		t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				if(Parser.XMLParser.instrument.equals("Guitar")) {

					if(musicgoto != "") {
						if(seqMang.getTickPosition() == seqMang.getTickLength()) {

							Player player = new Player();
							System.out.println("Giving: " + musicgoto);
							Sequence s = player.getSequence(musicgoto);
							try {
								seqMang.setSequence(s);
							} catch (InvalidMidiDataException e) {
								e.printStackTrace();
							}
							seqMang.setTickPosition(0);
							playing=false;
						}
					}

					else {
						if(seqMang.getTickPosition() == seqMang.getTickLength()) {

							Player player = new Player();
							Parser.GuitarParser.jfugueTester.playNotes();
							System.out.println("Giving: " + Parser.GuitarParser.jfugueTester.total);
							Sequence s = player.getSequence(Parser.GuitarParser.jfugueTester.total);
							try {
								seqMang.setSequence(s);
								canvasNote.currentMeasure = 0;
							} catch (InvalidMidiDataException e) {
								e.printStackTrace();
							}
							seqMang.setTickPosition(0);
							playing=false;
						}
					}

				}

				else if(Parser.XMLParser.instrument.equals("Drumset")) {

					if(seqMang.getTickPosition() == seqMang.getTickLength()) {

						Player player = new Player();
						Parser.DrumParser.drumTest.playNotes();
						Sequence s = player.getSequence(Parser.DrumParser.drumTest.total);
						try {
							seqMang.setSequence(s);
						} catch (InvalidMidiDataException e) {
							e.printStackTrace();
						}
						seqMang.setTickPosition(0);
						canvasNote.currentMeasure = 0;
						playing=false;
					}


				}

				if(!playing) {
					seqMang.start();
					canvasNote.highlight();
					playing = true;
				}

			}}); 
		t1.setDaemon(true);
		t1.start();

	}

	@FXML
	void handlePause(ActionEvent event) {

		if(playing) {
			seqMang.stop();
			canvasNote.t1.stop();
			havePlayed=true;
			playing=false;
		}

		else {
			System.out.println("Nothing Playing");
		}
	}


	@FXML
	void handleSave() {


		WritableImage canvImage = anchor.snapshot(null, null);
		Printer printer = Printer.getDefaultPrinter();
		PageLayout layout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);

		double pw = layout.getPrintableWidth();
		double ph = layout.getPrintableHeight(); 
		final double scaleX = pw / (canvImage.getWidth());

		final double scaleY = ph / canvImage.getHeight();
		final ImageView print_node = new ImageView(canvImage);
		print_node.getTransforms().add(new Scale(scaleX, scaleX));

		PrinterJob printSheet = PrinterJob.createPrinterJob();

		if (printSheet != null && printSheet.showPrintDialog(anchor.getScene().getWindow())) {

			double numberOfPages = Math.ceil(scaleX / scaleY);
			Translate gridTransform = new Translate(0, 0);
			print_node.getTransforms().add(gridTransform);
			for (int i = 0; i < numberOfPages; i++) {
				gridTransform.setY(-i * (ph / scaleX));
				printSheet.printPage(layout, print_node);
			}

			printSheet.endJob();

		}

	}

	@FXML 
	void  editInput() {
		seqMang.stop();
		canvasNote.t1.stop();
		mvc.convertWindow.hide();

	}

	@FXML
	void handleStyle(ActionEvent event) {

		Parent root;

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("GUI/sheetMusicStyle.fxml"));
			root = loader.load();
			SheetMusicStyleController controller = loader.getController();
			controller.setMainViewController(mvc);
			convertWindow = openNewWindow(root, "Sheet Music Style Editor");
		} catch (IOException e) {
			Logger logger = Logger.getLogger(getClass().getName());
			logger.log(Level.SEVERE, "Failed to create new Window.", e);
		}


	}


	Window openNewWindow(Parent root, String windowName) {
		Stage stage = new Stage();
		stage.setTitle(windowName);
		//stage.initModality(Modality.APPLICATION_MODAL);
		stage.initModality(Modality.NONE);
		stage.initOwner(MainApp.STAGE);
		stage.setResizable(false);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		return scene.getWindow();
	}


	@FXML
	void handleGotoMeasure(ActionEvent event) {
		int count = 0;

		String measureValueText=gotoMeasureField.getText();
		try {
			count= Integer.parseInt(measureValueText);
		}

		catch (NumberFormatException e) {
			count = 0;
		}


		if(count < 1 || count > canvasNote.totalMeasures) {
			Alert alert = new Alert(AlertType.ERROR, "Invalid Measure Entered", ButtonType.OK);
			alert.showAndWait();
			System.out.println("Measure number not vaid: " + measureValueText);
		}

		else {
			Parser.GuitarParser.jfugueTester.playNotes();
			String playn= Parser.GuitarParser.jfugueTester.total;
			String instr=playn.substring(0,18);

			String measures =playn.substring(18,playn.length()-1);

			int index = nthOccurrence(measures, "|", count);
			String complete = instr + measures.substring(index,measures.length()-1);
			System.out.println("a"+complete);

			musicgoto=complete;
			canvasNote.goToMeasure(count);
		}

	}



	public static int nthOccurrence(String str1, String str2, int n) {

		String tempStr = str1;
		int tempIndex = -1;
		int finalIndex = 0;
		for(int occurrence = 0; occurrence < n ; ++occurrence){
			tempIndex = tempStr.indexOf(str2);
			if(tempIndex==-1){
				finalIndex = 0;
				break;
			}
			tempStr = tempStr.substring(++tempIndex);
			finalIndex+=tempIndex;
		}
		return --finalIndex;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("Hello");
	}


}

