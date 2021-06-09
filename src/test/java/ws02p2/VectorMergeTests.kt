package ws02p2

import org.junit.Assert
import org.junit.Test
import ws02.Vector
import java.lang.IllegalArgumentException

class VectorMergeTests {

    @Test
    fun mergeVectors3Test() {
        Assert.assertEquals(
                vector3_1.mergeVector(vector3_2).length(), Vector(3.0,3.0,3.0).length(), 0.1
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun mergeVectorsNExceptionTest() {
        vectorN_5.mergeVector(vectorN_6)
    }

    @Test
    fun mergeVectorsNTest() {
        Assert.assertEquals(
                vectorN_6.mergeVector(vectorN_7).length(), Vector(5.0,7.0,9.0,11.0).length(), 0.1
        )
    }
}