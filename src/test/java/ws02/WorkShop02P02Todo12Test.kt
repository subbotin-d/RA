package ws02

import org.junit.Assert
import org.junit.Test

class WorkShop02P02Todo12Test {

    @Test
    fun `check customers from the city`() {
        val expected = listOf(customer1, customer2)
        Assert.assertEquals(expected, shop.getCustomersFromCity(tsk))
    }

    @Test
    fun `WHEN there are no customers from the city EXPECT result is emptyList`() {
        val expected = emptyList<Customer>()
        Assert.assertEquals(expected, shopWithoutCustomersFromNsk.getCustomersFromCity(nsk))
    }
}