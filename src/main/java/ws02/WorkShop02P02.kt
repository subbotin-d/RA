package ws02

import java.lang.IllegalArgumentException
import kotlin.math.sqrt

//TODO 2.1 Релизовать 2 класса, которые представляют собой "вектор"
// https://ru.wikipedia.org/wiki/%D0%92%D0%B5%D0%BA%D1%82%D0%BE%D1%80_(%D0%B3%D0%B5%D0%BE%D0%BC%D0%B5%D1%82%D1%80%D0%B8%D1%8F)
// Первый класс должен представлять из себя трехмерный вектор (Vector3)
// Второй класс должен представлять из себя N-мерный вектор (Vector)
// /
// /
// У объектов каждого из классов должны быть доступны свойства x,y,z для трехмерного и x1, x2, x3, x4 и т.д. для N-мерного
// /
// /
// Должны быть реализованы конструкторы:
// 1. Конструктор "По умолчанию". Создает вектор, у которого все компоненты содержат нули
// (для N-мерного вектора, конструктор "по умолчанию" должен принимать размер)
// 2. Конструктор, который позволяет создать объект с указанием 3-х компонент для трехмерного и
// N компонент N-мерного (можно использовать vararg для N-мерного)
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

//TODO 2.2 *** Реализовать механизм сравнения(исключая равенство) двух векторов по длине. (Пример использования: vector1 < vector2)
// См. https://kotlinlang.org/docs/operator-overloading.html#comparison-operators

//TODO 2.3 *** Создать список векторов и отсортировать их в порядке возрастания с помощью метода списка sorted() (list.sorted())
// Tip. Необходимо реализовать интерфейс Comparable<Vector> / Comparable<Vector3>

//1. Конструктор "По умолчанию". Создает вектор, у которого все компоненты содержат нули
// (для N-мерного вектора, конструктор "по умолчанию" должен принимать размер)
open class Vector(size: Int): Comparable<Vector> {

    var elements = mutableListOf<Double>()

    init {
        for (i in 0 until size) {
            elements.add(0.0)
        }
    }

    // 2. Конструктор, который позволяет создать объект с указанием 3-х компонент для трехмерного и
    // N компонент N-мерного (можно использовать vararg для N-мерного)
    constructor(vararg elements: Double): this(elements.size) {
        this.elements = elements.toMutableList()
    }

    constructor(elements: List<Double>): this(elements.size) {
        this.elements = elements.toMutableList()
    }

    // 1. Опеределение длиный вектора (вычисляется как квадратный корень из суммы квадратов всех компонент)
    fun length(): Double = sqrt(elements.fold(0.0) { acc, current -> acc + current * current })

    // 2. Сложение и вычитание двух векторов (вычисляется как сумма/разность соответствующих координат вектора)
    fun mergeVector(other: Vector): Vector {
        if (other.elements.size != this.elements.size) {
            throw IllegalArgumentException()
        } else {
            val elements = mutableListOf<Double>()
            for(i in 0 until this.elements.size) {
                elements.add(this.elements[i] + other.elements[i])
            }
            return Vector(elements)
        }
    }

    fun subVector(other:Vector): Vector {
        if (other.elements.size != this.elements.size) {
            throw IllegalArgumentException()
        } else {
            val elements = mutableListOf<Double>()
            for(i in 0 until this.elements.size) {
                elements.add(this.elements[i] - other.elements[i])
            }
            return Vector(elements)
        }
    }

    // 3. Скалярное произведение двух векторов
    fun scaleVector(other: Vector): Double {
        if (other.elements.size != this.elements.size) {
            throw IllegalArgumentException()
        } else {
            var result = 0.0
            for(i in 0 until this.elements.size) {
                result += this.elements[i] * other.elements[i]
            }
            return result
        }
    }

    // 4. Умножение вектора на число
    fun multiVector(num: Int) = Vector(this.elements.map { number -> number * num })

    // 5. Вычисление косинуса между двумя векторами
    fun cos(other: Vector): Double = when {
                // думаю, что у векторов разной размерности не может быть угла :))
                this.elements.size != other.elements.size -> throw IllegalArgumentException()
                length() == 0.0 || other.length() == 0.0  -> throw ArithmeticException()
                else -> kotlin.math.cos((scaleVector(other) / length() * other.length()))
            }

    // 6. Добавление/удаление элемента
    fun addElement(element: Double) { elements.add(element) }

    fun removeElement(element: Double) {
        if (elements.any { it == element }) {
            elements.remove(element)
        } else {
            throw IllegalArgumentException()
        }
    }

    override fun compareTo(other: Vector) = when {
                this.length() < other.length() -> -1
                this.length() > other.length() -> 1
                else -> 0
            }

    override fun toString(): String = elements.toString()
}

// Первый класс должен представлять из себя трехмерный вектор (Vector3)
// 1. Конструктор "По умолчанию". Создает вектор, у которого все компоненты содержат нули
class Vector3: Vector {

    constructor(): super(3)

    constructor(x: Double, y: Double, z: Double): super(x, y, z)
}

fun main() {
    val vector1 = Vector3(2.0,1.0,0.0)
    val vector2 = Vector3(1.0, 2.0, 3.0)
    val vector3 = Vector3(0.0, 0.0, 0.0)
    val vector4 = Vector(1.0,2.0,3.0)
    val vector5 = Vector(2.0,3.0,4.0,5.0)
    val vector6 = Vector(3.0,4.0,5.0,6.0)

    val vectors = listOf(vector1, vector2, vector3, vector4, vector5, vector6)
    println(vectors)
    println(vectors.sorted())
}