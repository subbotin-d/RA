package ws02

import java.lang.Exception
import java.lang.IllegalArgumentException
import kotlin.math.cos
import kotlin.math.sqrt

open class Vector(size: Int): Comparable<Vector> {

    private val defaultInitValue = 0.0
    var vectorComponentsList: MutableList<Double>

    // 1. Конструктор "По умолчанию". Создает вектор, у которого все компоненты содержат нули (для N-мерного вектора, конструктор "по умолчанию" должен принимать размер)

    init {
        this.vectorComponentsList = initVectorComponentsList(size)
    }

    private fun initVectorComponentsList (size: Int, initValue: Double? = 0.0): MutableList<Double> =
    MutableList(size) { initValue ?: defaultInitValue }

    // 2. Конструктор, который позволяет создать объект с указанием 3-х компонент для трехмерного и N компонент N-мерного (можно использовать vararg для N-мерного)
    constructor(vararg vectorElements: Double): this(vectorElements.size) {
        vectorComponentsList = vectorElements.toMutableList()
    }


    // Реализовать операции, которые доступны с векторами:
    // 1. Опеределение длины вектора (вычисляется как квадратный корень из суммы квадратов всех компонент)

    fun length(incVector: Vector): Double = sqrt(incVector.vectorComponentsList.fold(0.0) { acc, current -> acc + current })

    // 2. Сложение и вычитание двух векторов (вычисляется как сумма/разность соответствующих координат вектора)

    fun vectorSubtraction(incVector1: Vector, incVector2: Vector): Vector? {
        return try {
            checkSize(incVector1, incVector2)
            val resultVector = Vector()
            for (n in 0 until this.vectorComponentsList.size) {
                resultVector.vectorComponentsList[n] = incVector1.vectorComponentsList[n].minus(incVector2.vectorComponentsList[n])
            }
            resultVector
        } catch (e: IllegalArgumentException) {
            println("Different vectors in length were passed to the input")
            null
        }
    }

    fun vectorAddition(incVector1: Vector, incVector2: Vector): Vector? {
        return try {
            checkSize(incVector1, incVector2)
            val resultVector = Vector()
            for (n in 0 until this.vectorComponentsList.size) {
                resultVector.vectorComponentsList[n] = incVector1.vectorComponentsList[n].plus(incVector2.vectorComponentsList[n])
            }
            resultVector
        } catch (e: IllegalArgumentException) {
            println("Different vectors in length were passed to the input")
            null
        }
    }

    private fun checkSize(incVector1: Vector, incVector2: Vector) {
        if (incVector1.vectorComponentsList.size != incVector2.vectorComponentsList.size) {
            throw IllegalArgumentException()
        }
    }

    // 3. Скалярное произведение двух векторов

    fun scalarProductOfVectors(incVector1: Vector, incVector2: Vector) : Double {
        return try {
            checkSize(incVector1, incVector2)
            var resultOfOperationUnderVectorComponents = 0.0
            for (n in 0 until this.vectorComponentsList.size) {
                resultOfOperationUnderVectorComponents += incVector1.vectorComponentsList[n] * incVector2.vectorComponentsList[n]
            }
            resultOfOperationUnderVectorComponents
        } catch (e: Exception) {
            println(e)
            0.0
        }
    }

    // 4. Умножение вектора на число

    fun multiplyingVectorByNumber(multiplier: Int, incVector: Vector): Vector {
        incVector.vectorComponentsList.map { vectorComponent -> multiplier * vectorComponent }
        return incVector
    }

    // 5. Вычисление косинуса между двумя векторами

    fun cosOfAngleBetweenTwoVectors(incVector1: Vector, incVector2: Vector) : Double {
        return try {
            checkSize(incVector1, incVector2)
            if (length(incVector1) != 0.0 && length(incVector2) != 0.0) {
                throw ArithmeticException()
            }
            val angleCalculationResult = cos(scalarProductOfVectors(incVector1, incVector2) / (length(incVector1) * length(incVector2)))
            angleCalculationResult
        } catch (e: ArithmeticException) {
            println("The length of one of the vectors is 0")
            0.0
        }
    }

    // 6. Добавление/удаление элемента
    // В реализации необходимо учесть что количество координат у N-мерного вектора может различаться, в этом случае
    //   необходимо кидать иксключение IllegalArgumentException - (см. https://kotlinlang.org/docs/exceptions.html оператор "throw")

    fun addComponentToVector(component: Double) = this.vectorComponentsList.add(component)

    fun deleteComponentOfVector(component: Double) = this.vectorComponentsList.remove(component)

    // 2.2 *** Реализовать механизм сравнения(исключая равенство) двух векторов по длине. (Пример использования: vector1 < vector2)
    // См. https://kotlinlang.org/docs/operator-overloading.html#comparison-operators

    override fun compareTo(other: Vector) : Int = when {
            this.length(this) > length(other) -> 1
            this.length(this) < length(other) -> -1
            else -> 0
    }


    // 2.3 *** Создать список векторов и отсортировать их в порядке убывания с помощью метода списка sorted() (list.sorted())
    // Tip. Необходимо реализовать интерфейс Comparable<Vector> / Comparable<Vector3>
    val vector1 = Vector3(33.0, 2.0, 11.0)
    val vector2 = Vector3(170.0, 4.0, 22.0)
    val vector3 = Vector3(144.0, 66.0, 22.0)
    val vector4 = Vector3(124.0, 26.0, 12.0)
    val vectorList = listOf<Vector3>(vector1, vector3, vector2, vector4)
    val sortedVectors = vectorList.sorted()


}


