import org.junit.Assert.*
import org.junit.Test

class WorkShop01P7Test {

    private val lessOrEqual: (Int, Int) -> Boolean = { num1, num2 ->
        num1 <= num2
    }

    private val moreOrEqual: (Int, Int) -> Boolean = { num1, num2 ->
        num1 >= num2
    }

    //TODO Раскомментировать тесты и прогнать
    @Test
    fun `num1 less than num2 with lambda lessOrEqual`() {
        assertTrue(WorkShop01.compareTwoInts(1, 2, lessOrEqual))
    }

    @Test
    fun `num1 equal num2 with lambda lessOrEqual`() {
        assertTrue(WorkShop01.compareTwoInts(1, 1, lessOrEqual))
    }

    @Test
    fun `num1 more than num2 with lambda lessOrEqual`() {
        assertFalse(WorkShop01.compareTwoInts(2, 1, lessOrEqual))
    }

    @Test
    fun `num1 less than num2 with lambda moreOrEqual`() {
        assertFalse(WorkShop01.compareTwoInts(1, 2, moreOrEqual))
    }

    @Test
    fun `num1 equal num2 with lambda moreOrEqual`() {
        assertTrue(WorkShop01.compareTwoInts(2, 2, moreOrEqual))
    }

    @Test
    fun `num1 more than num2 with lambda moreOrEqual`() {
        assertTrue(WorkShop01.compareTwoInts(3, 2, moreOrEqual))
    }
}