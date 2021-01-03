import scala.util.chaining._
import scala.BigInt

object Day3 extends InputData[Nothing] {

  override def processInput(contents: List[String]): Nothing = ???
  
  def numberOfTreesOnGrid(grid: List[String], right: Int = 3, down: Int = 1): Int = {
    val numRows = grid.size
    val numCols = grid(0).size // number of elements/cols after which the pattern repeats
    
    var row = 0
    var col = 0
    var numTrees = 0
    
    while (row < (numRows - 1)) {
      row += down
      col += right  
      // The modulo operator here can reach columns not in the data, since the pattern repeats after the last char
      //println(s"row: $row, col: $col, value: ${grid(row).charAt(col % numCols)}")
      if (grid(row).charAt(col % numCols) == '#')
        numTrees += 1
    }
    
    numTrees
  }
  
  def productNumTreesMultipleSlopes(grid: List[String], slopes: List[(Int, Int)]): BigInt = {
    val numTreesPerSlope: List[BigInt] = slopes
      .map((right, down) => BigInt(numberOfTreesOnGrid(grid, right, down)))
    
    println(s"# trees per slope: $numTreesPerSlope")
    numTreesPerSlope.reduce(_ * _)
  }

  def main(args: Array[String]): Unit = {
    val testData = """..##.......
                     |#...#...#..
                     |.#....#..#.
                     |..#.#...#.#
                     |.#...##..#.
                     |..#.##.....
                     |.#.#.#....#
                     |.#........#
                     |#.##...#...
                     |#...##....#
                     |.#..#...#.#"""
      .stripMargin
      .split("\n")
      .toList
    
//    println("Test data:\n")
//    testData.foreach(println)
    
    val filename = "data/input-day3.txt"
    val inputData = getInput(filename)

//    println("Input data:\n")
//    println(inputData.take(3))

    val data = Map(("test", testData), ("input", inputData))
    
    // Part 1
    println("\nPart 1:")
    data
      .map((k, v) => (k, numberOfTreesOnGrid(v)))
      .foreach((k, v) => println(s"Number of trees on $k grid: $v"))
    
    // Part 2
    println("\nPart 2:")
    val slopes = List((1,1), (3,1), (5,1), (7,1), (1,2))
    
    data
      .map((k, v) => (k, productNumTreesMultipleSlopes(v, slopes)))
      .foreach((k, v) => println(s"Product of number of trees for different slopes on $k grid: $v"))
    
  }

}
