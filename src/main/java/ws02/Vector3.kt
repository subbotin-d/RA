package ws02

class Vector3 : Vector {
    constructor() : super(3)
    constructor(x: Double, y: Double, z: Double) : super(x, y, z)

    var x : Double
        get() = getValueOfDimension(1)
        set(value) = setValueOfDimension(1, value)

    var y : Double
        get() = getValueOfDimension(2)
        set(value) = setValueOfDimension(2, value)

    var z : Double
        get() = getValueOfDimension(3)
        set(value) = setValueOfDimension(3, value)
}