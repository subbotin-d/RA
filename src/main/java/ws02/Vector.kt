package ws02

import java.lang.IllegalArgumentException
import kotlin.math.pow
import kotlin.math.sqrt

open class Vector(initialSize : Int) : Comparable<Vector> {

    private val _dimensions : MutableList<Double> = MutableList<Double>(initialSize) { _ -> 0.0 }

    constructor(vararg values : Double) : this(values.size) {
        var index = 0
        for(value in values) {
            _dimensions[index] = value
            index++
        }
    }

    val size : Int
        get() = _dimensions.size

    val length : Double
        get() = sqrt(_dimensions.sumByDouble { value -> value.pow(2) })


    fun getValues() : List<Double> = _dimensions.toList()

    fun getValueOfDimension(dimensionIndex : Int) : Double {
        var normalizedIndex = getNormalizedIndex(dimensionIndex)
        ensureIndexInAvailableRange(normalizedIndex)
        return getValueOfDimensionInternal(normalizedIndex)
    }

    private fun getValueOfDimensionInternal(index : Int) : Double {
        return _dimensions[index]
    }

    fun setValueOfDimension(dimensionIndex: Int, value : Double) {
        var normalizedIndex = getNormalizedIndex(dimensionIndex)
        ensureIndexInAvailableRange(normalizedIndex)
        setValueOfDimensionInternal(normalizedIndex, value)
    }

    private fun setValueOfDimensionInternal(index: Int, value : Double) {
        _dimensions[index] = value
    }

    fun dotProductWith(anotherVector: Vector) : Double {
        ensureSameSizeWith(anotherVector)
        var product = 0.0
        for(index in 0 until size) {
            product += (this.getValueOfDimensionInternal(index) * anotherVector.getValueOfDimensionInternal(index))
        }
        return product
    }

    fun multiplyBy(multiplier : Double) {
        for(index in 0 until size) {
            setValueOfDimensionInternal(index, getValueOfDimensionInternal(index) * multiplier)
        }
    }

    fun computeCosToVector(anotherVector: Vector) : Double {
        ensureSameSizeWith(anotherVector)
        return (dotProductWith(anotherVector) / (this.length * anotherVector.length))
    }

    fun addDimension(value: Double = 0.0) {
        _dimensions.add(value)
    }

    fun removeDimension(dimensionIndex: Int) {
        val normalizedIndex = getNormalizedIndex(dimensionIndex)
        ensureIndexInAvailableRange(normalizedIndex)
        _dimensions.removeAt(normalizedIndex)
    }

    operator fun plus(anotherVector : Vector) : Vector =
            performJoinOperation(anotherVector) { currentValue, anotherValue -> currentValue + anotherValue }

    operator fun minus(anotherVector: Vector) : Vector =
            performJoinOperation(anotherVector) { currentValue, anotherValue -> currentValue - anotherValue }

    private fun performJoinOperation(anotherVector : Vector, operation: (Double, Double) -> Double) : Vector {
        ensureSameSizeWith(anotherVector)
        val resultVector = Vector(size)
        for(index in 0 until size) {
            val newValue = operation(getValueOfDimensionInternal(index), anotherVector.getValueOfDimensionInternal(index))
            resultVector.setValueOfDimensionInternal(index, newValue)
        }
        return resultVector
    }

    override operator fun compareTo(anotherVector: Vector) : Int {
        val lengthDiff = length - anotherVector.length
        return when {
            lengthDiff > 0.0 -> 1
            lengthDiff < 0.0 -> -1
            else -> 0
        }
    }

    private fun getNormalizedIndex(dimensionIndex : Int) : Int = dimensionIndex-1; //dims 1-based, list 0-based

    private fun ensureIndexInAvailableRange(index : Int) {
        if(index < 0 || index > size - 1)
            throw IllegalArgumentException("Forbidden dimension ${index + 1} for vector with $size dimensions")
    }

    private fun ensureSameSizeWith(anotherVector: Vector) {
        if(this.size != anotherVector.size)
            throw IllegalArgumentException("Can't perform operation with vectors of different size ($size and ${anotherVector.size})")
    }

    override fun toString(): String  {
        return if(_dimensions.isEmpty())
             "[]"
        else
            _dimensions.joinToString(separator = ", ", prefix = "[", postfix = "]")
    }
}