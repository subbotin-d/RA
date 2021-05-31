import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class WorkShop01P8Test {

    @Test
    fun `check num is not in any range from left`() {
        assertEquals("Число -100 не принадлежит ни одному из интервалов", WorkShop01.checkNumInRange(-100))
    }

    @Test
    fun `check num is in 1st range from left`() {
        assertEquals("Число -99 принадлежит интервалу (-100; 0]", WorkShop01.checkNumInRange(-99))
    }

    @Test
    fun `check num is in 1st range from right`() {
        assertEquals("Число 0 принадлежит интервалу (-100; 0]", WorkShop01.checkNumInRange(0))
    }

    @Test
    fun `check num is in 2nd range from left`() {
        assertEquals("Число 1 принадлежит интервалу (0; 3]", WorkShop01.checkNumInRange(1))
    }

    @Test
    fun `check num is in 2nd range from right`() {
        assertEquals("Число 3 принадлежит интервалу (0; 3]", WorkShop01.checkNumInRange(3))
    }

    @Test
    fun `check num is in 3rd range from left`() {
        assertEquals("Число 4 принадлежит интервалу (3; 100]", WorkShop01.checkNumInRange(4))
    }

    @Test
    fun `check num is in 3rd range from right`() {
        assertEquals("Число 100 принадлежит интервалу (3; 100]", WorkShop01.checkNumInRange(100))
    }

    @Test
    fun `check num is in 4th range from left`() {
        assertEquals("Число 101 принадлежит интервалу (100; 102]", WorkShop01.checkNumInRange(101))
    }

    @Test
    fun `check num is in 4th range from right`() {
        assertEquals("Число 102 принадлежит интервалу (100; 102]", WorkShop01.checkNumInRange(102))
    }

    @Test
    fun `check num is not in any range from right`() {
        assertEquals("Число 103 не принадлежит ни одному из интервалов", WorkShop01.checkNumInRange(103))
    }
}