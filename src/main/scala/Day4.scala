import scala.util.chaining._

object Day4 extends InputData [List[Map[String, String]]] {
  
  def extractFieldsFromString(str: String): Map[String, String] = {
    str
      .replaceAll("\n", " ") // replace the newline character in each passport fields string
      .split(" ")
      .map(_.split(":")) // returns Array[Array[String]]
      .map {case Array(key, value) => (key, value)}
      .toMap
  }
  
  override def processInput(contents: List[String]): List[Map[String, String]] = {
    contents
      .flatMap(_.split("\n\n")) // split string into list of strings with fields
      .iterator
      .map(extractFieldsFromString)
      .toList
  }
  
  def numValidPassports(listPassports: List[Map[String, String]]): Int = {
     listPassports
        // (part 1) if the map contains at least 7 fields and the only missing field is cid
       .filter(map => (map.keys.size == 8) | (map.keys.size == 7 & !map.contains("cid")))
       // (part 2) if the fields are valid after first check
       //.map(checkFieldValidity)
       //.filter(_ == true)
       .size
  }

  def main(args: Array[String]): Unit = {
    val testDataRaw = """ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
                     |byr:1937 iyr:2017 cid:147 hgt:183cm
                     |
                     |iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
                     |hcl:#cfa07d byr:1929
                     |
                     |hcl:#ae17e1 iyr:2013
                     |eyr:2024
                     |ecl:brn pid:760753108 byr:1931
                     |hgt:179cm
                     |
                     |hcl:#cfa07d eyr:2025 pid:166559648
                     |iyr:2011 ecl:brn hgt:59in"""
      .stripMargin

    val testData = processInput(List(testDataRaw))

    println("Test data:\n")
    testData.foreach(println)

    val filename = "data/input-day4.txt"
    val inputData = processInput(getInput(filename, asString = true))

    println("\nInput data:\n")
    inputData.take(2).foreach(println)

    val data = Map(("test", testData), ("input", inputData))

    // Part 1
    println("\nPart 1:")
    data
      .map((k, v) => (k, numValidPassports(v)))
      .foreach((k, v) => println(s"Number of valid passports on $k data: $v"))
    
    // TOOD: Part 2 seems like a hassle
  }

}
