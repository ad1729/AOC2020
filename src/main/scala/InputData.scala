import scala.io.Source

trait InputData[A] {
  
  def getInput(filename: String): List[String] = {
    val bufferedSource = Source.fromFile(filename, "UTF-8")
    val contents = bufferedSource.getLines.toList.filter(_ != "")
    bufferedSource.close // close the file connection
    contents
  }
  
  // implemented in objects that extend this trait
  def processInput(contents: List[String]): A = ???

}
