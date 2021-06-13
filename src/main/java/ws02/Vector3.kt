package ws02

class Vector3: Vector, Comparable<Vector> {

    constructor(): super(3)

    constructor(x: Double, y: Double, z: Double): super(x, y, z)
}