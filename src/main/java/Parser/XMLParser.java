package Parser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;


public class XMLParser {

	public static String instrument = "";
	public static ArrayList<Integer> nNPM = new ArrayList<Integer>();
	public int numOfmeasures = 0;

	public NodeList out = null;
	public Document document = null;

	public static ArrayList<String> details = new ArrayList<String>();
	public ArrayList<String> repeatNum = new ArrayList<String>();
	public ArrayList<String> repeatDirection = new ArrayList<String>();
	public ArrayList<String> repN= new ArrayList<String>();
	public ArrayList<String> repD = new ArrayList<String>();
	int count=0;
	int count2=0;
	int numMeasures=0;




	public void getXml(Document doc) {

		nNPM = new ArrayList<Integer>();
		details = new ArrayList<String>();
		repeatNum = new ArrayList<String>();
		repeatDirection = new ArrayList<String>();
		repN= new ArrayList<String>();
		repD = new ArrayList<String>();
		boolean hasRepeat = false;

		details = new ArrayList<>();
		doc.getDocumentElement().normalize();


		NodeList partNameList  =  doc.getElementsByTagName("part-name");
		NodeList repeats  =  doc.getElementsByTagName("repeat");

		String partName = "";

		for (int j=0;j<repeats.getLength();j++) {

			Element repeat = (Element) repeats.item(j);    
			String numRep = repeat.getAttribute("times");
			String dirRep = repeat.getAttribute("direction");

			repN.add(numRep);
			repD.add(dirRep);
		}

		for(int i = 0; i < partNameList.getLength(); i++) {


			Element child = (Element) partNameList.item(i);    
			String  st1 = child.getTextContent();
			details.add("Part " + (i + 1)  + ": " + st1);

			if(i == 0) {

				partName = st1;
			}

		}


		NodeList measures = doc.getElementsByTagName("measure"); 
		details.add("Amount of Measures is: " + measures.getLength());
		numOfmeasures  = measures.getLength();



		for(int i = 0; i < measures.getLength(); i++) {

			int temp2=0;

			hasRepeat = false;
			numMeasures=0;

			NodeList measure = (NodeList) measures.item(i); 



			Node mes =  (Node) measures.item(i);

			NodeList measures2 = (NodeList) mes;

			for (int k=0;k<measures2.getLength();k++) {



				if (measures2.item(k).getNodeName().equals("barline")) {

					hasRepeat = true;

				}

				if (measures2.item(k).getNodeName().equals("note")) {

					numMeasures++;

				}
			}

			if (hasRepeat) {



				if (repN.get(count).equals("")) {

					//					repeatNum.add(repN.get(count+1));
					//					repeatDirection.add(repD.get(count));
					//					count++;

					int temp=count;
					temp2=count;

					while(repD.get(temp).equals("forward")) {


						count2++;
						temp++;


					}

					while(repD.get(count).equals("forward")) {

						repeatNum.add(repN.get(temp2+count2));
						count++;



					}

					count=temp2;

					while(repD.get(count).equals("forward")) {


						repeatDirection.add(repD.get(count));
						count++;



					}




				}

				else {

					repeatNum.add(repN.get(count));
					repeatDirection.add(repD.get(count));
					count++;
				}
			}

			else {
				repeatNum.add("NAN");
				repeatDirection.add("NAN");


			}
			details.add( "Number of notes in measure " + (i+1) + ": "+ numMeasures);
			nNPM.add(numMeasures);


		}

		details.add("Repeat number is: " +repeatNum + "");
		details.add("Repeat direction is: " +repeatDirection + "");



		if(partName.equals("Guitar")) {
			GuitarParser guitarParser = new GuitarParser();
			instrument = "Guitar";
			guitarParser.parseGuitar(details, measures, nNPM, doc);

		}

		else if (partName.equals("Drumset")) {
			DrumParser drumParser = new DrumParser();
			instrument = "Drumset";
			drumParser.parseDrums(details, measures, nNPM, doc);
		}

		out = measures;
		document = doc;

	}

	public void loadXMLFromString(String xml) throws Exception
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		getXml( builder.parse(is) );


	}





}
