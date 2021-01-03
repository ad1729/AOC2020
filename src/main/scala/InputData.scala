import scala.io.Source
import scala.util.Using

trait InputData[A] {
  
  private def getInputAsListOfStrings(source: Source): List[String] = {
    source.getLines.toList.filter(_ != "")
  }
  
  private def getInputAsListWithSingleString(source: Source): List[String] = {
    /* for most cases the list of strings above works fine
     for a more complex parsing case, it might be easier to read the file as a full string, and
     let the processInput method do all the work 
     */
    List(source.mkString)
  }
  
  def getInput(filename: String, asString: Boolean = false): List[String] = {
    Using(Source.fromFile(filename, "UTF-8")) {
      asString match {
        case false => getInputAsListOfStrings
        case true  => getInputAsListWithSingleString
      }
    }.get
  }
  
  // implemented in objects that extend this trait
  def processInput(contents: List[String]): A

}
