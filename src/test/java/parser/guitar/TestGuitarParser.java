package parser.guitar;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Parser.GuitarParser;
import Parser.XMLParser;

class TestGuitarParser {

	public String getGuitarXML() {

		String parse  = """
				<score-partwise version="3.1">
				<identification>
				<creator type="composer"></creator>
				</identification>
				<part-list>
				<score-part id="P1">
				<part-name>Guitar</part-name>
				</score-part>
				</part-list>
				<part id="P1">
				<measure number="1">
				<attributes>
				<divisions>16</divisions>
				<key>
				<fifths>0</fifths>
				</key>
				<clef>
				<sign>TAB</sign>
				<line>5</line>
				</clef>
				<staff-details>
				<staff-lines>6</staff-lines>
				<staff-tuning line="1">
				<tuning-step>E</tuning-step>
				<tuning-octave>2</tuning-octave>
				</staff-tuning>
				<staff-tuning line="2">
				<tuning-step>A</tuning-step>
				<tuning-octave>2</tuning-octave>
				</staff-tuning>
				<staff-tuning line="3">
				<tuning-step>D</tuning-step>
				<tuning-octave>3</tuning-octave>
				</staff-tuning>
				<staff-tuning line="4">
				<tuning-step>G</tuning-step>
				<tuning-octave>3</tuning-octave>
				</staff-tuning>
				<staff-tuning line="5">
				<tuning-step>B</tuning-step>
				<tuning-octave>3</tuning-octave>
				</staff-tuning>
				<staff-tuning line="6">
				<tuning-step>E</tuning-step>
				<tuning-octave>4</tuning-octave>
				</staff-tuning>
				</staff-details>
				</attributes>
				<note>
				<pitch>
				<step>E</step>
				<octave>2</octave>
				</pitch>
				<duration>8</duration>
				<voice>1</voice>
				<type>eighth</type>
				<notations>
				<technical>
				<string>6</string>
				<fret>0</fret>
				</technical>
				</notations>
				</note>
				<note>
				<pitch>
				<step>B</step>
				<octave>2</octave>
				</pitch>
				<duration>8</duration>
				<voice>1</voice>
				<type>eighth</type>
				<notations>
				<technical>
				<string>5</string>
				<fret>2</fret>
				</technical>
				</notations>
				</note>
				<note>
				<pitch>
				<step>E</step>
				<octave>3</octave>
				</pitch>
				<duration>8</duration>
				<voice>1</voice>
				<type>eighth</type>
				<notations>
				<technical>
				<string>4</string>
				<fret>2</fret>
				</technical>
				</notations>
				</note>
				<note>
				<pitch>
				<step>G</step>
				<alter>1</alter>
				<octave>3</octave>
				</pitch>
				<duration>8</duration>
				<voice>1</voice>
				<type>eighth</type>
				<notations>
				<technical>
				<string>3</string>
				<fret>1</fret>
				</technical>
				</notations>
				</note>
				<note>
				<pitch>
				<step>B</step>
				<octave>3</octave>
				</pitch>
				<duration>8</duration>
				<voice>1</voice>
				<type>eighth</type>
				<notations>
				<technical>
				<string>2</string>
				<fret>0</fret>
				</technical>
				</notations>
				</note>
				<note>
				<pitch>
				<step>E</step>
				<octave>4</octave>
				</pitch>
				<duration>8</duration>
				<voice>1</voice>
				<type>eighth</type>
				<notations>
				<technical>
				<string>1</string>
				<fret>0</fret>
				</technical>
				</notations>
				</note>
				<note>
				<pitch>
				<step>B</step>
				<octave>3</octave>
				</pitch>
				<duration>8</duration>
				<voice>1</voice>
				<type>eighth</type>
				<notations>
				<technical>
				<string>2</string>
				<fret>0</fret>
				</technical>
				</notations>
				</note>
				<note>
				<pitch>
				<step>G</step>
				<alter>1</alter>
				<octave>3</octave>
				</pitch>
				<duration>8</duration>
				<voice>1</voice>
				<type>eighth</type>
				<notations>
				<technical>
				<string>3</string>
				<fret>1</fret>
				</technical>
				</notations>
				</note>
				</measure>
				<measure number="2">
				<attributes>
				<divisions>16</divisions>
				<key>
				<fifths>0</fifths>
				</key>
				</attributes>
				<note>
				<pitch>
				<step>E</step>
				<octave>4</octave>
				</pitch>
				<duration>64</duration>
				<voice>1</voice>
				<type>whole</type>
				<notations>
				<technical>
				<string>1</string>
				<fret>0</fret>
				</technical>
				</notations>
				</note>
				<note>
				<chord/>
				<pitch>
				<step>B</step>
				<octave>3</octave>
				</pitch>
				<duration>64</duration>
				<voice>1</voice>
				<type>whole</type>
				<notations>
				<technical>
				<string>2</string>
				<fret>0</fret>
				</technical>
				</notations>
				</note>
				<note>
				<chord/>
				<pitch>
				<step>G</step>
				<alter>1</alter>
				<octave>3</octave>
				</pitch>
				<duration>64</duration>
				<voice>1</voice>
				<type>whole</type>
				<notations>
				<technical>
				<string>3</string>
				<fret>1</fret>
				</technical>
				</notations>
				</note>
				<note>
				<chord/>
				<pitch>
				<step>E</step>
				<octave>3</octave>
				</pitch>
				<duration>64</duration>
				<voice>1</voice>
				<type>whole</type>
				<notations>
				<technical>
				<string>4</string>
				<fret>2</fret>
				</technical>
				</notations>
				</note>
				<note>
				<chord/>
				<pitch>
				<step>B</step>
				<octave>2</octave>
				</pitch>
				<duration>64</duration>
				<voice>1</voice>
				<type>whole</type>
				<notations>
				<technical>
				<string>5</string>
				<fret>2</fret>
				</technical>
				</notations>
				</note>
				<note>
				<chord/>
				<pitch>
				<step>E</step>
				<octave>2</octave>
				</pitch>
				<duration>64</duration>
				<voice>1</voice>
				<type>whole</type>
				<notations>
				<technical>
				<string>6</string>
				<fret>0</fret>
				</technical>
				</notations>
				</note>
				</measure>
				</part>
				</score-partwise>



				""";

		return parse;

	}
	
	public String getXML2() {
		
		String parse = """
				
				<score-partwise version="3.1">
  <identification>
    <creator type="composer"></creator>
  </identification>
  <part-list>
    <score-part id="P1">
      <part-name>Guitar</part-name>
    </score-part>
  </part-list>
  <part id="P1">
    <measure number="1">
      <attributes>
        <divisions>4</divisions>
        <key>
          <fifths>0</fifths>
        </key>
        <clef>
          <sign>TAB</sign>
          <line>5</line>
        </clef>
        <staff-details>
          <staff-lines>6</staff-lines>
          <staff-tuning line="1">
            <tuning-step>E</tuning-step>
            <tuning-octave>2</tuning-octave>
          </staff-tuning>
          <staff-tuning line="2">
            <tuning-step>A</tuning-step>
            <tuning-octave>2</tuning-octave>
          </staff-tuning>
          <staff-tuning line="3">
            <tuning-step>D</tuning-step>
            <tuning-octave>3</tuning-octave>
          </staff-tuning>
          <staff-tuning line="4">
            <tuning-step>G</tuning-step>
            <tuning-octave>3</tuning-octave>
          </staff-tuning>
          <staff-tuning line="5">
            <tuning-step>B</tuning-step>
            <tuning-octave>3</tuning-octave>
          </staff-tuning>
          <staff-tuning line="6">
            <tuning-step>E</tuning-step>
            <tuning-octave>4</tuning-octave>
          </staff-tuning>
        </staff-details>
      </attributes>
      <note>
        <pitch>
          <step>A</step>
          <octave>4</octave>
        </pitch>
        <duration>8</duration>
        <voice>1</voice>
        <type>half</type>
        <notations>
          <technical>
            <string>1</string>
            <fret>5</fret>
          </technical>
        </notations>
      </note>
      <note>
        <pitch>
          <step>F</step>
          <alter>1</alter>
          <octave>4</octave>
        </pitch>
        <duration>8</duration>
        <voice>1</voice>
        <type>half</type>
        <notations>
          <technical>
            <string>2</string>
            <fret>7</fret>
          </technical>
        </notations>
      </note>
      <note>
        <pitch>
          <step>C</step>
          <alter>1</alter>
          <octave>4</octave>
        </pitch>
        <duration>8</duration>
        <voice>1</voice>
        <type>half</type>
        <notations>
          <technical>
            <string>3</string>
            <fret>6</fret>
          </technical>
        </notations>
      </note>
    </measure>
  </part>
</score-partwise>

				
				""";
		
		return parse;
		
		
	}

	@Test
	public void testStrings() {

		String parse = getGuitarXML();


		XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
//		GuitarParser guitar = new GuitarParser();
//		guitar.parseGuitar(xmlParser.getDetails(),xmlParser.out, xmlParser.getnNpm(), xmlParser.document);
		
		GuitarParser guitar = new GuitarParser();
		guitar.parseGuitar(XMLParser.details,xmlParser.out, XMLParser.nNPM, xmlParser.document);
		
		assertEquals("6",guitar.stringList.get(0));
		assertEquals("5",guitar.stringList.get(1));
		assertEquals("4",guitar.stringList.get(2));
		assertEquals("3",guitar.stringList.get(3));
		assertEquals("2",guitar.stringList.get(4));
		assertEquals("1",guitar.stringList.get(5));
		assertEquals("2",guitar.stringList.get(6));
		assertEquals("3",guitar.stringList.get(7));
		assertEquals("1",guitar.stringList.get(8));
		assertEquals("2",guitar.stringList.get(9));
		assertEquals("3",guitar.stringList.get(10));
		assertEquals("4",guitar.stringList.get(11));
		assertEquals("5",guitar.stringList.get(12));
		assertEquals("6",guitar.stringList.get(13));
		
		
		
	}
	
	@Test
	public void testFrets() {

		String parse = getGuitarXML();


		XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		GuitarParser guitar = new GuitarParser();
		guitar.parseGuitar(xmlParser.details,xmlParser.out, xmlParser.nNPM, xmlParser.document);
		
		assertEquals("0",guitar.fretList.get(0));
		assertEquals("2",guitar.fretList.get(1));
		assertEquals("2",guitar.fretList.get(2));
		assertEquals("1",guitar.fretList.get(3));
		assertEquals("0",guitar.fretList.get(4));
		assertEquals("0",guitar.fretList.get(5));
		assertEquals("0",guitar.fretList.get(6));
		assertEquals("1",guitar.fretList.get(7));
		assertEquals("0",guitar.fretList.get(8));
		assertEquals("0",guitar.fretList.get(9));
		assertEquals("1",guitar.fretList.get(10));
		assertEquals("2",guitar.fretList.get(11));
		assertEquals("2",guitar.fretList.get(12));
		assertEquals("0",guitar.fretList.get(13));
		
		
	}
	
	
	@Test
	public void testNotes() {

		String parse = getGuitarXML();


		XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		GuitarParser guitar = new GuitarParser();
		guitar.parseGuitar(xmlParser.details,xmlParser.out, xmlParser.nNPM, xmlParser.document);
		
		assertEquals("E2I",guitar.notesList.get(0));
		assertEquals("B2I",guitar.notesList.get(1));
		assertEquals("E3I",guitar.notesList.get(2));
		assertEquals("G#3I",guitar.notesList.get(3));
		assertEquals("B3I",guitar.notesList.get(4));
		assertEquals("E4I",guitar.notesList.get(5));
		assertEquals("B3I",guitar.notesList.get(6));
		assertEquals("G#3I",guitar.notesList.get(7));
		assertEquals("E4W",guitar.notesList.get(8));
		assertEquals("B3W",guitar.notesList.get(9));
		assertEquals("G#3W",guitar.notesList.get(10));
		assertEquals("E3W",guitar.notesList.get(11));
		assertEquals("B2W",guitar.notesList.get(12));
		assertEquals("E2W",guitar.notesList.get(13));
	}
	
	@Test
	public void testNotesLength() {

		String parse = getGuitarXML();


		XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		GuitarParser guitar = new GuitarParser();
		guitar.parseGuitar(xmlParser.details,xmlParser.out, xmlParser.nNPM, xmlParser.document);
		
		assertEquals("eighth",guitar.noteLengthList.get(0));
		assertEquals("eighth",guitar.noteLengthList.get(1));
		assertEquals("eighth",guitar.noteLengthList.get(2));
		assertEquals("eighth",guitar.noteLengthList.get(3));
		assertEquals("eighth",guitar.noteLengthList.get(4));
		assertEquals("eighth",guitar.noteLengthList.get(5));
		assertEquals("eighth",guitar.noteLengthList.get(6));
		assertEquals("eighth",guitar.noteLengthList.get(7));
		assertEquals("whole",guitar.noteLengthList.get(8));
		assertEquals("whole",guitar.noteLengthList.get(9));
		assertEquals("whole",guitar.noteLengthList.get(10));
		assertEquals("whole",guitar.noteLengthList.get(11));
		assertEquals("whole",guitar.noteLengthList.get(12));
		assertEquals("whole",guitar.noteLengthList.get(13));
		
	}
	
	@Test
	public void testchords() {

		String parse = getGuitarXML();


		XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		GuitarParser guitar = new GuitarParser();
		guitar.parseGuitar(xmlParser.details,xmlParser.out, xmlParser.nNPM, xmlParser.document);
		
	    assertEquals(1,guitar.chordList.get(0));
	    assertEquals(1,guitar.chordList.get(1));
	    assertEquals(1,guitar.chordList.get(2));
	    assertEquals(1,guitar.chordList.get(3));
	    assertEquals(1,guitar.chordList.get(4));
	    assertEquals(1,guitar.chordList.get(5));
	    assertEquals(1,guitar.chordList.get(6));
	    assertEquals(1,guitar.chordList.get(7));
	    assertEquals(1,guitar.chordList.get(8));
	    assertEquals(0,guitar.chordList.get(9));
	    assertEquals(0,guitar.chordList.get(10));
	    assertEquals(0,guitar.chordList.get(11));
	    assertEquals(0,guitar.chordList.get(12));
	    assertEquals(0,guitar.chordList.get(13));
		
	}
	
	@Test
	public void testStrings2() {

		String parse = getXML2();


		XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		GuitarParser guitar = new GuitarParser();
		guitar.parseGuitar(xmlParser.details,xmlParser.out, xmlParser.nNPM, xmlParser.document);
		
	  assertEquals("1",guitar.stringList.get(0));
	  assertEquals("2",guitar.stringList.get(1));
	  assertEquals("3",guitar.stringList.get(2));
		
	}
	
	@Test
	public void testFrets2() {

		String parse = getXML2();


	XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		GuitarParser guitar = new GuitarParser();
		guitar.parseGuitar(XMLParser.details,xmlParser.out, XMLParser.nNPM, xmlParser.document);
		
		
		
		assertEquals("5",guitar.fretList.get(0));
		assertEquals("7",guitar.fretList.get(1));
		assertEquals("6",guitar.fretList.get(2));
	}
	
	@Test
	public void testNotes2() {

		String parse = getXML2();


		XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		GuitarParser guitar = new GuitarParser();
		guitar.parseGuitar(xmlParser.details,xmlParser.out, xmlParser.nNPM, xmlParser.document);
		
		assertEquals("A4H",guitar.notesList.get(0));
		assertEquals("F#4H",guitar.notesList.get(1));
		assertEquals("C#4H",guitar.notesList.get(2));
		
	}
	
	@Test
	public void testNotesLength2() {

		String parse = getXML2();


		XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		GuitarParser guitar = new GuitarParser();
		guitar.parseGuitar(xmlParser.details,xmlParser.out, xmlParser.nNPM, xmlParser.document);
		
		assertEquals("half",guitar.noteLengthList.get(0));
		assertEquals("half",guitar.noteLengthList.get(1));
		assertEquals("half",guitar.noteLengthList.get(2));
		
		
	}
	
	@Test
	public void testchords2() {

		String parse = getXML2();


		XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		GuitarParser guitar = new GuitarParser();
		guitar.parseGuitar(xmlParser.details,xmlParser.out, xmlParser.nNPM, xmlParser.document);
		
	    assertEquals(1,guitar.chordList.get(0));
	    assertEquals(1,guitar.chordList.get(1));
	    assertEquals(1,guitar.chordList.get(2));

		
	}




}
