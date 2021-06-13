package ws02

import java.lang.Exception
import java.lang.IllegalArgumentException
import kotlin.math.cos
import kotlin.math.sqrt

open class Vector(size: Int) : Comparable<Vector> {

    // 1. Конструктор "По умолчанию". Создает вектор, у которого все компоненты содержат нули (для N-мерного вектора, конструктор "по умолчанию" должен принимать размер)


    var vectorComponentsList = mutableListOf<Double>()

    init {
        for (n in 0 until size){
            vectorComponentsList.add(0.0)
        }
    }
    // 2. Конструктор, который позволяет создать объект с указанием 3-х компонент для трехмерного и N компонент N-мерного (можно использовать vararg для N-мерного)
    constructor(vararg vectorElements: Double): this(vectorElements.size) {
        this.vectorComponentsList = vectorElements.toMutableList()
    }


    // Реализовать операции, которые доступны с векторами:
    // 1. Опеределение длины вектора (вычисляется как квадратный корень из суммы квадратов всех компонент)

    fun determineLengthOfVector(vectorComponents: List<Double>): Double = sqrt(vectorComponents.fold(0.0) { acc, current -> acc + current })

    // 2. Сложение и вычитание двух векторов (вычисляется как сумма/разность соответствующих координат вектора)

    fun vectorSubtraction(incVector: Vector) {
        try {
            if (this.vectorComponentsList.size != incVector.vectorComponentsList.size) {
                throw IllegalArgumentException()
            }
            val resultOfOperationUnderVectorComponents = mutableListOf<Double>()
            for (n in 0 until this.vectorComponentsList.size) {
                resultOfOperationUnderVectorComponents.add(this.vectorComponentsList[n] - incVector.vectorComponentsList[n])
                this.vectorComponentsList[n] = resultOfOperationUnderVectorComponents[n]
            }
        } catch (e: Exception) {
            println(e)
        }
    }

    fun vectorAddition(incVector: Vector) {
        try {
            if (this.vectorComponentsList.size != incVector.vectorComponentsList.size) {
                throw IllegalArgumentException()
            }
            val resultOfOperationUnderVectorComponents = mutableListOf<Double>()
            for (n in 0 until this.vectorComponentsList.size) {
                resultOfOperationUnderVectorComponents.add(this.vectorComponentsList[n] + incVector.vectorComponentsList[n])
                this.vectorComponentsList[n] = resultOfOperationUnderVectorComponents[n]
            }
        } catch (e: Exception) {
            println(e)
        }
    }

    // 3. Скалярное произведение двух векторов

    fun scalarProductOfVectors(incVector: Vector) : Double? {
        return try {
            if (this.vectorComponentsList.size != incVector.vectorComponentsList.size) {
                throw IllegalArgumentException()
            }
            var resultOfOperationUnderVectorComponents = 0.0
            for (n in 0 until this.vectorComponentsList.size) {
                resultOfOperationUnderVectorComponents += this.vectorComponentsList[n] * incVector.vectorComponentsList[n]
            }
            resultOfOperationUnderVectorComponents
        } catch (e: Exception) {
            println(e)
            null
        }
    }

    // 4. Умножение вектора на число

    fun multiplyingVectorByNumber(multiplier: Int) = this.vectorComponentsList.map { vectorComponent -> multiplier * vectorComponent}

    // 5. Вычисление косинуса между двумя векторами

    fun cosineOfAngleBetweenTwoVectors(incVector: Vector) : Double? {
        return try {
            if (this.vectorComponentsList.size != incVector.vectorComponentsList.size) {
                throw IllegalArgumentException()
            }
            if (determineLengthOfVector(this.vectorComponentsList) != 0.0 && determineLengthOfVector(incVector.vectorComponentsList) != 0.0) {
                throw ArithmeticException()
            }
            val angleCalculationResult = cos(scalarProductOfVectors(incVector)!! / (determineLengthOfVector(this.vectorComponentsList) * determineLengthOfVector(incVector.vectorComponentsList)))
            angleCalculationResult
        } catch (e: Exception) {
            println(e)
            null
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
            this.determineLengthOfVector(this.vectorComponentsList) > determineLengthOfVector(other.vectorComponentsList) -> 1
            this.determineLengthOfVector(this.vectorComponentsList) < determineLengthOfVector(other.vectorComponentsList) -> -1
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


