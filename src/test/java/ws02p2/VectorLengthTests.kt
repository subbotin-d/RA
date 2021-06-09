package ws02p2

import org.junit.Assert
import org.junit.Test

class VectorLengthTests {

    @Test
    fun checkVectorNLengthTest() {
        Assert.assertEquals(7.3, vectorN_6.length(), 0.1)
    }

    @Test
    fun checkVector3LengthTest() {
        Assert.assertEquals(2.2, vector3_1.length(), 0.1)
    }
}