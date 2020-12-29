import scala.util.chaining._

case class Day2Input(minOccurrence: Int, maxOccurrence: Int, character: Char, password: String)

object Day2 extends InputData[List[Day2Input]] {
  
  override def process_input(contents: List[String]): List[Day2Input] = {
    contents map {
      val splitStr = _.split(" ")
    }
  }

  def check_vaild_first() = ???

  def check_valid_second() = ???

  def valid_passwords(value: List[Day2Input]): Unit = ???

  def main(args: Array[String]): Unit = {
    val testData = """1-3 a: abcde
                      1-3 b: cdefg
                      2-9 c: ccccccccc"""
      .split("\n")
      .toList pipe process_input
    
    println(testData)

    val filename = "../data/input-day2.txt"
    val inputData = filename pipe get_input pipe process_input

//    valid_passwords(testData)
//    valid_passwords(inputData)
  }

}
