package Mishanya

import java.io.PrintWriter

import scala.xml._
import Notes._

/**
  * Created by Mishanya on 11.04.2016.
  */
object NotesGenerator {

  val header = """<work></work>
    <identification><encoding><software>Guitar Pro 5</software>
      <encoding-description>MusicXML 1.0</encoding-description>
    </encoding>
      <miscellaneous></miscellaneous>
    </identification>
    <part-list><score-part id="P1"><part-name>Percussion</part-name>
      <score-instrument id="P1-I1"><instrument-name></instrument-name>
      </score-instrument>
      <midi-instrument id="P1-I1"><midi-channel>10</midi-channel>
        <midi-program>0</midi-program>
      </midi-instrument>
    </score-part>
    </part-list>
    <part id="P1">"""

  def generateXml(d1: Int, d2: Int) = {
    var result = "<score-partwise>\n"
    result += header

    val hihats = hihatPertubations(d1).sortBy(x => x.count(y => y))
    for (delim <- 1 until d1) {
      val lead = leadPattern(d1, delim)
      for (i <- hihats.indices) {
        val hihat = hihats(i)
        val measure = generateMeasure(d1, d2, (delim - 1) * hihats.length + i + 1, lead, hihat)
        result += "\n" + measure
      }
    }
    result += "\n\t</part>\n</score-partwise>"

    val printer = new PrintWriter(s".\\results\\${d1}v${d2}.xml", "UTF-8")
    printer.write(result)
    printer.close()
    println(s"$d1  $d2  finished")
  }

  def generateMeasure(d1: Int, d2: Int, i: Int, lead: Array[Boolean], hihat: Array[Boolean]): String = {
    var measure = s"""<measure number="$i">\n"""
    if (i == 1) {
      measure += s"""<attributes><divisions>2</divisions>
        <key><fifths>0</fifths>
          <mode>major</mode>
        </key>
        <time><beats>$d1</beats>
          <beat-type>$d2</beat-type>
        </time>
        <clef><sign>percussion</sign>
          <line>5</line>
        </clef>
        <staff-details><staff-lines>6</staff-lines>
          <staff-tuning line="6"><tuning-step>C</tuning-step>
            <tuning-octave>0</tuning-octave>
          </staff-tuning>
          <staff-tuning line="5"><tuning-step>C</tuning-step>
            <tuning-octave>0</tuning-octave>
          </staff-tuning>
          <staff-tuning line="4"><tuning-step>C</tuning-step>
            <tuning-octave>0</tuning-octave>
          </staff-tuning>
          <staff-tuning line="3"><tuning-step>C</tuning-step>
            <tuning-octave>0</tuning-octave>
          </staff-tuning>
          <staff-tuning line="2"><tuning-step>C</tuning-step>
            <tuning-octave>0</tuning-octave>
          </staff-tuning>
          <staff-tuning line="1"><tuning-step>C</tuning-step>
            <tuning-octave>0</tuning-octave>
          </staff-tuning>
        </staff-details>
      </attributes>
        <sound pan="8" tempo="120"></sound>"""
    }
    measure += "\n" + "<barline location=\"left\"><bar-style>heavy-light</bar-style></barline>\n"
    for (j <- lead.indices) {
      if (lead(j)) {
        measure += snare8
      } else {
        measure += bass8
      }
      if (hihat(j)) {
        measure += hihat8
      }
    }
    measure += "\n</measure>"
    measure
  }

  def leadPattern(len: Int, delim: Int): Array[Boolean] = {
    var lead = new Array[Boolean](len)
    for (i <- 0 until len) {
      lead(i) = i < delim
    }
    lead

  }

  def hihatPertubations(len: Int): List[Array[Boolean]] = {
    var result = List[Array[Boolean]]()
    var curArr = new Array[Boolean](len)
    for (i <- 0 until len) {
      curArr(i) = false
    }
    for (i <- 0 until math.pow(2, len).toInt) {
      add(curArr)
      result :+= curArr.clone()
    }
    result
  }

  def add(arr: Array[Boolean]) = {
    var continue = true
    for (i <- arr.indices) {
      if (continue) {
        if (!arr(i)) {
          arr(i) = true
          continue = false
        } else {
          arr(i) = false
        }
      }
    }

  }
}
