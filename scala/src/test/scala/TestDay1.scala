
import org.junit.Test
import org.junit.Assert._

class TestDay1 {
  @Test def testTwoNumbers(): Unit = {
    assertEquals(514579, Day1.two_numbers(Vector(1721, 979, 366, 299, 675, 1456)))
  }
  
  @Test def testThreeNumbers(): Unit = {
    assertEquals(514579, Day1.two_numbers(Vector(1721, 979, 366, 299, 675, 1456)))
  }
}