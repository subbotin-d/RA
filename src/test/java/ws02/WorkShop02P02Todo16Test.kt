package ws02

import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test

class WorkShop02P02Todo16Test {

    @Test
    fun `WHEN there is only one the most popular product EXPECT result is tea`() {
        val expected = tea.copy()
        assertEquals(expected, shop.getMostPopularProduct())
    }

    @Test
    fun `WHEN there are no orders EXPECT result is null `() {
        assertNull(shopWithoutCustomers.getMostPopularProduct())
    }

    @Test
    fun `WHEN there are two the most popular products EXPECT result is first product`() {
        val expected = milk.copy()
        assertEquals(expected, shopWithTwoTheMostPopularProducts.getMostPopularProduct())
    }
}


