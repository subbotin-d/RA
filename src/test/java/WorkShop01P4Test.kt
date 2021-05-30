import junit.framework.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

class WorkShop01P4Test {

    //TODO Раскомментировать тесты и прогнать
    @Test
    fun `str1 less than str2`() {
        val str1 = "Ааабб"
        val str2 = "Ааабв"
        assertTrue(WorkShop01.compareTwoStrings(str1, str2))
    }

    @Test
    fun `str1 more than str2`() {
        val str1 = "Ааабб"
        val str2 = "Аааба"
        assertFalse(WorkShop01.compareTwoStrings(str1, str2))
    }
}