package ws02p2

import org.junit.Assert
import org.junit.Test
import ws02.Vector

class VectorMultiplicationTests {

    @Test
    fun vectorNMultipleTest() {
        Assert.assertEquals(
                Vector(8.0, 12.0, 16.0, 20.0).length(), vectorN_6.multiVector(4).length(), 0.1
        )
    }

    @Test
    fun emptyVectorMultipleTests() {
        Assert.assertEquals(
                Vector(0.0, 0.0, 0.0).length(), vectorN_2.multiVector(4).length(), 0.1
        )
    }
}