package ws02

import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import org.junit.Test

class WorkShop02P02Todo14Test {

    @Test
    fun `check there are customers with one order`() {
        val expected = listOf(customer1, customer2, customer3, customer6)
        assertEquals(expected, shop.getCustomersWithAtLeastOneDeliveredOrder())
    }

    @Test
    fun `check there are no customers with one order`() {
        val expected = emptyList<Customer>()
        assertEquals(expected, shopWithCustomersWithoutDeliveredOrders.getCustomersWithAtLeastOneDeliveredOrder())
    }
}