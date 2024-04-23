package playnotes.player;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Parser.GuitarParser;
import Parser.XMLParser;
import PlayNotes.JfugueForGuitar;

class TestguitarPlaying {
	
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
	void TestPlaying() {
		
		String parse = getGuitarXML();
		
		String test = "T120 V0 I[Guitar] | E2I B2I E3I G#3I B3I E4I B3I G#3I | E4W+B3W+G#3W+E3W+B2W+E2W ";


		XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		GuitarParser guitar = new GuitarParser();
		guitar.parseGuitar(xmlParser.details,xmlParser.out, xmlParser.nNPM, xmlParser.document);
		
		 // JfugueTest jfugueGuitar = new JfugueTest();
		//jfugueGuitar.getNotes(guitar.notesList, xmlParser.nNPM, guitar.stringList, guitar.fretList, guitar.chordList, guitar.alterList);
		guitar.jfugueTester.playNotes();
	
		
		assertEquals(test,guitar.jfugueTester.total);
		
		
	}
	
	@Test
	void TestPlaying2() {
		
		String parse = getXML2();
		
		String test = "T120 V0 I[Guitar] | A4H F#4H C#4H | ";


		XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
//		GuitarParser guitar = new GuitarParser();
//		guitar.parseGuitar(xmlParser.details,xmlParser.out, xmlParser.nNPM, xmlParser.document);
//		
//		JfugueTest jfugueGuitar = new JfugueTest();
//		guitar.jfugueGuitar.getNotes(guitar.notesList, xmlParser.nNPM, guitar.stringList, guitar.fretList, guitar.chordList, guitar.alterList);
//		jfugueGuitar.playNotes();
		
		GuitarParser guitar = new GuitarParser();
		guitar.parseGuitar(xmlParser.details,xmlParser.out, xmlParser.nNPM, xmlParser.document);
		
		 // JfugueTest jfugueGuitar = new JfugueTest();
		//jfugueGuitar.getNotes(guitar.notesList, xmlParser.nNPM, guitar.stringList, guitar.fretList, guitar.chordList, guitar.alterList);
	    guitar.jfugueTester.playNotes();
	
		
		assertEquals(test,guitar.jfugueTester.total);
		
		
	}

}
