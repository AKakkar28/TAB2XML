package Parser;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import GUI.PreviewSheetMusicController;
import PlayNotes.JfugueForGuitar;

public class GuitarParser {

	public ArrayList<String> notesList = new ArrayList<>();
	public ArrayList<String> alterList = new ArrayList<>();
	public ArrayList<Integer> chordList = new ArrayList<>();
	public ArrayList<String> fretList = new ArrayList<>();
	public ArrayList<String> stringList = new ArrayList<>();
	public ArrayList<String> noteLengthList = new ArrayList<>();

	public ArrayList<Integer> numOfSlur = new ArrayList<>();
	public ArrayList<String> slurNumber = new ArrayList<>();
	public ArrayList<String> slurType = new ArrayList<>();
	public ArrayList<String> slurPlacement = new ArrayList<>();
	public ArrayList<String> pullOffNumber = new ArrayList<>();
	public ArrayList<String> pullOffType = new ArrayList<>();
	public ArrayList<Integer> numberOfPullOff = new ArrayList<>();
	public ArrayList<String> actualNotesL= new ArrayList<>();
	public ArrayList<String> normalNotesL = new ArrayList<>();
	public ArrayList<String> bendsL = new ArrayList<>();
	public ArrayList<String> tiesL = new ArrayList<>();
	public ArrayList<Integer> NumOfTiesL = new ArrayList<>();

	public ArrayList<String> slursN = new ArrayList<>();
	public ArrayList<String> slursT = new ArrayList<>();
	public ArrayList<String> slursP = new ArrayList<>();
	public ArrayList<String> pullT = new ArrayList<>();
	public ArrayList<String> pullN = new ArrayList<>();
	public ArrayList<String> actNotes = new ArrayList<>();
	public ArrayList<String> nomNotes = new ArrayList<>();
	public ArrayList<String> bendExist = new ArrayList<>();
	public ArrayList<String> tieExist = new ArrayList<>();



	public static JfugueForGuitar jfugueTester = new JfugueForGuitar();
	int counter =0;
	int count2 = 0;
	int count3=0;
	int count4=0;
	int count5 =0;
	int trying;


	public void parseGuitar(ArrayList<String> details, NodeList measures, ArrayList<Integer> nNPM, Document doc) {
		notesList = new ArrayList<>();
		alterList = new ArrayList<>();
		chordList = new ArrayList<>();
		fretList = new ArrayList<>();
		stringList = new ArrayList<>();
		noteLengthList = new ArrayList<>();

		numOfSlur = new ArrayList<>();
		slurNumber = new ArrayList<>();
		slurType = new ArrayList<>();
		slurPlacement = new ArrayList<>();
		pullOffNumber = new ArrayList<>();
		pullOffType = new ArrayList<>();
		numberOfPullOff = new ArrayList<>();
		actualNotesL= new ArrayList<>();
		normalNotesL = new ArrayList<>();
		bendsL = new ArrayList<>();
		tiesL = new ArrayList<>();
		NumOfTiesL = new ArrayList<>();

		slursN = new ArrayList<>();
		slursT = new ArrayList<>();
		slursP = new ArrayList<>();
		pullT = new ArrayList<>();
		pullN = new ArrayList<>();
		actNotes = new ArrayList<>();
		nomNotes = new ArrayList<>();
		bendExist = new ArrayList<>();
		tieExist = new ArrayList<>();

		for(int i = 0; i < measures.getLength(); i++) {


			NodeList divisions  =  doc.getElementsByTagName("divisions");
			Element division = (Element) divisions.item(i);    
			String NOD = division.getTextContent();
			details.add("Number of divisions in measure " + (i + 1) + ": " + NOD);


			String NOF = "";

			NodeList fifths =  doc.getElementsByTagName("fifths");
			if(fifths.item(i) != null) {

				Element fifth = (Element) fifths.item(i);    
				NOF = fifth.getTextContent();
				details.add("Fifth of measure " + ( i+1) + ": " + NOF);
			}

			NodeList signs =  doc.getElementsByTagName("sign");
			String  NOS = "";

			if ( signs.item(i) != null ) {
				Element sign = (Element) signs.item(i);    
				NOS = sign.getTextContent();
				details.add("Sign: " + NOS);
			}


			NOD = "";
			NOF = "";
			NOS = "";
		}


		NodeList tuningSteps =  doc.getElementsByTagName("tuning-step");
		NodeList staffLines  =  doc.getElementsByTagName("staff-lines");
		Element staffLine = (Element) staffLines.item(0);    
		String  NOST = staffLine.getTextContent();

		
		details.add("*********************");
		details.add("Number of staff Lines" + ": " + NOST);
		details.add("Staff detals: ");

		for (int k = 0; k < tuningSteps.getLength(); k++) {

			if ( tuningSteps.item(k) != null) {

				Element tuningStep = (Element) tuningSteps.item(k);    
				String  NOTS = tuningStep.getTextContent();

				NodeList tuningOctaves =  doc.getElementsByTagName("tuning-octave");
				Element tuningOctave = (Element) tuningOctaves.item(k);    
				String  NOTO = tuningOctave.getTextContent();

				int x = k + 1;

				details.add("Line = " + x );
				details.add("tuning-step: " +  NOTS);
				details.add("tuning-octave: " +  NOTO);

			}

		}

		details.add("*********************");
	

		NodeList notes = doc.getElementsByTagName("note");
		details.add("Amount of notes is: " + notes.getLength());
	

		NodeList steps =  doc.getElementsByTagName("step");
		NodeList alters = doc.getElementsByTagName("alter");
		NodeList octaves = doc.getElementsByTagName("octave");
		NodeList durations = doc.getElementsByTagName("duration");
		NodeList voices = doc.getElementsByTagName("voice");
		NodeList types = doc.getElementsByTagName("type");
		NodeList strings= doc.getElementsByTagName("string");
		NodeList frets = doc.getElementsByTagName("fret");

		NodeList slurs = doc.getElementsByTagName("slur");
		NodeList pullOffs = doc.getElementsByTagName("pull-off");

		NodeList actualNotes = doc.getElementsByTagName("actual-notes");
		NodeList normalNotes = doc.getElementsByTagName("normal-notes");

		NodeList bends = doc.getElementsByTagName("bend-alter");
		NodeList ties = doc.getElementsByTagName("tied");


		String[] alterExistList = new String[alters.getLength()];
		int alterExistCounter = 0;

		for(int i = 0; i < alterExistList.length; i++) {

			Element alter = (Element) alters.item(i);    
			String alterValue= alter.getTextContent();
			alterExistList[i] = alterValue;
		}


		for(int j = 0; j < notes.getLength(); j++) {

			int noslurs =0;
			boolean hasSlurs = false;

			int numPullOff =0;
			boolean hasPullOff = false;

			boolean hasActualNotes = false;

			boolean hasBend = false;

			boolean hastie = false;
			int numTie = 0;
			
			boolean hasDuration = false;



			NodeList singleNote = (NodeList) notes.item(j);
			NodeList technical = (NodeList) singleNote.item(1); //1: Technical  3: Number of Notes 

			boolean hasChord = false;
			boolean hasAlter = false;
			

			String NOS = "";
			String POS = "";
			String TOS = "";




			for (int z =0;z<slurs.getLength();z++) {

				if (j == 0) {


					if (slurs.item(z) != null) {

						Element slur = (Element) slurs.item(z);    
						NOS = slur.getAttribute("number");
						TOS = slur.getAttribute("type");
						POS = slur.getAttribute("placement");
						details.add("slur number is : " + NOS);
						details.add("slur type is : " + TOS);
						details.add("slur placement is : " + POS);

						slursN.add(NOS);
						slursT.add(TOS);
						slursP.add(POS);

					}
				}

			}

			for (int x =0;x<pullOffs.getLength();x++) {

				if (j == 0) {


					if (pullOffs.item(x) != null) {

						Element pullOff = (Element) pullOffs.item(x);    
						String NOP = pullOff.getAttribute("number");
						String TOP = pullOff.getAttribute("type");
						details.add("pull-Off number is : " + NOP);
						details.add("slur type is : " + TOP);

						pullN.add(NOP);
						pullT.add(TOP);



					}
				}

			}

			for (int x =0;x<ties.getLength();x++) {

				if (j == 0) {


					if (ties.item(x) != null) {

						Element tie = (Element) ties.item(x);    
						String NOT = tie.getAttribute("type");
						tieExist.add(NOT);





					}
				}

			}


			for (int h =0;h<actualNotes.getLength();h++) {

				if (j == 0) {


					if (actualNotes.item(h) != null) {

						Element actualNote = (Element) actualNotes.item(h);    
						String NOA = actualNote.getTextContent();	
						actNotes.add(NOA);

					}


					if (normalNotes.item(h) != null) {

						Element normalNote = (Element) normalNotes.item(h);    
						String NON = normalNote.getTextContent();	
						nomNotes.add(NON);


					}
				}

			}

			for (int f =0;f<bends.getLength();f++) {

				if (j == 0) {


					if (bends.item(f) != null) {

						Element bend = (Element) bends.item(f);    
						String NOB = bend.getTextContent();
						bendExist.add(NOB);


					}
				}

			}



			//Checks if the current note has a chord or attribute 

			for(int k = 0; k < singleNote.getLength(); k++) {

				Node singleNoteElement = (Node) singleNote.item(k);

				if(singleNoteElement.getNodeName().equals("chord")) {
					hasChord = true;
				}


				if(singleNoteElement.getNodeName().equals("time-modification")) {

					hasActualNotes = true;


				}


				if(singleNoteElement.getNodeName().equals("notations")) {
					NodeList notation = (NodeList)singleNoteElement;
					for(int l=0; l<notation.getLength(); l++) {
						if(notation.item(l).getNodeName().equals("slur")) {
							hasSlurs = true;
							noslurs++;


						}

						else if (notation.item(l).getNodeName().equals("tied")) {

							hastie = true;
							numTie ++;


						}
						
						else if(notation.item(l).getNodeName().equals("duration")) {
							
							hasDuration=true;
							
						}
					}
				}

				if (singleNoteElement.getNodeName().equals("notations")) {
					NodeList notation = (NodeList)singleNoteElement;
					for (int l=0;l<notation.getLength();l++) {
						if(notation.item(l).getNodeName().equals("technical")) {
							NodeList tech = (NodeList) notation.item(l);
							for (int a=0;a<tech.getLength();a++) {
								if(tech.item(a).getNodeName().equals("pull-off")) {

									details.add("working");

									hasPullOff = true;
									numPullOff++;

								}

								else if (tech.item(a).getNodeName().equals("bend")) {


									hasBend = true;

								}


							}		

						}		
					}	

				}

			}	

			if(hasSlurs) {

				numOfSlur.add(noslurs);
				slurNumber.add(slursN.get(counter));
				slurType.add(slursT.get(counter));
				slurPlacement.add(slursP.get(counter));

				counter++;

				if (noslurs > 1) {

					for (int y=0;y<noslurs-1;y++) {

						slurNumber.add(slursN.get(counter));
						slurType.add(slursT.get(counter));
						slurPlacement.add(slursP.get(counter));

						counter++;



					}




				}




			}

			else {

				numOfSlur.add(0);
				slurNumber.add("NAN");
				slurType.add("NAN");
				slurPlacement.add("NAN");

			}


			if (hasPullOff) {

				numberOfPullOff.add(numPullOff);
				pullOffNumber.add(pullN.get(count2));
				pullOffType.add(pullT.get(count2));
				count2++;

				if (numPullOff > 1) {

					for (int y=0;y<numPullOff-1;y++) {	
						pullOffNumber.add(pullN.get(count2));
						pullOffType.add(pullT.get(count2));
						count2++;



					}
				}

			}

			else {

				pullOffNumber.add("0");
				pullOffType.add("NAN");	


			}

			if(hasActualNotes) {

				actualNotesL.add(actNotes.get(count3));
				normalNotesL.add(nomNotes.get(count3));
				count3++;



			}

			else {
				actualNotesL.add("NAN");
				normalNotesL.add("NAN");

			}


			if(hastie) {


				tiesL.add(tieExist.get(count5));
				NumOfTiesL.add(numTie);
				count5++;

				if (numTie > 1) {

					for (int y=0;y<noslurs-1;y++) {

						tiesL.add(tieExist.get(count5));
						count5++;

					}
				}




			}

			else {

				tiesL.add("NAN");
				NumOfTiesL.add(0);


			}

			if (hasBend) {

				bendsL.add(bendExist.get(count4));
				count4++;	
			}

			else {

				bendsL.add("0");
			}


			if(hasChord) {
				chordList.add(0);
			}

			else {
				chordList.add(1);
			}



			/*
			 * When cord exits move the technical section one below
			 * Technical shows the details of each note
			 */

			if(hasChord) { 

				technical = (NodeList) singleNote.item(3);

			}

			//Checks if the current note has a alter alter attribute (part of technical list)
			for(int k = 0; k < technical.getLength(); k++) {

				Node technicalElement = (Node) technical.item(k);

				if(technicalElement.getNodeName().equals("alter")) {
					hasAlter = true;
				}


			}

			if(hasAlter) {

				alterList.add(alterExistList[alterExistCounter]);
				alterExistCounter++;
			}

			else {

				alterList.add("Non");
			}


			String note = "";

			details.add("Note: " + (j+1));

			if(steps.item(j) != null) {

				Element step = (Element) steps.item(j);    
				String  stepValue= step.getTextContent();
				details.add("Step: " +  stepValue);
				note += stepValue;
			}

			if(hasAlter && alterList.get(j).equals("1")) {
				note += "#";
			}

			if(hasAlter && alterList.get(j).equals("-1")) {
				note += "b";
			}
			details.add("Alter: " +  alterList.get(j));

			if(octaves.item(j) != null) {

				Element octave = (Element) octaves.item(j);    
				String  octaveValue = octave.getTextContent();
				details.add("Octave: " +  octaveValue);
				note += octaveValue;
			}

			if(durations.item(j) != null) {

				Element duration = (Element) durations.item(j);    
				String  durationValue = duration.getTextContent();
				details.add("Duration: " +  durationValue);
			}

			if(voices.item(j) != null) {

				Element voice = (Element) voices.item(j);    
				String  voiceValue = voice.getTextContent();
				details.add("Voice: " +  voiceValue);

			}

			if(types.item(j) != null) {

				Element type = (Element) types.item(j);    
				String  typeValue = type.getTextContent();
				details.add("Type: " +  typeValue);
				noteLengthList.add(typeValue);


				/*Duration 		Character
				 * whole 	   		w
				 * half 				h
				 * quarter 			q
				 * eighth 			i
				 * sixteenth 		s
				 * thirty-second 	t
				 * sixty-fourth 		x
				 * one-twenty-eighth o
				 */

				if(typeValue.equals("whole")) {
					note += "W";
				}

				else if(typeValue.equals("half")) {
					note += "H";
				}

				else if(typeValue.equals("quarter")) {
					note += "Q";
				}

				else if(typeValue.equals("eighth")) {
					note += "I";
				}

				else if(typeValue.equals("16th")) {
					note += "S";
				}

				else if(typeValue.equals("32nd")) {
					note += "T";
				}

				else if(typeValue.equals("64th")) {
					note += "X";
				}

				else if(typeValue.equals("128th")) {
					note += "O";
				}

				else {
					note += "";
				}

			}

			if(strings.item(j) != null) {

				Element string = (Element) strings.item(j);    
				String  stringValue = string.getTextContent();
				details.add("String: " +  stringValue);
				stringList.add(stringValue);

			}

			if(frets.item(j) != null) {

				Element fret = (Element) frets.item(j);    
				String  fretValue = fret.getTextContent();
				details.add("Fret: " +  fretValue);
				fretList.add(fretValue);
			}



			notesList.add(note);
			details.add("--------------------");



		}

						details.add("Number of slur for notes: " + numOfSlur + "" );
						details.add("Slur value: " + slurNumber + "" );
						details.add("Slur type: " + slurType + "" );
						details.add("Slur placement: " + slurPlacement + "" );
						
				
						details.add("*********************");
				
						details.add("pull-off value: " + pullOffNumber + "" );
						details.add("pull-off type: " + pullOffType + "" );
				
					
						
						details.add("*********************");
		
						
						details.add("Actual Notes are: " + actualNotesL + "" );
						details.add("Normal notes are: " + normalNotesL + "" );


			
				details.add( "bends are: " + bendsL + "" );


		details.add( "Number of ties for note: " + NumOfTiesL + "" );
		details.add("Tie value" + tiesL + "" );

		for(int i = 0; i< nNPM.size(); i++) {
			if(i != 0) {
				nNPM.set(i, (nNPM.get(i) + nNPM.get(i-1)));
			}
		}


		jfugueTester.getNotes(notesList, nNPM, stringList, fretList, chordList, alterList);
		PreviewSheetMusicController.canvasNote.getNotesGuitar("Guitar",bendsL,stringList, fretList, nNPM, alterList, noteLengthList, chordList);

	}

}
