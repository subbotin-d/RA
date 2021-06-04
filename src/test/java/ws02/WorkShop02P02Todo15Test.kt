package ws02

import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import org.junit.Test

class WorkShop02P02Todo15Test {

    @Test
    fun `WHEN total amount more than 2220 EXPECT result with 1,2,3,4 customers`() {
        val expected = listOf(customer1, customer2, customer3, customer4)
        assertEquals(expected, shop.getCustomersWithTotalAmountMoreThan(2220.0))
    }

    @Test
    fun `WHEN total amount more than 10k EXPECT result is emptyList`() {
        val expected = emptyList<Customer>()
        assertEquals(expected, shop.getCustomersWithTotalAmountMoreThan(10000.0))
    }
}