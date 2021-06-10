package ws02

import kotlin.math.abs

//TODO 2.1 Релизовать 2 класса, которые представляют собой "вектор" https://ru.wikipedia.org/wiki/%D0%92%D0%B5%D0%BA%D1%82%D0%BE%D1%80_(%D0%B3%D0%B5%D0%BE%D0%BC%D0%B5%D1%82%D1%80%D0%B8%D1%8F)
// Первый класс должен представлять из себя трехмерный вектор (Vector3)
// Второй класс должен представлять из себя N-мерный вектор (Vector)
// /
// /
// У объектов каждого из классов должны быть доступны свойства x,y,z для трехмерного и x1, x2, x3, x4 и т.д. для N-мерного
// /
// /
// Должны быть реализованы конструкторы:
// 1. Конструктор "По умолчанию". Создает вектор, у которого все компоненты содержат нули (для N-мерного вектора, конструктор "по умолчанию" должен принимать размер)
// 2. Конструктор, который позволяет создать объект с указанием 3-х компонент для трехмерного и N компонент N-мерного (можно использовать vararg для N-мерного)
// /
// /
// Реализовать операции, которые доступны с векторами:
// 1. Опеределение длиный вектора (вычисляется как квадратный корень из суммы квадратов всех компонент)
// 2. Сложение и вычитание двух векторов (вычисляется как сумма/разность соответствующих координат вектора)
// 3. Скалярное произведение двух векторов
// 4. Умножение вектора на число
// 5. Вычисление косинуса между двумя векторами
// 6. Добавление/удаление элемента
// В реализации необходимо учесть что количество координат у N-мерного вектора может различаться, в этом случае
//   необходимо кидать иксключение IllegalArgumentException - (см. https://kotlinlang.org/docs/exceptions.html оператор "throw")
open class Vector {
    private val _coordinates: ArrayList<Double>
    val coordinates: List<Double> get() = _coordinates

    constructor(rank: Int) {
        if (rank < 0)
            throw IllegalArgumentException()

        _coordinates = ArrayList(rank)
    }

    constructor(vector: Vector) {
        _coordinates = ArrayList(vector._coordinates)
    }

    constructor(vararg coordinates: Double) : this(coordinates.toList())

    protected constructor(coordinates: List<Double>) : this(coordinates.size) {
        this._coordinates.addAll(coordinates.toList())
    }

    /**
     * Опеределение длины вектора
     */
    fun getModule() = abs(_coordinates.sumByDouble { it * it })

    private fun validateVectors(first: Vector, second: Vector) {
        if (first._coordinates.size != second._coordinates.size)
            throw IllegalArgumentException("Длина векторов должна быть одинаковой")
    }

    /**
     * Сложение двух векторов
     */
    operator fun plus(other: Vector): Vector {
        validateVectors(this, other)

        val resultVector = Vector(this)

        for (i in resultVector._coordinates.indices)
            resultVector[i] += other[i]

        return resultVector
    }

    /**
     * Вычитание двух векторов
     */
    operator fun minus(other: Vector): Vector {
        validateVectors(this, other)

        val resultVector = Vector(this)

        for (i in resultVector._coordinates.indices)
            resultVector[i] -= other[i]

        return resultVector
    }

    operator fun get(index: Int) = _coordinates[index]

    operator fun set(index: Int, value: Double) {
        _coordinates[index] = value
    }

    /**
     * Умножение вектора на число
     */
    operator fun times(number: Double): Vector {
        val resultVector = Vector(this)

        for (i in resultVector._coordinates.indices)
            resultVector[i] *= number

        return resultVector
    }

    /**
     * Скалярное произведение двух векторов
     */
    operator fun times(other: Vector): Double {
        validateVectors(this, other)

        var resultScalarMul = 0.0

        for (i in _coordinates.indices)
            resultScalarMul += this[i] * other[i]

        return resultScalarMul
    }

    /**
     * Вычисление косинуса между двумя векторами
     */
    fun cos(other: Vector): Double {
        val thisModule = getModule()
        val otherModule = other.getModule()

        if (thisModule == 0.0 || otherModule == 0.0)
            throw ArithmeticException()

        return (this * other) / (thisModule * otherModule)
    }

    /**
     * Добавление элемента
     */
    open fun add(coordinate: Double) {
        _coordinates.add(coordinate)
    }

    /**
     * Удаление элемента
     */
    open fun remote(coordinate: Double) {
        _coordinates.remove(coordinate)
    }
}

class Vector3 : Vector {
    val x: Double get() = this[0]
    val y: Double get() = this[1]
    val z: Double get() = this[2]

    constructor() : this(0.0, 0.0, 0.0)
    constructor(x: Double, y: Double, z: Double) : super(x, y, z)

    override fun add(coordinate: Double) {
        throw  IllegalStateException("В трёхмерный вектор нельзя добавлять элементы")
    }

    override fun remote(coordinate: Double) {
        throw  IllegalStateException("Из трёхмерного вектора нельзя удалять элементы")
    }
}
//TODO 2.2 *** Реализовать механизм сравнения(исключая равенство) двух векторов по длине. (Пример использования: vector1 < vector2)
// См. https://kotlinlang.org/docs/operator-overloading.html#comparison-operators

//TODO 2.3 *** Создать список векторов и отсортировать их в порядке возрастания с помощью метода списка sorted() (list.sorted())
// Tip. Необходимо реализовать интерфейс Comparable<Vector> / Comparable<Vector3>