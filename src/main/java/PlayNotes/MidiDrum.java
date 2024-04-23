package PlayNotes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import models.part_list.MIDIInstrument;

public class MidiDrum {
	
	private ArrayList<String> notesList = new ArrayList<>();
	private ArrayList<Integer> chordList = new ArrayList<>();
	private ArrayList<String> noteHeadList = new ArrayList<>();
	private ArrayList<Integer> noteLengthList = new ArrayList<>();
	private ArrayList<String> stemList = new ArrayList<>();
	private ArrayList<String> noteInstrumentIDList = new ArrayList<>();

	private List<String> notes = Arrays.asList("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B");
	private MidiChannel[] channels;
	private MidiChannel midiChannel;  // An interface to the actual Midi system.
	private int INSTRUMENT = 10; // 0 is a piano, 9 is percussion, 25 is guitar, other channels are for other instruments
	private int VOLUME = 79; // between 0 & 127
	
	
	
	//Receive notes from Drum Parser
	public void getNotes(ArrayList<String> notesRecieved, ArrayList<Integer> chordsRecieved, ArrayList<String> noteHeadsRecieved, ArrayList<Integer> noteLengthRecieved, ArrayList<String> stemRecieved, ArrayList<String> noteInstrumIDRecieved) {
		
		notesList = notesRecieved;
		chordList = chordsRecieved;
		noteHeadList = noteHeadsRecieved;
		noteLengthList = noteLengthRecieved;
		stemList = stemRecieved;
		noteInstrumentIDList = noteInstrumIDRecieved;
		
	}
	
	public void playNotes() {
		
		try {
			// * Open a synthesizer
			Synthesizer synth = MidiSystem.getSynthesizer();
			synth.open();
			MidiChannel[] allChannels = synth.getChannels();
			midiChannel = allChannels[0];
			midiChannel.programChange(40);
			channels = synth.getChannels();
		
			// * Play some notes
//			play("6D",  1000);
//			rest(500);
//			
//			play("6D",  300);
//			play("6C#", 300);
//			play("6D",  1000);
//			rest(500);
//			
//			play("6D",  300);
//			play("6C#", 300);
//			play("6D",  1000);
//			play("6E",  300);
//			play("6E",  600);
//			play("6G",  300);
//			play("6G",  600);
//			rest(500);
			
			for(int i = 0; i < notesList.size(); i++) {
				play(notesList.get(i), getDuration(noteLengthList.get(i)));
			}
			
			
			// * finish up
			synth.close();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * Calculates the MIDI duration based on the length of the note
	 */
	private int getDuration(int noteDuration){
		
		/*
		 * BPM/Tempo = 60
		 * 1  -> 64 ->  4000 ms
		 * 1/2  -> 32 -> 2000 ms
		 * 1/4 -> 16 -> 1000 ms	        
		 * 1/8 -> 8 -> 500 ms	
		 * 1/16 -> 4 -> 250 ms	
		 * 1//32 -> 2 -> 125 ms	
		 * 1/64  -> 1 -> 63 ms	
		 * 1/128 -> 1/2 -> 31 ms
		 * 1/256 -> 1/4 -> 15 ms
		 * 1/512  -> 1/8 -> 8 ms
		 * 1/1024 -> 1/16 - > 4 ms
		 */
		return (int) (noteDuration * 62.5);
			
	}
	
	
//	/**
//	 * Plays the given note for the given duration
//	 */
//	private void play(String note, int duration) throws InterruptedException{
//			// * start playing a note
//			channels[INSTRUMENT].noteOn(id(note), VOLUME );
//			// * wait
//			Thread.sleep( duration );
//			// * stop playing a note
//			channels[INSTRUMENT].noteOff(id(note));
//	}
	
	
	/**
	 * Plays the given note for the given duration
	 */
	private void play(String note, int duration) throws InterruptedException{
				
			midiChannel.noteOn( id(notesList.get(0)), VOLUME);
			Thread.sleep(duration);
			midiChannel.noteOff(id(notesList.get(0)));
	}
	
	
	/**
	 * Plays nothing for the given duration
	 */
	private void rest(int duration) throws InterruptedException{
		Thread.sleep(duration);
	}
	
	/**
	 * Returns the MIDI id for a given note: eg. 4C -> 60
	 * @return
	 */
	private int id(String note){
		int octave = Integer.parseInt(note.substring(0, 1));
		return notes.indexOf(note.substring(1)) + 12 * octave + 12;	
	}
	
//	    public static void main(String[] args) { 
//	      try{
//	        /* Create a new Sythesizer and open it. Most of 
//	         * the methods you will want to use to expand on this 
//	         * example can be found in the Java documentation here: 
//	         * https://docs.oracle.com/javase/7/docs/api/javax/sound/midi/Synthesizer.html
//	         */
//	        Synthesizer midiSynth = MidiSystem.getSynthesizer(); 
//	        midiSynth.open();
//	    
//	        //get and load default instrument and channel lists
//	        Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
//	        MidiChannel[] mChannels = midiSynth.getChannels();
//	        
//	        midiSynth.loadInstrument(instr[0]);//load an instrument
//	    
//	    
//	        mChannels[0].noteOn(60, 100);//On channel 0, play note number 60 with velocity 100 
//	        try { Thread.sleep(1000); // wait time in milliseconds to control duration
//	        } catch( InterruptedException e ) {
//	            e.printStackTrace();
//	        }
//	        mChannels[0].noteOff(60);//turn of the note
//	    
//	    
//	      } catch (MidiUnavailableException e) {
//	         e.printStackTrace();
//	      }
//	   }
	    public static void main(String[] args) { 
	      try{
	        /* Create a new Sythesizer and open it. Most of 
	         * the methods you will want to use to expand on this 
	         * example can be found in the Java documentation here: 
	         * https://docs.oracle.com/javase/7/docs/api/javax/sound/midi/Synthesizer.html
	         */
	        Synthesizer midiSynth = MidiSystem.getSynthesizer(); 
	        midiSynth.open();
	    
	        //get and load default instrument and channel lists
	        Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
	        MidiChannel[] mChannels = midiSynth.getChannels();
	        
	        midiSynth.loadInstrument(instr[9]);//load an instrument
	    
	    
	        mChannels[0].noteOn(60, 100);//On channel 0, play note number 60 with velocity 100 
	        try { Thread.sleep(1000); // wait time in milliseconds to control duration
	        } catch( InterruptedException e ) {
	            e.printStackTrace();
	        }
	        mChannels[0].noteOff(60);//turn of the note
	        

	    
	      } catch (MidiUnavailableException e) {
	         e.printStackTrace();
	      }
	   }

}