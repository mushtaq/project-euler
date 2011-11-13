package top

object Common {
  val alphabet = 'a' to 'z'
  val alphabetSet = alphabet.toSet
}

import Common._

object Zero {
  val result = BigInt(2) pow 38
}

object One {
  val shiftedAlphabet = (alphabet drop 2) ++ (alphabet take 2)
  val encoding = (alphabet zip shiftedAlphabet toMap) withDefault identity
  val data = "g fmnc wms bgblr rpylqjyrc gr zw fylb. rfyrq ufyr amknsrcpq ypc dmp. bmgle gr gl zw fylb gq glcddgagclr ylb rfyr'q ufw rfgq rcvr gq qm jmle. sqgle qrpgle.kyicrpylq() gq pcamkkclbcb. lmu ynnjw ml rfc spj."
  val result0 = data map encoding
  val result = "map" map encoding
}

object Two {
  val data = io.Source.fromURL("http://www.pythonchallenge.com/pc/def/ocr.html").getLines().toList.drop(37).dropRight(2).flatten
  val result = (data filter alphabetSet).mkString
}

object Three {
  val data = io.Source.fromURL("http://www.pythonchallenge.com/pc/def/equality.html").getLines().toList.drop(21).dropRight(2).flatten

  object ExactThreeUppers {
    val upperCasePattern = Seq(false, true, true, true, false, true, true, true, false)
    def unapply(chars: Seq[Char]) = if(chars.map(_.isUpper) == upperCasePattern) Some(chars(4)) else None
  }

  val result = data sliding 9 collect { case ExactThreeUppers(centralChar) => centralChar} mkString
}

object Four {
  def getNextNumber(number: String): String =
    io.Source.fromURL("http://www.pythonchallenge.com/pc/def/linkedlist.php?nothing=" + number).mkString.dropWhile(!_.isDigit) match {
      case "" => number
      case other => getNextNumber(other)
    }
  val result = getNextNumber("16031")

}
