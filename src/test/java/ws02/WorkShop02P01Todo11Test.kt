package ws02

import org.junit.Assert.assertEquals
import org.junit.Test

class WorkShop02P01Todo11Test {

    @Test
    fun `check customers' city`() {
        val expected = setOf(tsk, nsk, spb, msk)
        assertEquals(expected, shop.getCustomerCities())
    }

    @Test
    fun `WHEN shop without customers EXPECT result is emptyList`() {
        val expected = emptySet<City>()
        assertEquals(expected, shopWithoutCustomers.getCustomerCities())
    }
}