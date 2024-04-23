package xmlparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

import Parser.XMLParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;



class testParser {
	
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
public String getDrumXML() {
	
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
	void testInstrumentName() {
		
		String parse = getGuitarXML();
		
		XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		assertEquals("Guitar",XMLParser.instrument);
	}
	
	@Test
	void testXNumberOfNotes() {
		
		
		String parse = getGuitarXML();
		
		
		XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		assertEquals(8,xmlParser.nNPM.get(0),0.2);
		assertEquals(14,xmlParser.nNPM.get(1),0.2);
		
		System.out.println("test" +  xmlParser.nNPM);
	}
	
	@Test
	void testNumberOfMeasures() {
		
		
		String parse = getGuitarXML();
		
		
		XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		assertEquals(2,xmlParser.numOfmeasures);
		
	}
	
	
	@Test
	void testXMLParser2() {
		
		String parse = getXML2();
		
		XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		assertEquals("Guitar",XMLParser.instrument);
	}
	
	@Test
	void testXNumberOfNotes2() {
		
		
		String parse = getXML2();
		
		
		XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		assertEquals(3,xmlParser.nNPM.get(0),0.2);
	
		
	}
	
	@Test
	void testNumberOfMeasures2() {
		
		
		String parse = getXML2();
		
		
		XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		assertEquals(1,xmlParser.numOfmeasures);
		
	}
	
	@Test
	void testXMLParser3() {
		
		String parse = getDrumXML();
		
		XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		assertEquals("Drumset",XMLParser.instrument);
	}
	
	@Test
	void testNumberOfMeasures3() {
		
		
		String parse = getDrumXML();
		
		
		XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		assertEquals(2,xmlParser.numOfmeasures);
		
	}
	
	@Test
	void testXNumberOfNotes3() {
		
		
		String parse = getDrumXML();
		
		
		XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		assertEquals(12,xmlParser.nNPM.get(0),0.2);
		assertEquals(23,xmlParser.nNPM.get(1),0.2);
	
		
	}

}

