package PlayNotes;
import java.util.ArrayList;

import org.jfugue.player.Player;

@SuppressWarnings("unused")
public class JfugueForDrum {

	ArrayList<String> instruments = new ArrayList<>();
	ArrayList<String> instrumentsID = new ArrayList<>();
	
	private ArrayList<String> notesList = new ArrayList<>();
	private ArrayList<Integer> chordList = new ArrayList<>();
	private ArrayList<String> noteHeadList = new ArrayList<>();
	private ArrayList<Integer> noteLengthList = new ArrayList<>();
	private ArrayList<String> stemList = new ArrayList<>();
	private ArrayList<Integer> nNPM = new ArrayList<Integer>();
	private ArrayList<String> noteInstrumentIDList = new ArrayList<>();
	
	private  int nNPMCounter = 0;
	public String total = "";
	public static String tempo = "120";

	//Receive notes from Drum Parser
	public void getNotes(ArrayList<String> notesRecieved, ArrayList<Integer> chordsRecieved, ArrayList<String> noteHeadsRecieved, ArrayList<Integer> noteLengthRecieved, ArrayList<String> stemRecieved, ArrayList<String> noteInstrumIDRecieved, ArrayList<Integer> nNPMrecieved) {
		notesList = notesRecieved;
		chordList = chordsRecieved;
		noteHeadList = noteHeadsRecieved;
		noteLengthList = noteLengthRecieved;
		stemList = stemRecieved;
		nNPM = nNPMrecieved;
		noteInstrumentIDList = noteInstrumIDRecieved;
		
		getIntruments();
	}
	
	public void getIntruments() {
		
		instruments.add("HIGH_FLOOR_TOM");
		instrumentsID.add("P1-I44");
		
		instruments.add("RIDE_BELL");
		instrumentsID.add("P1-I54");

		instruments.add("RIDE_CYMBAL_1");
		instrumentsID.add("P1-I52");
		
		instruments.add("LO_TOM");
		instrumentsID.add("P1-I46");
		
		instruments.add("BASS_DRUM");
		instrumentsID.add("P1-I36");
		
		instruments.add("OPEN_HI_HAT");
		instrumentsID.add("P1-I47");
		
		instruments.add("CHINESE_CYMBAL");
		instrumentsID.add("P1-I53");
		
		instruments.add("PEDAL_HI_HAT");
		instrumentsID.add("P1-I45");
		
		instruments.add("LO_FLOOR_TOM");
		instrumentsID.add("P1-I42");
		
		instruments.add("LO_MID_TOM");
		instrumentsID.add("P1-I48");
		
		instruments.add("CRASH_CYMBAL_1");
		instrumentsID.add("P1-I50");
		
		instruments.add("CLOSED_HI_HAT");
		instrumentsID.add("P1-I43");
		
		instruments.add("ACOUSTIC_SNARE");
		instrumentsID.add("P1-I39");
		
	}
	
	public void playNotes() {
		Player player = new Player();
		String str= "T" +tempo + " V9 ";
		total = str;
		
		for(int i = 0; i < notesList.size(); i++) {
			String noteInstrum = "";			
			for(int j = 0; j < instrumentsID.size(); j++) {
				
				if(noteInstrumentIDList.get(i).equals(instrumentsID.get(j))) {
					noteInstrum = instruments.get(j);
					total += "[" + noteInstrum + "]"; 
					break;
				}
				
			}
			
			if(noteLengthList.get(i).equals(64)) {
				total += "W";
			}

			else if(noteLengthList.get(i).equals(32)) {
				total += "H";
			}

			else if(noteLengthList.get(i).equals(16)) {
				total += "Q";
			}

			else if(noteLengthList.get(i).equals(8)) {
				total += "I";
			}

			else if(noteLengthList.get(i).equals(4)) {
				total += "S";
			}

			else if(noteLengthList.get(i).equals(2)) {
				total += "T";
			}

			else if(noteLengthList.get(i).equals(1)) {
				total += "X";
			}

			else if(noteLengthList.get(i).equals(1/2)) {
				total += "O";
			}
			
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
//		player.play(total);
		nNPMCounter = 0;
	}

	
}

