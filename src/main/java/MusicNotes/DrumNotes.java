package  MusicNotes;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class DrumNotes {
	 public static int x=80;
	 public static int y=268;
	 public static ArrayList<String> notes;
	
	 public static void SimpleBlackNote(Graphics g) {
		 Graphics2D g2 = (Graphics2D) g;
		    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);

		    Font font = new Font("Bravura", Font.PLAIN, 60);
		   
		    g2.setFont(font);
		 g2.drawString("\u266A", x, y);
		 if(x<880) {
			 x+=50;
		 }
		 else
		 {
			 x=80;
			 y+=100;
		 }
		 
		 
	 }
	 public static void Gclef(Graphics g) {
		 Graphics2D g2 = (Graphics2D) g;
		    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);

		    Font font = new Font("Bravura", Font.PLAIN, 60);
		   
		    g2.setFont(font);
		    g2.drawString("\uD834\uDD1E", x, y);// Gclef
		 if(x<880) {
			 x+=50;
		 }
		 else
		 {
			 x=80;
			 y+=100;
		 }
		 
		 
	 }
	 public static void Fclef(Graphics g) {
		 Graphics2D g2 = (Graphics2D) g;
		    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);

		    Font font = new Font("Bravura", Font.PLAIN, 60);
		   
		    g2.setFont(font);
		    g2.drawString("\uD834\uDD22", x, y);// Fclef
		 if(x<880) {
			 x+=50;
		 }
		 else
		 {
			 x=80;
			 y+=100;
		 }
		 
		 
	 }
	 
	public static void main(String[] args) {
	DrumNotes mn=new DrumNotes();
		
	JFrame f = new JFrame() {

	public void paint(Graphics g) {
		  
		  
		  //
	    Graphics2D g2 = (Graphics2D) g;
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	        RenderingHints.VALUE_ANTIALIAS_ON);

	    Font font = new Font("Bravura", Font.PLAIN, 60);
	   
	    g2.setFont(font);
//	    g2.drawString("\uD834\uDD1E", 40, 268);// Gclef
//
//	    g2.drawString("\u266A", 40, 470);
//	    g2.drawString("\uD834\uDD61", 40,570);
//	    
//	    g2.drawString("\uD834\uDD22", 40, 370);// Fclef
	    //g2.drawString("\u266A", 40, 470);
	    DrumNotes.SimpleBlackNote(g);
	    DrumNotes.SimpleBlackNote(g);
	    DrumNotes.Gclef(g);
	    DrumNotes.Fclef(g);
	    
	    // Vertical lines
		g2.drawLine(0,268,960,268);
		g2.drawLine(0,468,960,468);
		g2.drawLine(0,568,960,568);
	 
		 
//		 Graphics2D g3 = (Graphics2D) g;
//		    g3.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//		        RenderingHints.VALUE_ANTIALIAS_ON);
//
//		    Font font1 = new Font("Bravura", Font.PLAIN, 60);
//		    g3.setFont(font1);
//		    g3.drawString("\uD834\uDD22", 40, 370);// Fclef
//		    
//		    g3.drawLine(0,368,960,368);
	}
	 
	};
	SwingUtilities.updateComponentTreeUI(f);
	f.setSize(960,960);
	f.setVisible(true);
	
	
	}
	
	public static void getNotes(ArrayList<String> RecievedNotes) {
		
		notes = RecievedNotes;
		main(null);
	}
	}
