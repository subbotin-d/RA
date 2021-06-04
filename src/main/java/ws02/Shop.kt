package ws02

data class City(val name: String)

data class Product(val name: String, val price: Double)

data class Order(val products: List<Product>, val isDelivered: Boolean)

data class Customer(val name: String, val city: City, val orders: List<Order>) {
    override fun toString() = "$name + ${city.name}"
}

data class Shop(val name: String, val customers: List<Customer>)