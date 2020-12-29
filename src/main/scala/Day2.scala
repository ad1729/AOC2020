import scala.util.chaining._

case class Day2Input(minOccurrence: Int, maxOccurrence: Int, character: Char, password: String)

object Day2 extends InputData[List[Day2Input]] {
  
  override def process_input(contents: List[String]): List[Day2Input] = {
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
  
  def first_policy(row: Day2Input): Boolean = {
    val occurrence = row.password.filter(_ == row.character).size
    (occurrence >= row.minOccurrence) & (occurrence <= row.maxOccurrence)
  }
  
  def second_policy(row: Day2Input): Boolean = {
    val mainChar = row.character
    val firstChar = row.password.charAt(row.minOccurrence - 1)
    val secondChar = row.password.charAt(row.maxOccurrence - 1)
    (firstChar == mainChar & secondChar != mainChar) | (firstChar != mainChar & secondChar == mainChar)
  }
  
  def valid_passwords(data: List[Day2Input]): Unit = {
    val size = data.size
    val first = data.map(first_policy).filter(_ == true).size
    println(s"${first} passwords (out of $size) are valid according to the first policy.")

    val second = data.map(second_policy).filter(_ == true).size
    println(s"${second} passwords (out of $size) are valid according to the second policy.")
    
  }

  def main(args: Array[String]): Unit = {
    val testData = """1-3 a: abcde
                      |1-3 b: cdefg
                      |2-9 c: ccccccccc"""
      .stripMargin
      .split("\n")
      .toList pipe process_input
    
    println("Test data:")
    println(testData)
    valid_passwords(testData)

    val filename = "data/input-day2.txt"
    val inputData = filename pipe get_input pipe process_input
    
    println("Input data:")
    println(inputData.take(5))
    valid_passwords(inputData)
  }

}
