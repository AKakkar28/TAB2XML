package playnotes.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Parser.DrumParser;
import Parser.XMLParser;

class TestDrumsPlaying {

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
   void testPlayin() {
	   
		String parse = getXML();
		
		String test = "T120 V9 [CRASH_CYMBAL_1]I+[BASS_DRUM]I [CLOSED_HI_HAT]I [CLOSED_HI_HAT]I+[ACOUSTIC_SNARE]I [CLOSED_HI_HAT]I [CLOSED_HI_HAT]I+[BASS_DRUM]I [CLOSED_HI_HAT]I [CLOSED_HI_HAT]I+[ACOUSTIC_SNARE]I [CLOSED_HI_HAT]I | [ACOUSTIC_SNARE]S+[BASS_DRUM]S [ACOUSTIC_SNARE]S [ACOUSTIC_SNARE]S [ACOUSTIC_SNARE]S [LO_MID_TOM]S [LO_MID_TOM]S [LO_TOM]S [LO_TOM]S [CRASH_CYMBAL_1]H+[BASS_DRUM]H ";
				


		XMLParser xmlParser = new XMLParser();
		try {
			xmlParser.loadXMLFromString(parse);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		DrumParser drum = new DrumParser();
		drum.parseDrums(xmlParser.details,xmlParser.out, xmlParser.nNPM, xmlParser.document);
		
		drum.drumTest.playNotes();
		
		assertEquals(test,drum.drumTest.total);
	   
	   
		
	   
	   
	   
   }

}
