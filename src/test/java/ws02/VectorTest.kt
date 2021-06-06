package ws02

import org.junit.Assert.*
import org.junit.Test
import java.lang.IllegalArgumentException
import kotlin.math.pow
import kotlin.math.sqrt

class VectorTest {
    private val _delta = 0.0000001

    @Test
    fun `new vector initialized with zeroes`() {
        val newVector = Vector(4)
        assertTrue(newVector.getValues().all { value -> value == 0.0 })
    }

    @Test
    fun `new vector has correct size`() {
        val expectedValue = 666
        val newVector = Vector(expectedValue)
        assertEquals(expectedValue, newVector.size)
    }

    @Test
    fun `is new vector with values correct`() {
        val arg1 = 1.22222
        val arg2 = -0.23232
        val arg3 = 42.0
        val arg4 = Double.MAX_VALUE
        val arg5 = Double.MIN_VALUE
        var args = listOf<Double>(arg1, arg2, arg3, arg4, arg5)
        val newVector = Vector(*args.toDoubleArray())
        assertEquals(args, newVector.getValues())
    }

    @Test
    fun `is modification correct`() {
        val arg1 = 1.22222
        val arg2 = -0.23232
        val vector = Vector(arg1, arg2)
        val newArg = 9442.11
        vector.setValueOfDimension(2, newArg)
        assertEquals(arg1, vector.getValueOfDimension(1), _delta)
        assertEquals(newArg, vector.getValueOfDimension(2), _delta)
    }

    @Test()
    fun `modification throws if illegal index`() {
        val arg1 = 1.22222
        val arg2 = -0.23232
        val vector = Vector(arg1, arg2)
        val newArg = 9442.11
        var thrown = false
        try {
            vector.setValueOfDimension(3, newArg)
        } catch (e : IllegalArgumentException) {
            thrown = true
        }

        assertTrue(thrown)
    }


    @Test
    fun `is vector length correct`() {
        val x1 = 3.0
        val x2 = 4.0
        val x3 = 5.0
        val expected = sqrt(x1.pow(2) + x2.pow(2) + x3.pow(2))

        val newVector = Vector(x1, x2, x3)
        assertEquals(expected, newVector.length, _delta)
    }

    @Test
    fun `is empty vector length zero`() {
        val newVector = Vector(0)
        assertEquals(0.0, newVector.length, _delta)
    }

    @Test
    fun `is vectors addition correct`() {
        val arg1_1 = 1.0
        val arg1_2 = 3.5
        val arg1_3 = -3.4
        val vector1 = Vector(arg1_1, arg1_2, arg1_3)

        val arg2_1 = -0.55555
        val arg2_2 = 23.3
        val arg2_3 = 1010101010.0
        val vector2 = Vector(arg2_1, arg2_2, arg2_3)

        val sum = vector1 + vector2

        var areAllDimensCorrect = true
        for(index in 1 until vector1.size) {
            val expectedSum = vector1.getValueOfDimension(index) + vector2.getValueOfDimension(index)
            val isDimenCorrect = sum.getValueOfDimension(index) == expectedSum
            areAllDimensCorrect = areAllDimensCorrect && isDimenCorrect
        }

        assertTrue(areAllDimensCorrect)
    }

    @Test
    fun `sum throws if length differs`() {

        val vector1 = Vector(1.1, 2.2)
        val vector2 = Vector(3.3)
        var thrown = false

        try {
            val sum = vector1 + vector2
        } catch (e : IllegalArgumentException) {
            thrown = true
        }
        assertTrue(thrown)
    }

    @Test
    fun `is vectors subtraction correct`() {
        val arg1_1 = 1.0
        val arg1_2 = 3.5
        val arg1_3 = -3.4
        val vector1 = Vector(arg1_1, arg1_2, arg1_3)

        val arg2_1 = -0.55555
        val arg2_2 = 23.3
        val arg2_3 = 1010101010.0
        val vector2 = Vector(arg2_1, arg2_2, arg2_3)

        val sub = vector1 - vector2

        var areAllDimensCorrect = true
        for(index in 1 until vector1.size) {
            val expectedSum = vector1.getValueOfDimension(index) - vector2.getValueOfDimension(index)
            val isDimenCorrect = sub.getValueOfDimension(index) == expectedSum
            areAllDimensCorrect = areAllDimensCorrect && isDimenCorrect
        }

        assertTrue(areAllDimensCorrect)
    }

    @Test
    fun `subtract throws if size differs`() {

        val vector1 = Vector(1.1, 2.2)
        val vector2 = Vector(3.3)
        var thrown = false

        try {
            val suи = vector1 - vector2
        } catch (e : IllegalArgumentException) {
            thrown = true
        }
        assertTrue(thrown)
    }

    @Test
    fun `is dot product correct`() {
        val arg1_1 = 1.0
        val arg1_2 = 3.5
        val arg1_3 = -3.4
        val vector1 = Vector(arg1_1, arg1_2, arg1_3)

        val arg2_1 = -0.55555
        val arg2_2 = 23.3
        val arg2_3 = 1010101010.0
        val vector2 = Vector(arg2_1, arg2_2, arg2_3)

        val expectedValue = (1..vector1.size)
                .map { index -> vector1.getValueOfDimension(index) * vector2.getValueOfDimension(index) }
                .sum()

        val product = vector1.dotProductWith(vector2)
        assertEquals(expectedValue, product, _delta)
    }

    @Test
    fun `is dot product zero if one vector with zeroes`() {
        val arg1_1 = 1.0
        val arg1_2 = 3.5
        val arg1_3 = -3.4
        val vector1 = Vector(arg1_1, arg1_2, arg1_3)

        val zeroesVector = Vector(3)

        val product = vector1.dotProductWith(zeroesVector)
        assertEquals(0.0, product, _delta)
    }

    @Test
    fun `dot product throws if size differs`() {

        val vector1 = Vector(1.1, 2.2)
        val vector2 = Vector(3.3)
        var thrown = false

        try {
            val product = vector1.dotProductWith(vector2)
        } catch (e : IllegalArgumentException) {
            thrown = true
        }
        assertTrue(thrown)
    }

    @Test
    fun `is multiply by works`() {
        val arg1_1 = 1.0
        val arg1_2 = 3.5
        val arg1_3 = -3.4
        val args = listOf<Double>(arg1_1, arg1_2, arg1_3)
        val vector1 = Vector(*args.toDoubleArray())
        val multiplier = -0.456

        vector1.multiplyBy(multiplier)

        for(index in 1..vector1.size) {
            val expectedValue = args[index-1]*multiplier
            assertEquals(expectedValue, vector1.getValueOfDimension(index), _delta)
        }
    }

    @Test
    fun `is cos between vectors correct`() {
        val arg1_1 = 1.0
        val arg1_2 = 3.5
        val arg1_3 = -3.4
        val vector1 = Vector(arg1_1, arg1_2, arg1_3)

        val arg2_1 = -0.55555
        val arg2_2 = 23.3
        val arg2_3 = 101.0
        val vector2 = Vector(arg2_1, arg2_2, arg2_3)

        val expectedCos = -0.50824442
        val cos = vector1.computeCosToVector(vector2)

        assertEquals(expectedCos, cos, _delta)
    }

    @Test
    fun `cos throws if vectors have different size`() {
        val vector1 = Vector(1.1, 2.2)
        val vector2 = Vector(3.3)
        var thrown = false

        try {
            val cos = vector1.computeCosToVector(vector2)
        } catch (e : IllegalArgumentException) {
            thrown = true
        }
        assertTrue(thrown)
    }

    @Test
    fun `add dimension works `() {
        val vector1 = Vector(1.1, 2.2)
        val initialSize = vector1.size
        assertEquals(initialSize, vector1.size)

        val addedValue = 3.5
        vector1.addDimension(addedValue)
        val newSize = initialSize + 1
        assertEquals(newSize, vector1.size)
        assertEquals(addedValue, vector1.getValueOfDimension(newSize), _delta)
    }

    @Test
    fun `add dimension set zero if not specified `() {
        val vector1 = Vector(1.1, 2.2)
        val initialSize = vector1.size
        assertEquals(initialSize, vector1.size)

        vector1.addDimension()
        val newSize = initialSize + 1
        assertEquals(newSize, vector1.size)
        assertEquals(0.0, vector1.getValueOfDimension(newSize), _delta)
    }

    @Test
    fun `remove dimension works `() {
        val vector1 = Vector(1.1, 2.2, 3.3, 4.4, 5.5)
        val initialArgs = vector1.getValues()
        val initialSize = vector1.size
        assertEquals(initialSize, vector1.size)

        val removingIndex = 3
        val removingValue = vector1.getValueOfDimension(removingIndex)
        vector1.removeDimension(removingIndex)
        val newSize = initialSize - 1
        assertEquals(newSize, vector1.size)
        val resultValues = vector1.getValues()
        assertFalse(resultValues.contains(removingValue))
        assertTrue(resultValues.all{ value -> initialArgs.contains(value) })
    }

    @Test
    fun `remove throws if wrong index `() {
        val vector1 = Vector(1.1, 2.2, 3.3, 4.4, 5.5)
        var thrown = false

        try {
            vector1.removeDimension(999)
        } catch (e : IllegalArgumentException) {
            thrown = true
        }

        assertTrue(thrown)
    }

    @Test
    fun `compare works`() {
        val vector1 = Vector(1.1, 2.2, 3.3, 4.4, 5.5)
        val vector2 = Vector(11.1, 2.2, 3.3, 4.4, 5.5)
        val vector3 = Vector(2.2, 11.1, 3.3, 4.4, 5.5) //vector3.length == vector2.length

        assertTrue("compare correct", vector1 < vector2)
        assertTrue("compare symmetric", vector2 > vector1)
        assertTrue("self compare leads to eq", vector2 == vector2)
        assertTrue("Same length vectors equal", vector2 >= vector3 && vector2 <= vector3)
    }

    @Test
    fun `sorting works`() {
        val vector1 = Vector(1.1, 2.2, 3.3, 4.4, 5.5)
        val vector2 = Vector(11.1, 2.2, 3.3, 4.4, 5.5)
        val vector3 = Vector(111.1, 2.2, 3.3, 4.4, 5.5)
        val vector4 = Vector(1111.1, 2.2, 3.3, 4.4, 5.5)

        val vectors = listOf<Vector>(vector3, vector1, vector4, vector2)
        val sortedVectors = vectors.sorted()

        assertTrue(sortedVectors.indexOf(vector1) == 0)
        assertTrue(sortedVectors.indexOf(vector2) == 1)
        assertTrue(sortedVectors.indexOf(vector3) == 2)
        assertTrue(sortedVectors.indexOf(vector4) == 3)
    }
}