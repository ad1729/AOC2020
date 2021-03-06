import scala.util.chaining._

case class Day2Input(minOccurrence: Int, maxOccurrence: Int, character: Char, password: String)

sealed trait Policy
case object FirstPolicy extends Policy
case object SecondPolicy extends Policy

object Day2 extends InputData[List[Day2Input]] {
  
  override def processInput(contents: List[String]): List[Day2Input] = {
    contents.map {str => 
      val splitstr = str.split(" ")
      val occurrence = splitstr(0).split("-")
      Day2Input(
        minOccurrence = occurrence(0).toInt,
        maxOccurrence = occurrence(1).toInt,
        character = splitstr(1).charAt(0),
        password = splitstr(2)
      )
    }
  }
  
  def firstPolicy(row: Day2Input): Boolean = {
    val occurrence = row.password.filter(_ == row.character).size
    (occurrence >= row.minOccurrence) & (occurrence <= row.maxOccurrence)
  }
  
  def secondPolicy(row: Day2Input): Boolean = {
    val mainChar = row.character
    val firstChar = row.password.charAt(row.minOccurrence - 1)
    val secondChar = row.password.charAt(row.maxOccurrence - 1)
    (firstChar == mainChar & secondChar != mainChar) | (firstChar != mainChar & secondChar == mainChar)
  }
  
  def numValidPasswords(data: List[Day2Input], policy: Policy): Int = {
    val policyType = policy match {
      case FirstPolicy => firstPolicy
      case SecondPolicy => secondPolicy
    }
    
    data.map(policyType).filter(_ == true).size
  }
  
  def main(args: Array[String]): Unit = {
    val testData = """1-3 a: abcde
                      |1-3 b: cdefg
                      |2-9 c: ccccccccc"""
      .stripMargin
      .split("\n")
      .toList pipe processInput
    
//    println("Test data:")
//    println(testData)

    val filename = "data/input-day2.txt"
    val inputData = processInput(getInput(filename))
    
//    println("Input data:")
//    println(inputData.take(5))

    val data = Map(("test", testData), ("input", inputData))
    val policy = List(FirstPolicy, SecondPolicy)
    
    data
      .flatMap((k, v) => policy.map(p => (k, p, v)))
      .foreach((k, p, v) => 
        println(s"${k.capitalize} data: ${numValidPasswords(v, p)} passwords (out of ${v.size}) are valid according to $p."))
  }

}
