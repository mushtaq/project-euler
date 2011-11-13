package euler

import Lib._

object Lib {
  implicit def longToRichLong(n: Long): RichLong = new RichLong(n)
  implicit def intToRichLong(n: Int): RichLong = new RichLong(n)
  implicit def bigDecimalToRichBigDecimal(n: BigDecimal): RichBigDecimal = new RichBigDecimal(n)
  implicit def seqToRichSeq[T](xs: Seq[T]): RichSeq[T] = new RichSeq(xs)
  implicit def matrixToRichMatrix(matrix: Seq[Seq[Int]]): RichMatrix = new RichMatrix(matrix)
  implicit def arrayMatrixToRichMatrix(matrix: Array[Array[Int]]): RichMatrix = new RichMatrix(matrix.toSeq.map(_.toSeq))
}

class RichLong(n: Long) {
  def digits = n.toString.map(_.asDigit)
  def ^^(exponent: Int) = BigInt(n).pow(exponent).toLong

  def isDivisibleBy(factor: Long) = n % factor == 0
  def isNotDivisibleBy(factor: Long) = !isDivisibleBy(factor)
  def isEven = isDivisibleBy(2)

  def isDivisorOf(number: Long) = number.isDivisibleBy(n)
  def factorsRange = 2L to math.sqrt(n).toLong
  def isPrime = n > 0 && (factorsRange forall n.isNotDivisibleBy)

  def factors: Seq[Long] = factorsRange find (_.isDivisorOf(n)) match {
    case None => Seq(n)
    case Some(x) => x +: (n / x).factors
  }

  def divisors = 1L +: factors.allCombinations.map(_.product).distinct
  def isPanDigital = digits.toSet == (1 to n.toString.size).toSet

  def gcd(other: Long) = BigInt(n).gcd(other).toLong
  def lcm(other: Long) = (n*other)/gcd(other)

  lazy val sumOfProperDivisors = divisors.init.sum
  def isAmicable = sumOfProperDivisors != n && sumOfProperDivisors.sumOfProperDivisors == n
  def isAbundant = sumOfProperDivisors > n

  def quadraticWith(a: Int,  b: Int) = (n*n) + (a*n) + b
}

class RichBigDecimal(n: BigDecimal) {
  def mantissa = n.toString.split("\\.").last
}

class RichSeq[T](xs: Seq[T]) {
  def allCombinations = (1 to xs.size) flatMap xs.combinations
  def diffThenUnion(ys: Seq[T]) = xs diff ys union ys
}

class RichMatrix(matrix: Seq[Seq[Int]]) {
  val (rowNum, colNum) = (matrix.length, matrix.head.length)

  def rightDiagonalAt(coordinates: (Int, Int)): Seq[Int] = coordinates match {
    case (`rowNum`, _) | (_, `colNum`) => Seq()
    case (row, col) => matrix(row)(col) +: rightDiagonalAt(row + 1, col + 1)
  }

  def firstColumn = (0 until rowNum).map ((_, 0)).reverse
  def firstRow = (1 until colNum) map ((0, _))
  def diagonalStartCoordinates = firstColumn ++ firstRow

  def rightDiagonals = diagonalStartCoordinates.toSeq map rightDiagonalAt

  def diagonals = rightDiagonals ++ matrix.reverse.rightDiagonals
}

class MemoizedFunction[T, S](f: T => S) {
  val cache = collection.mutable.Map[T, S]()
  def apply(args: T): S = cache.getOrElseUpdate(args, f(args))
}
