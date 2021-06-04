package ws02

import junit.framework.TestCase.assertEquals
import org.junit.Test

class WorkShop02P02Todo13Test {

    @Test
    fun `check order total amount`() {
        val expected = 1035.0
        assertEquals(expected, customer1Order1.getTotalAmount())
    }

    @Test
    fun `WHEN there are no products in the order EXPECT total amount is zero`() {
        val expected = 0.0
        assertEquals(expected, emptyOrder.getTotalAmount())
    }
}