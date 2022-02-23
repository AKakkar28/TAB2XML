package PlayNotes;

import java.util.ArrayList;

import org.jfugue.player.Player;

import MusicNotes.CanvasNotes;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class JfugueTest {

	private  ArrayList<String> notes = new ArrayList<>();
	private ArrayList<String> stringList;
	private ArrayList<String> fretList;
	private  ArrayList<Integer> nNPM = new ArrayList<Integer>();
	private  ArrayList<Integer> xPlacement;
	private  ArrayList<Integer> yPlacement;
	private AnimationTimer loop;

	public static CanvasNotes canvasNote;
	private  int nNPMCounter = 0;
	GraphicsContext graphics_context;


	public void getNotes(ArrayList<String>NotesReceived, ArrayList<Integer> nNPMRecieved, ArrayList<String> recievedString, ArrayList<String> recievedFret) {

		notes = NotesReceived;	
		nNPM = nNPMRecieved;
		stringList = recievedString;
		fretList = recievedFret;

	}

	public void getCanvas(GraphicsContext graphicsRecieved, ArrayList<Integer> xRecieved, ArrayList<Integer> yRecieved) {
		graphics_context = graphicsRecieved;
		xPlacement = xRecieved;
		yPlacement = yRecieved;

	}

	public void playNotes() {
		if(notes.isEmpty() == false) {
			Player player = new Player();
			String str="V0 I[Guitar] |";
			String total = str;
			System.out.println(xPlacement);
			System.out.println(yPlacement);
			for(int i=0;i<notes.size();i++) {
			//	highLightNotes(i);
				//player.play(notes.get(i));
				total += " " + notes.get(i);

				if(i == (nNPM.get(nNPMCounter) - 1)) {
					total += " |";
					player.play("|");
					nNPMCounter ++;
				}
			}

			System.out.println(total);
			player.play(total);
			nNPMCounter = 0;
		}


		else {
			System.out.println("No notes to play");
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

