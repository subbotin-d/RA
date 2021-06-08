package ws02.solution

import kotlin.math.pow
import kotlin.math.sqrt

//TODO 2.1 Релизовать 2 класса, которые представляют собой "вектор" https://ru.wikipedia.org/wiki/%D0%92%D0%B5%D0%BA%D1%82%D0%BE%D1%80_(%D0%B3%D0%B5%D0%BE%D0%BC%D0%B5%D1%82%D1%80%D0%B8%D1%8F)
// Второй класс должен представлять из себя N-мерный вектор (Vector)
open class Vector : Comparable<Vector> {

    companion object {
        private const val INITIAL_VALUE = 0.0
    }

    private var components: MutableList<Double>

    // Должны быть реализованы конструкторы:
    // 1. Конструктор "По умолчанию". Создает вектор, у которого все компоненты содержат нули (для N-мерного вектора, конструктор "по умолчанию" должен принимать размер)
    constructor(size: Int) {
        components = MutableList(size) { INITIAL_VALUE }
    }

    // Должны быть реализованы конструкторы:
    // 2. Конструктор, который позволяет создать объект с указанием 3-х компонент для трехмерного и N компонент N-мерного (можно использовать vararg для N-мерного)
    constructor(vararg component: Double) {
        components = component.toMutableList()
    }

    constructor(components: List<Double>) {
        this.components = components.toMutableList()
    }

    val size: Int
        get() = components.size

    // Реализовать операции, которые доступны с векторами:
    // 1. Опеределение длиный вектора (вычисляется как квадратный корень из суммы квадратов всех компонент)
    val length: Double
        get() = sqrt(components.sumByDouble { value -> value.pow(2) })

    // У объектов каждого из классов должны быть доступны свойства x,y,z для трехмерного и x1, x2, x3, x4 и т.д. для N-мерного
    operator fun get(index: Int): Double = this.components[index]

    // Реализовать операции, которые доступны с векторами:
    // 2. Сложение и вычитание двух векторов (вычисляется как сумма/разность соответствующих координат вектора)
    operator fun minus(anotherVector: Vector): Vector {
        requireSameSize(anotherVector)

        return components.mapIndexed { index, num ->
            num - anotherVector[index]
        }
            .asVector()
    }

    // Реализовать операции, которые доступны с векторами:
    // 2. Сложение и вычитание двух векторов (вычисляется как сумма/разность соответствующих координат вектора)
    operator fun plus(anotherVector: Vector): Vector {
        requireSameSize(anotherVector)

        return components.mapIndexed { index, num ->
            num + anotherVector[index]
        }
            .asVector()
    }

    // Реализовать операции, которые доступны с векторами:
    // 3. Скалярное произведение двух векторов
    fun scalarProduct(anotherVector: Vector): Double {
        requireSameSize(anotherVector)

        return components.mapIndexed { index, num ->
            num * anotherVector[index]
        }
            .sum()
    }

    // Реализовать операции, которые доступны с векторами:
    // 4. Умножение вектора на число
    operator fun times(multiplier: Int): Vector = components.map { component -> component * multiplier }.asVector()
    operator fun times(multiplier: Double): Vector = components.map { component -> component * multiplier }.asVector()

    // Реализовать операции, которые доступны с векторами:
    // 5. Вычисление косинуса между двумя векторами
    fun cos(anotherVector: Vector) = scalarProduct(anotherVector) / (this.length * anotherVector.length)

    // Реализовать операции, которые доступны с векторами:
    // В реализации необходимо учесть что количество координат у N-мерного вектора может различаться, в этом случае
    //   необходимо кидать иксключение IllegalArgumentException - (см. https://kotlinlang.org/docs/exceptions.html оператор "throw")
    private fun requireSameSize(vector: Vector) {
        if (this.size != vector.size) throw IllegalArgumentException("A vector's size should be the same")
    }

    private fun List<Double>.asVector(): Vector = Vector(this)

    // Реализовать операции, которые доступны с векторами:
    // 6. Добавление/удаление элемента
    open fun add(value: Double): Boolean = components.add(value)

    // Реализовать операции, которые доступны с векторами:
    // 6. Добавление/удаление элемента
    open fun remove(index: Int): Double = components.removeAt(index)
    open fun remove(value: Double): Boolean = components.remove(value)

    //TODO 2.2 *** Реализовать механизм сравнения(исключая равенство) двух векторов по длине. (Пример использования: vector1 < vector2)
    // См. https://kotlinlang.org/docs/operator-overloading.html#comparison-operators

    //TODO 2.3 *** Создать список векторов и отсортировать их в порядке возрастания с помощью метода списка sorted() (list.sorted())
    // Tip. Необходимо реализовать интерфейс Comparable<Vector> / Comparable<Vector3>
    override operator fun compareTo(other: Vector): Int = when {
        (length - other.length) > 0 -> 1
        (length - other.length) < 0 -> -1
        else -> 0
    }

    override fun toString(): String = components.joinToString(prefix = "[", postfix = "]")
}

//TODO 2.1 Релизовать 2 класса, которые представляют собой "вектор" https://ru.wikipedia.org/wiki/%D0%92%D0%B5%D0%BA%D1%82%D0%BE%D1%80_(%D0%B3%D0%B5%D0%BE%D0%BC%D0%B5%D1%82%D1%80%D0%B8%D1%8F)
// Первый класс должен представлять из себя трехмерный вектор (Vector3)
class Vector3 : Vector {

    // У объектов каждого из классов должны быть доступны свойства x,y,z для трехмерного и x1, x2, x3, x4 и т.д. для N-мерного
    val x: Double
        get() = this[0]
    val y: Double
        get() = this[1]
    val z: Double
        get() = this[2]

    // Должны быть реализованы конструкторы:
    // 1. Конструктор "По умолчанию". Создает вектор, у которого все компоненты содержат нули (для N-мерного вектора, конструктор "по умолчанию" должен принимать размер)
    constructor() : super(3)

    // Должны быть реализованы конструкторы:
    // 2. Конструктор, который позволяет создать объект с указанием 3-х компонент для трехмерного и N компонент N-мерного (можно использовать vararg для N-мерного)
    constructor(x: Double, y: Double, z: Double) : super(x, y, z)

    override fun add(value: Double): Boolean {
        throw NotImplementedError("There is no sense to add value to fixed vector")
    }

    override fun remove(index: Int): Double {
        throw NotImplementedError("There is no sense to remove value to fixed vector")
    }

    override fun remove(value: Double): Boolean {
        throw NotImplementedError("There is no sense to remove value to fixed vector")
    }
}

fun main(args: Array<String>) {
    val v1 = Vector(0.0, 1.0, 2.0)
    val v2 = Vector(3.0, 4.0, 5.0)
    val v3 = Vector(0.0, 0.0, 0.1, 0.2)
    val v4 = Vector(0.0, 1.0, 2.0)

    //операртор compareTo
    println("v1 < v2 = " + (v1 < v2)) //v1 < v2 = true
    println("v2 > v1 = " + (v2 > v1)) //v2 > v1 = true
    println("v2 < v1 = " + (v2 < v1)) //v2 < v1 = false

    //операторы times
    println("v1 * 2 = " + (v1 * 2)) //v1 * 2 = [0.0, 2.0, 4.0]
    println("v1 * 2.1 = " + (v1 * 2.1)) //v1 * 2.1 = [0.0, 2.1, 4.2]

    //операторы plus,minus
    println("v1 + v2 = " + (v1 + v2)) //v1 + v2 = [3.0, 5.0, 7.0]
    println("v2 - v1 = " + (v2 - v1)) //v2 - v1 = [3.0, 3.0, 3.0]

    //оператор get(index)
    println("v1[0] = " + v1[0]) //v1[0] = 0.0

    //операртор compareTo
    val listOfVector = listOf<Vector>(v2, v1, v3, v4)
    println("sorted listOfVector = ${listOfVector.sorted()}") //sorted listOfVector = [[0.0, 0.0, 0.1, 0.2], [0.0, 1.0, 2.0], [0.0, 1.0, 2.0], [3.0, 4.0, 5.0]]
}