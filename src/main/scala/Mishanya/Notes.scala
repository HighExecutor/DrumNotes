package Mishanya

/**
  * Created by Mishanya on 11.04.2016.
  */
object Notes {

  def snare8() = {
    "\n" + """<note><unpitched><display-step>D</display-step>
      					<display-octave>3</display-octave>
      				</unpitched>
      				<duration>1</duration>
      				<voice>1</voice>
      				<type>eighth</type>
      				<notations><dynamics><f></f>
      					</dynamics>
      					<technical><string>3</string>
      						<fret>38</fret>
      					</technical>
      				</notations>
      			</note>"""
  }

  def bass8() = {
    "\n" + """<note><unpitched><display-step>C</display-step>
      					<display-octave>3</display-octave>
      				</unpitched>
      				<duration>1</duration>
      				<voice>1</voice>
      				<type>eighth</type>
      				<notations><dynamics><f></f>
      					</dynamics>
      					<technical><string>5</string>
      						<fret>36</fret>
      					</technical>
      				</notations>
      			</note>"""
  }

  def hihat8() = {
    "\n" + """<note><chord></chord>
      				<unpitched><display-step>F</display-step>
      					<display-octave>3</display-octave>
      				</unpitched>
      				<duration>1</duration>
      				<voice>1</voice>
      				<type>eighth</type>
      				<notations><dynamics><f></f>
      					</dynamics>
      					<technical><string>1</string>
      						<fret>42</fret>
      					</technical>
      				</notations>
      			</note>"""
  }

}
