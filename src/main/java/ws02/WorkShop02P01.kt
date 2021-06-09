package ws02

//TODO 1.1. Реализовать функцию getCustomerCities,
// которая должна возвращать все города клиентов в единственном экземпляре
fun Shop.getCustomerCities(): Set<City> = customers.map { it.city }.toSet()

//TODO 1.2. Реализовать функцию getCustomersFromCity,
// которая возвращает всех клиентов из указанного города city
fun Shop.getCustomersFromCity(city: City): List<Customer> = customers.filter { it.city == city }

//TODO 1.3. Реализовать функцию getTotalAmount,
// которая возвращает итоговую сумму заказа
fun Order.getTotalAmount(): Double = products.fold(initial = 0.0) { acc: Double, product: Product ->
    acc + product.price
}

//TODO 1.4. Реализовать функцию getCustomersWithAtLeastOneDeliveredOrder, возвращающая клиентов,
// которые имеют хотя бы один доставленный заказ (isDelivered == true)
fun Shop.getCustomersWithAtLeastOneDeliveredOrder(): List<Customer> = customers.filter {
    it.orders.any { el -> el.isDelivered }
}

//TODO 1.5*. Реализовать функцию getCustomersWithTotalAmountMoreThan, возвращающая клиентов,
// которые заказали товары суммарно по всем заказам больше(либо равно) указанной суммы
fun Shop.getCustomersWithTotalAmountMoreThan(amount: Double): List<Customer> = customers.filter {
    it.orders.fold(0.0) { acc, order -> acc + order.getTotalAmount() } >= amount
}

//TODO 1.6*. Реализовать функцию,
// которая возвращает самый популярный продукт в магазине (по количеству продукта во всех заказах всех покупателей)
// Если продукт не найден, то необходимо вернуть null - (см. https://kotlinlang.org/docs/null-safety.html)
fun Shop.getMostPopularProduct(): Product? = customers
    .flatMap { customer -> customer.orders }
    .flatMap { order -> order.products }
    .groupBy { product -> product.name }
    .maxBy { el -> el.value.size }
    ?.value
    ?.get(0)