package ws02

import org.junit.Assert.*
import org.junit.Test

class Vector3Test {
    private val _delta = 0.0000001

    @Test
    fun `new vector initialized with zeroes`() {
        val newVector = Vector3()
        assertTrue(newVector.getValues().all { value -> value == 0.0 })
    }

    @Test
    fun `new vector has correct size`() {
        val newVector = Vector3()
        assertEquals(3, newVector.size)
    }

    @Test
    fun `is new vector with values correct`() {
        val x = 1.22222
        val y = -0.23232
        val z = 42.0
        val newVector = Vector3(x, y, z)
        assertEquals(x, newVector.x, _delta)
        assertEquals(y, newVector.y, _delta)
        assertEquals(z, newVector.z, _delta)
    }

    @Test
    fun `is modification correct`() {
        val x = 1.22222
        val y = -0.23232
        val z = 123123.2
        val vector = Vector3(x, y, z)
        val newZ = 9442.11
        vector.z = newZ
        assertEquals(x, vector.x, _delta)
        assertEquals(y, vector.y, _delta)
        assertEquals(newZ, vector.z, _delta)
    }
}