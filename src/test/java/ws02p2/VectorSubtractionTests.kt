package ws02p2

import org.junit.Assert
import org.junit.Test
import ws02.Vector
import java.lang.IllegalArgumentException

class VectorSubtractionTests {

    @Test
    fun mergeVectors3Test() {
        Assert.assertEquals(
                vector3_1.subVector(vector3_2).length(), Vector(1.0,-1.0,-3.0).length(), 0.1
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun mergeVectorsNExceptionTest() {
        vectorN_5.subVector(vectorN_6)
    }

    @Test
    fun mergeVectorsNTest() {
        Assert.assertEquals(
                vectorN_6.subVector(vectorN_7).length(), Vector(-1.0, -1.0, -1.0, -1.0).length(), 0.1
        )
    }
}