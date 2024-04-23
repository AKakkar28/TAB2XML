package parser.drums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Parser.DrumParser;
import Parser.GuitarParser;
import Parser.XMLParser;

class TestDrumParser {

	public String getXML() {
		
		String parse = """
				
				<score-partwise version="3.1">
  <identification>
    <creator type="composer"></creator>
  </identification>
  <part-list>
    <score-part id="P1">
      <part-name>Drumset</part-name>
      <score-instrument id="P1-I36">
        <instrument-name>Bass Drum 1</instrument-name>
      </score-instrument>
      <score-instrument id="P1-I39">
        <instrument-name>Snare</instrument-name>
      </score-instrument>
      <score-instrument id="P1-I52">
        <instrument-name>Ride Cymbal 1</instrument-name>
      </score-instrument>
      <score-instrument id="P1-I43">
        <instrument-name>Closed Hi-Hat</instrument-name>
      </score-instrument>
      <score-instrument id="P1-I50">
        <instrument-name>Crash Cymbal 1</instrument-name>
      </score-instrument>
      <score-instrument id="P1-I48">
        <instrument-name>Low-Mid Tom</instrument-name>
      </score-instrument>
      <score-instrument id="P1-I44">
        <instrument-name>High Floor Tom</instrument-name>
      </score-instrument>
      <score-instrument id="P1-I53">
        <instrument-name>Chinese Cymbal 1</instrument-name>
      </score-instrument>
      <score-instrument id="P1-I45">
        <instrument-name>Pedal Hi-Hat</instrument-name>
      </score-instrument>
      <score-instrument id="P1-I46">
        <instrument-name>Low Tom</instrument-name>
      </score-instrument>
      <score-instrument id="P1-I47">
        <instrument-name>Open Hi-Hat</instrument-name>
      </score-instrument>
      <score-instrument id="P1-I54">
        <instrument-name>Ride Bell</instrument-name>
      </score-instrument>
      <score-instrument id="P1-I42">
        <instrument-name>Low Floor Tom</instrument-name>
      </score-instrument>
      <midi-instrument id="P1-I36">
        <midi-channel>10</midi-channel>
        <midi-program>1</midi-program>
        <midi-unpitched>36</midi-unpitched>
        <volume>78.7402</volume>
        <pan>0</pan>
      </midi-instrument>
      <midi-instrument id="P1-I39">
        <midi-channel>10</midi-channel>
        <midi-program>1</midi-program>
        <midi-unpitched>39</midi-unpitched>
        <volume>78.7402</volume>
        <pan>0</pan>
      </midi-instrument>
      <midi-instrument id="P1-I52">
        <midi-channel>10</midi-channel>
        <midi-program>1</midi-program>
        <midi-unpitched>52</midi-unpitched>
        <volume>78.7402</volume>
        <pan>0</pan>
      </midi-instrument>
      <midi-instrument id="P1-I43">
        <midi-channel>10</midi-channel>
        <midi-program>1</midi-program>
        <midi-unpitched>43</midi-unpitched>
        <volume>78.7402</volume>
        <pan>0</pan>
      </midi-instrument>
      <midi-instrument id="P1-I50">
        <midi-channel>10</midi-channel>
        <midi-program>1</midi-program>
        <midi-unpitched>50</midi-unpitched>
        <volume>78.7402</volume>
        <pan>0</pan>
      </midi-instrument>
      <midi-instrument id="P1-I48">
        <midi-channel>10</midi-channel>
        <midi-program>1</midi-program>
        <midi-unpitched>48</midi-unpitched>
        <volume>78.7402</volume>
        <pan>0</pan>
      </midi-instrument>
      <midi-instrument id="P1-I44">
        <midi-channel>10</midi-channel>
        <midi-program>1</midi-program>
        <midi-unpitched>44</midi-unpitched>
        <volume>78.7402</volume>
        <pan>0</pan>
      </midi-instrument>
      <midi-instrument id="P1-I53">
        <midi-channel>10</midi-channel>
        <midi-program>1</midi-program>
        <midi-unpitched>53</midi-unpitched>
        <volume>78.7402</volume>
        <pan>0</pan>
      </midi-instrument>
      <midi-instrument id="P1-I45">
        <midi-channel>10</midi-channel>
        <midi-program>1</midi-program>
        <midi-unpitched>45</midi-unpitched>
        <volume>78.7402</volume>
        <pan>0</pan>
      </midi-instrument>
      <midi-instrument id="P1-I46">
        <midi-channel>10</midi-channel>
        <midi-program>1</midi-program>
        <midi-unpitched>46</midi-unpitched>
        <volume>78.7402</volume>
        <pan>0</pan>
      </midi-instrument>
      <midi-instrument id="P1-I47">
        <midi-channel>10</midi-channel>
        <midi-program>1</midi-program>
        <midi-unpitched>47</midi-unpitched>
        <volume>78.7402</volume>
        <pan>0</pan>
      </midi-instrument>
      <midi-instrument id="P1-I54">
        <midi-channel>10</midi-channel>
        <midi-program>1</midi-program>
        <midi-unpitched>54</midi-unpitched>
        <volume>78.7402</volume>
        <pan>0</pan>
      </midi-instrument>
      <midi-instrument id="P1-I42">
        <midi-channel>10</midi-channel>
        <midi-program>1</midi-program>
        <midi-unpitched>42</midi-unpitched>
        <volume>78.7402</volume>
        <pan>0</pan>
      </midi-instrument>
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
          <sign>percussion</sign>
          <line>2</line>
        </clef>
      </attributes>
      <note>
        <unpitched>
          <display-step>A</display-step>
          <display-octave>5</display-octave>
        </unpitched>
        <duration>8</duration>
        <instrument id="P1-I50"/>
        <voice>1</voice>
        <type>eighth</type>
        <stem>up</stem>
        <notehead>x</notehead>
      </note>
      <note>
        <chord/>
        <unpitched>
          <display-step>F</display-step>
          <display-octave>4</display-octave>
        </unpitched>
        <duration>8</duration>
        <instrument id="P1-I36"/>
        <voice>1</voice>
        <type>eighth</type>
        <stem>up</stem>
      </note>
      <note>
        <unpitched>
          <display-step>G</display-step>
          <display-octave>5</display-octave>
        </unpitched>
        <duration>8</duration>
        <instrument id="P1-I43"/>
        <voice>1</voice>
        <type>eighth</type>
        <stem>up</stem>
        <notehead>x</notehead>
      </note>
      <note>
        <unpitched>
          <display-step>G</display-step>
          <display-octave>5</display-octave>
        </unpitched>
        <duration>8</duration>
        <instrument id="P1-I43"/>
        <voice>1</voice>
        <type>eighth</type>
        <stem>up</stem>
        <notehead>x</notehead>
      </note>
      <note>
        <chord/>
        <unpitched>
          <display-step>C</display-step>
          <display-octave>5</display-octave>
        </unpitched>
        <duration>8</duration>
        <instrument id="P1-I39"/>
        <voice>1</voice>
        <type>eighth</type>
        <stem>up</stem>
      </note>
      <note>
        <unpitched>
          <display-step>G</display-step>
          <display-octave>5</display-octave>
        </unpitched>
        <duration>8</duration>
        <instrument id="P1-I43"/>
        <voice>1</voice>
        <type>eighth</type>
        <stem>up</stem>
        <notehead>x</notehead>
      </note>
      <note>
        <unpitched>
          <display-step>G</display-step>
          <display-octave>5</display-octave>
        </unpitched>
        <duration>8</duration>
        <instrument id="P1-I43"/>
        <voice>1</voice>
        <type>eighth</type>
        <stem>up</stem>
        <notehead>x</notehead>
      </note>
      <note>
        <chord/>
        <unpitched>
          <display-step>F</display-step>
          <display-octave>4</display-octave>
        </unpitched>
        <duration>8</duration>
        <instrument id="P1-I36"/>
        <voice>1</voice>
        <type>eighth</type>
        <stem>up</stem>
      </note>
      <note>
        <unpitched>
          <display-step>G</display-step>
          <display-octave>5</display-octave>
        </unpitched>
        <duration>8</duration>
        <instrument id="P1-I43"/>
        <voice>1</voice>
        <type>eighth</type>
        <stem>up</stem>
        <notehead>x</notehead>
      </note>
      <note>
        <unpitched>
          <display-step>G</display-step>
          <display-octave>5</display-octave>
        </unpitched>
        <duration>8</duration>
        <instrument id="P1-I43"/>
        <voice>1</voice>
        <type>eighth</type>
        <stem>up</stem>
        <notehead>x</notehead>
      </note>
      <note>
        <chord/>
        <unpitched>
          <display-step>C</display-step>
          <display-octave>5</display-octave>
        </unpitched>
        <duration>8</duration>
        <instrument id="P1-I39"/>
        <voice>1</voice>
        <type>eighth</type>
        <stem>up</stem>
      </note>
      <note>
        <unpitched>
          <display-step>G</display-step>
          <display-octave>5</display-octave>
        </unpitched>
        <duration>8</duration>
        <instrument id="P1-I43"/>
        <voice>1</voice>
        <type>eighth</type>
        <stem>up</stem>
        <notehead>x</notehead>
      </note>
    </measure>
    <measure number="2">
      <attributes>
        <divisions>16</divisions>
      </attributes>
      <note>
        <unpitched>
          <display-step>C</display-step>
          <display-octave>5</display-octave>
        </unpitched>
        <duration>4</duration>
        <instrument id="P1-I39"/>
        <voice>1</voice>
        <type>16th</type>
        <stem>up</stem>
      </note>
      <note>
        <chord/>
        <unpitched>
          <display-step>F</display-step>
          <display-octave>4</display-octave>
        </unpitched>
        <duration>4</duration>
        <instrument id="P1-I36"/>
        <voice>1</voice>
        <type>16th</type>
        <stem>up</stem>
      </note>
      <note>
        <unpitched>
          <display-step>C</display-step>
          <display-octave>5</display-octave>
        </unpitched>
        <duration>4</duration>
        <instrument id="P1-I39"/>
        <voice>1</voice>
        <type>16th</type>
        <stem>up</stem>
      </note>
      <note>
        <unpitched>
          <display-step>C</display-step>
          <display-octave>5</display-octave>
        </unpitched>
        <duration>4</duration>
        <instrument id="P1-I39"/>
        <voice>1</voice>
        <type>16th</type>
        <stem>up</stem>
      </note>
      <note>
        <unpitched>
          <display-step>C</display-step>
          <display-octave>5</display-octave>
        </unpitched>
        <duration>4</duration>
        <instrument id="P1-I39"/>
        <voice>1</voice>
        <type>16th</type>
        <stem>up</stem>
      </note>
      <note>
        <unpitched>
          <display-step>E</display-step>
          <display-octave>5</display-octave>
        </unpitched>
        <duration>4</duration>
        <instrument id="P1-I48"/>
        <voice>1</voice>
        <type>16th</type>
        <stem>up</stem>
      </note>
      <note>
        <unpitched>
          <display-step>E</display-step>
          <display-octave>5</display-octave>
        </unpitched>
        <duration>4</duration>
        <instrument id="P1-I48"/>
        <voice>1</voice>
        <type>16th</type>
        <stem>up</stem>
      </note>
      <note>
        <unpitched>
          <display-step>D</display-step>
          <display-octave>5</display-octave>
        </unpitched>
        <duration>4</duration>
        <instrument id="P1-I46"/>
        <voice>1</voice>
        <type>16th</type>
        <stem>up</stem>
      </note>
      <note>
        <unpitched>
          <display-step>D</display-step>
          <display-octave>5</display-octave>
        </unpitched>
        <duration>4</duration>
        <instrument id="P1-I46"/>
        <voice>1</voice>
        <type>16th</type>
        <stem>up</stem>
      </note>
      <note>
        <unpitched>
          <display-step>A</display-step>
          <display-octave>5</display-octave>
        </unpitched>
        <duration>32</duration>
        <instrument id="P1-I50"/>
        <voice>1</voice>
        <type>half</type>
        <stem>up</stem>
        <notehead>x</notehead>
      </note>
      <note>
        <chord/>
        <unpitched>
          <display-step>F</display-step>
          <display-octave>4</display-octave>
        </unpitched>
        <duration>32</duration>
        <instrument id="P1-I36"/>
        <voice>1</voice>
        <type>half</type>
        <stem>up</stem>
      </note>
    </measure>
  </part>
</score-partwise>
				
				""";
		
		return parse;
		
		
	}
	
	@Test
	public void testID() {
		
		String parse = getXML();


		XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		DrumParser drum = new DrumParser();
		drum.parseDrums(XMLParser.details,xmlParser.out, XMLParser.nNPM, xmlParser.document);
		
		
		assertEquals("P1-I36", drum.instrumentIDList.get(0));
		assertEquals("P1-I39", drum.instrumentIDList.get(1));
		assertEquals("P1-I52", drum.instrumentIDList.get(2));
		assertEquals("P1-I43", drum.instrumentIDList.get(3));
		assertEquals("P1-I50", drum.instrumentIDList.get(4));
		assertEquals("P1-I48", drum.instrumentIDList.get(5));
		assertEquals("P1-I44", drum.instrumentIDList.get(6));
		assertEquals("P1-I53", drum.instrumentIDList.get(7));
		assertEquals("P1-I45", drum.instrumentIDList.get(8));
		assertEquals("P1-I46", drum.instrumentIDList.get(9));
		assertEquals("P1-I47", drum.instrumentIDList.get(10));
		assertEquals("P1-I54", drum.instrumentIDList.get(11));
		assertEquals("P1-I42", drum.instrumentIDList.get(12));
	}
	
	@Test
	public void testName() {
		
		String parse = getXML();


		XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		DrumParser drum = new DrumParser();
		drum.parseDrums(xmlParser.details,xmlParser.out, xmlParser.nNPM, xmlParser.document);
		
		
		assertEquals("Bass Drum 1", drum.instrumentNameList.get(0));
		assertEquals("Snare", drum.instrumentNameList.get(1));
		
		assertEquals("Ride Cymbal 1", drum.instrumentNameList.get(2));
		assertEquals("Closed Hi-Hat", drum.instrumentNameList.get(3));
		assertEquals("Crash Cymbal 1", drum.instrumentNameList.get(4));
		assertEquals("Low-Mid Tom", drum.instrumentNameList.get(5));
		assertEquals("High Floor Tom", drum.instrumentNameList.get(6));
		assertEquals("Chinese Cymbal 1", drum.instrumentNameList.get(7));
		assertEquals("Pedal Hi-Hat", drum.instrumentNameList.get(8));
		assertEquals("Low Tom", drum.instrumentNameList.get(9));
		assertEquals("Open Hi-Hat", drum.instrumentNameList.get(10));
		assertEquals("Ride Bell", drum.instrumentNameList.get(11));
		assertEquals("Low Floor Tom", drum.instrumentNameList.get(12));
	}
	
	@Test
	public void testNotes() {
		
		String parse = getXML();


		XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
//		DrumParser drum = new DrumParser();
//		drum.parseDrums(xmlParser.details,xmlParser.out, xmlParser.nNPM, xmlParser.document);
		
		DrumParser drum = new DrumParser();
		drum.parseDrums(xmlParser.details,xmlParser.out, xmlParser.nNPM, xmlParser.document);
		
	    assertEquals("5A",drum.notesList.get(0));	
	    assertEquals("4F",drum.notesList.get(1));	
	    assertEquals("5G",drum.notesList.get(2));	
	    assertEquals("5G",drum.notesList.get(3));	
	    assertEquals("5C",drum.notesList.get(4));	
	    assertEquals("5G",drum.notesList.get(5));	
	    assertEquals("5G",drum.notesList.get(6));	
	    assertEquals("4F",drum.notesList.get(7));	
	    assertEquals("5G",drum.notesList.get(8));	
	    assertEquals("5G",drum.notesList.get(9));	
	    assertEquals("5C",drum.notesList.get(10));	
	    assertEquals("5G",drum.notesList.get(11));	
	    assertEquals("5C",drum.notesList.get(12));	
	    assertEquals("4F",drum.notesList.get(13));	
	    assertEquals("5C",drum.notesList.get(14));	
	    assertEquals("5C",drum.notesList.get(15));	
	    assertEquals("5C",drum.notesList.get(16));	
	    assertEquals("5E",drum.notesList.get(17));	
	    assertEquals("5E",drum.notesList.get(18));	
	    assertEquals("5D",drum.notesList.get(19));	
	    assertEquals("5D",drum.notesList.get(20));	
	    assertEquals("5A",drum.notesList.get(21));	
	    assertEquals("4F",drum.notesList.get(22));	
	    
	
	}
	
	@Test
	public void testChords() {
		
		String parse = getXML();


		XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		DrumParser drum = new DrumParser();
		drum.parseDrums(xmlParser.details,xmlParser.out, xmlParser.nNPM, xmlParser.document);
		
	   assertEquals(1,drum.chordList.get(0));	
	   assertEquals(0,drum.chordList.get(1));	
	   assertEquals(1,drum.chordList.get(2));	
	   assertEquals(1,drum.chordList.get(3));	
	   assertEquals(0,drum.chordList.get(4));	
	   assertEquals(1,drum.chordList.get(5));	
	   assertEquals(1,drum.chordList.get(6));	
	   assertEquals(0,drum.chordList.get(7));	
	   assertEquals(1,drum.chordList.get(8));	
	   assertEquals(1,drum.chordList.get(9));	
	   assertEquals(0,drum.chordList.get(10));	
	   assertEquals(1,drum.chordList.get(11));	
	   assertEquals(1,drum.chordList.get(12));	
	   assertEquals(0,drum.chordList.get(13));	
	   assertEquals(1,drum.chordList.get(14));	
	   assertEquals(1,drum.chordList.get(15));	
	   assertEquals(1,drum.chordList.get(16));	
	   assertEquals(1,drum.chordList.get(17));	
	   assertEquals(1,drum.chordList.get(18));	
	   assertEquals(1,drum.chordList.get(19));	
	   assertEquals(1,drum.chordList.get(20));	
	   assertEquals(1,drum.chordList.get(21));	
	   assertEquals(0,drum.chordList.get(22));	
		
	}
}
