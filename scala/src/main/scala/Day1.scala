import scala.util.chaining._

object Day1 extends InputData[Vector[Int]]{

  override def process_input(contents: List[String]): Vector[Int] = {
    val numbers = contents
      .map(_.trim.toInt)
      .toVector
    
    numbers
  }
  
  def two_numbers(numbers: Vector[Int]): Unit = {
    val size = numbers.size

    for (i <- 0 until size)
      val first = numbers(i)
      for (j <- i until size)
        val second = numbers(j)
        if (first + second == 2020)
          println(s"x1: $first, x2: $second, sum: ${first + second}, product: ${first * second}")
  }
  
  def three_numbers(numbers: Vector[Int]): Unit = {
    val size = numbers.size

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
    val testData = Vector(1721, 979, 366, 299, 675, 1456)
    val filename = "../data/input-day1.txt"
    val inputData = filename pipe get_input pipe process_input
    
    two_numbers(testData)    
    two_numbers(inputData)

    three_numbers(testData)
    three_numbers(inputData)
  }
}
