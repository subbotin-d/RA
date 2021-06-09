package ws02p2

import org.junit.Assert
import org.junit.Test
import java.lang.IllegalArgumentException

class VectorScaleTests {

    @Test(expected = IllegalArgumentException::class)
    fun scaleVectorExceptionTest() {
        vector3_1.scaleVector(vectorN_6)
    }

    @Test
    fun scaleVectorTest() {
        Assert.assertEquals(4.0,  vector3_1.scaleVector(vectorN_5), 0.1)
    }
}