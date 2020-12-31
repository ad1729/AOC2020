import scala.util.chaining._

object Day1 extends InputData[List[Int]]{

  override def processInput(contents: List[String]): List[Int] = {
    contents.map(_.trim.toInt)
  }
  
  def twoNumbers(numbers: List[Int]): Unit = {
    val size = numbers.size

    // TODO: refactor this to do the printing in the main method
    for (i <- 0 until size)
      val first = numbers(i)
      for (j <- i until size)
        val second = numbers(j)
        if (first + second == 2020)
          println(s"x1: $first, x2: $second, sum: ${first + second}, product: ${first * second}")
  }
  
  def threeNumbers(numbers: List[Int]): Unit = {
    val size = numbers.size

    // TODO: refactor this to do the printing in the main method
    for (i <- 0 until size)
      val first = numbers(i)
      for (j <- i until size)
        val second = numbers(j)
        for (k <- j until size)
          val third = numbers(k) 
          if (first + second + third == 2020)
            println(s"x1: $first, x2: $second, x3: $third, sum: ${first + second + third}, product: ${first * second * third}")
  }
  
  def main(args: Array[String]): Unit = {
    val testData = List(1721, 979, 366, 299, 675, 1456)
    val filename = "data/input-day1.txt"
    val inputData = filename pipe getInput pipe processInput
    
    twoNumbers(testData)    
    twoNumbers(inputData)

    threeNumbers(testData)
    threeNumbers(inputData)
  }
}
