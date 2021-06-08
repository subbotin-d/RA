package ws02.solution

import ws02.*

//TODO 1.1. Реализовать функцию getCustomerCities,
// которая должна возвращать все города клиентов в единственном экземпляре
fun Shop.getCustomerCities(): Set<City> = this.customers.map { customer -> customer.city }.toSet()

//TODO 1.2. Реализовать функцию getCustomersFromCity,
// которая возвращает всех клиентов из указанного города city
fun Shop.getCustomersFromCity(city: City): List<Customer> = this.customers.filter { customer -> customer.city == city }

//TODO 1.3. Реализовать функцию getTotalAmount,
// которая возвращает итоговую сумму заказа
fun Order.getTotalAmount(): Double = this.products.sumByDouble { product -> product.price }

//TODO 1.4. Реализовать функцию getCustomersWithAtLeastOneDeliveredOrder, возвращающая клиентов,
// которые имеют хотя бы один доставленный заказ (isDelivered == true)
fun Shop.getCustomersWithAtLeastOneDeliveredOrder(): List<Customer> = this.customers.filter { customer ->
    customer.orders.any { order -> order.isDelivered }
}

//TODO 1.5*. Реализовать функцию getCustomersWithTotalAmountMoreThan, возвращающая клиентов,
// которые заказали товары суммарно по всем заказам больше(либо равно) указанной суммы
fun Shop.getCustomersWithTotalAmountMoreThan(amount: Double): List<Customer> =
    this.customers.filter { customer ->
        customer.getOrdersTotalPrice() >= amount
    }

private fun Customer.getOrdersTotalPrice(): Double = this.orders.sumByDouble { order -> order.getTotalAmount() }

//TODO 1.6*. Реализовать функцию,
// которая возвращает самый популярный продукт в магазине (по количеству продукта во всех заказах всех покупателей)
// Если продукт не найден, то необходимо вернуть null - (см. https://kotlinlang.org/docs/null-safety.html)
fun Shop.getMostPopularProduct(): Product? = getAllOrderedProducts().maxBy { product -> getCountOfProduct(product) }

private fun Shop.getAllOrderedProducts(): Set<Product> = this.customers.flatMap { customer -> customer.orders }
    .flatMap { order -> order.products }
    .toSet()

private fun Shop.getCountOfProduct(product: Product): Int = this.customers.flatMap { customer -> customer.orders }
    .flatMap { order -> order.products }
    .count { it == product }