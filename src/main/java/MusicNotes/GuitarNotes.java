package MusicNotes;

import java.awt.Color;
import java.awt.Graphics;
//import java.awt.color.*;
import javax.swing.JFrame;

public class GuitarNotes extends JFrame {
	public GuitarNotes() {

setTitle("Shape");
setSize(960, 960);
setVisible(true);
setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		//Vertical lines
		g.drawLine(0,268,960,268);
		g.drawLine(0,256,960,256);
		g.drawLine(0,244,960,244);
		g.drawLine(0,232,960,232);
		
//		// Simple black note(Quarter Note)
		g.fillOval(240, 240,80, 56);
		g.drawLine(320, 268, 320, 83);
		
		//Simple white note(Half Note)
		g.drawOval(340,340,80,56);	//g.setColor(Color.ORANGE);
		g.drawLine(420, 368, 420, 183);
		
		
		
		//Slanted Beamed Eighth Note
		g.fillOval(440,540,80,56);
		g.drawLine(520,568, 520, 383);
		
		g.fillOval(540,440,80,56);
		g.drawLine(620, 468, 620, 283);
		
		
		
		g.drawLine(520,383,620, 283);
//		
//		
//		
		//Beamed Eighth Note
		g.fillOval(640,440,80,56);
		g.drawLine(720,468,720, 283);
//		
		g.fillOval(740,440,80,56);
		g.drawLine(820,468,820, 283);
		
		g.drawLine(720,283,820, 283);
		
		int currentY = 268 -50;
		int currentX = 100-20; 
		int alpha = 127; // 50% transparent
		int val3 = Color.blue.getBlue();
		int val2 = Color.blue.getGreen();
		int val1 = Color.blue.getRed();
		Color myColor = new Color ( val1, val2, val3, alpha);
		g.setColor(myColor);
		g.fillRect(currentX,currentY,30,65);
		
		currentX+=40;
		g.fillRect(currentX,currentY,30,65);
		
		
	}
	public static void main(String args[]) {
		GuitarNotes s= new GuitarNotes();
		s.paint(null);
	}
}

