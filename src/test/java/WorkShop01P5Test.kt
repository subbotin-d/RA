import org.junit.Assert.*
import org.junit.Test

class WorkShop01P5Test {

    @Test
    fun `even number`() {
        assertEquals("Чётное", WorkShop01.evenOddNumber(2))
    }

    @Test
    fun `odd number`() {
        assertEquals("Нечётное", WorkShop01.evenOddNumber(1))
    }
}