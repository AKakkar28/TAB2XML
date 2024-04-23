package PlayNotes;

import java.util.ArrayList;
import org.jfugue.player.Player;

import MusicNotes.CanvasNotes;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

@SuppressWarnings("unused")
public class JfugueForGuitar {

	private  ArrayList<String> notes = new ArrayList<>();
	private ArrayList<String> stringList;
	private ArrayList<String> fretList;
	private ArrayList<Integer> chordList;
	private ArrayList<String> alterList;
	private  ArrayList<Integer> nNPM = new ArrayList<Integer>();
	private  ArrayList<Integer> xPlacement;
	private  ArrayList<Integer> yPlacement;
	private AnimationTimer loop;
	public Player player;
	public String total = "";
	public static String tempo = "120";


	public static CanvasNotes canvasNote;
	private  int nNPMCounter = 0;
	GraphicsContext graphics_context;


	public void getNotes(ArrayList<String>NotesReceived, ArrayList<Integer> nNPMRecieved, ArrayList<String> recievedString, ArrayList<String> recievedFret, ArrayList<Integer> chordRecieved, ArrayList<String> altersRecieved) {

		notes = NotesReceived;	
		nNPM = nNPMRecieved;
		stringList = recievedString;
		fretList = recievedFret;
		chordList = chordRecieved;
		alterList = altersRecieved;
	}

	public void getCanvas(GraphicsContext graphicsRecieved, ArrayList<Integer> xRecieved, ArrayList<Integer> yRecieved) {
		graphics_context = graphicsRecieved;
		xPlacement = xRecieved;
		yPlacement = yRecieved;

	}

	public void playNotes() {
		if(notes.isEmpty() == false) {

			Player player = new Player();
		
			String str= "T" + tempo + " V0 I[Guitar] | ";
			total = str;
			System.out.println(xPlacement);
			System.out.println(yPlacement);
			int counter=0;
			

			for(int i=0;i<notes.size();i++) {
				total+= notes.get(i);
			
				
				if((i+1)<chordList.size() && chordList.get(i+1)==0) 
				{
					total+="+";
				}
				
				else {
					total += " ";
				}
				
				
				if(nNPMCounter<nNPM.size() && i == (nNPM.get(nNPMCounter) - 1)) {
					total += "| ";
					nNPMCounter ++;
				}
				
			}

			System.out.println(total);
			
			//player.play(total);
			nNPMCounter = 0;
		}
	}

	public void highLightNotes(int index) {
		
		 loop = new AnimationTimer() {

			@Override
			public void handle(long now) {
				
				graphics_context.setStroke(Color.RED);
				graphics_context.setLineWidth(4);
				graphics_context.strokeRect((xPlacement.get(index) - 10), (yPlacement.get(index) - 30), (xPlacement.get(index) - 10), (yPlacement.get(index) - 50));
				loop.stop();
			}
		};

		loop.start();

	}




}

