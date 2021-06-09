package ws02p2

import org.junit.Assert
import org.junit.Test
import java.lang.ArithmeticException
import java.lang.IllegalArgumentException

class VectorCosTests {

    @Test(expected = IllegalArgumentException::class)
    fun vectorsCosIllegalArgumentExceptionTest() {
        vector3_1.cos(vectorN_7)
    }

    @Test(expected = ArithmeticException::class)
    fun vectorsCosArithmeticExceptionTest() {
        vector3_3.cos(vector3_2)
    }

    @Test
    fun vectorsCosTest() {
        Assert.assertEquals(0.9, vector3_1.cos(vector3_2), 0.1)
    }
}