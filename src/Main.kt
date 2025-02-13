data class Car(
    val brand: String,
    val model: String,
    val country: String,
    val year: Int,
    val volumeEngine: Double,
    val fuelConsumption: Double,
    val price: Int,
    val count: Int
)

class CarDealership{
    private var cars = mutableListOf<Car>()

    fun addCar(car: Car){
        cars.add(car)
    }

    fun getCountCar(): Int{
        return cars.size
    }
    infix fun searchOfBrand(brand: String): List<Car>{
        return cars.filter {it.brand.equals(brand, ignoreCase = false)}
    }

    infix fun searchOfModel(model: String): List<Car>{
        return cars.filter {it.model.equals(model, ignoreCase = false)}
    }

    infix fun priceOnYear(year: Int): List<Pair<Car, Int>>{
        return cars.filter {it.year == year}.map{it to it.price}
    }

    operator fun unaryPlus(): Int{
        return getCountCar()
    }
    // Функция высшего порядка для фильтрации автомобилей
    fun filterCars(predicate: (Car) -> Boolean): List<Car> {
        return cars.filter(predicate)
    }

}
infix fun CarDealership.add(car: Car) {
    addCar(car)
}
operator fun Car.compareTo(other: Car): Int{
    return this.price.compareTo(other.price)
}

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val dealership = CarDealership()

    // Добавление автомобилей
    dealership add Car("Lada", "Priora", "Russia", 2009, 2.5, 8.0, 300000, 10)
    dealership add Car("Honda", "Accord", "Japan", 2019, 2.0, 7.5, 280000, 5)
    dealership add Car("Ford", "Focus", "USA", 2021, 1.5, 6.5, 250000, 8)

    // Поиск по марке
    val toyotaCars = dealership searchOfBrand "Lada"
    println("Поиск по марке Toyota: $toyotaCars")

    // Поиск по модели
    val hondaCars = dealership searchOfModel "Accord"
    println("Поиск по модели Accord: $hondaCars")

    // Получение стоимости автомобилей по году
    val carsFrom2020 = dealership.priceOnYear(2009)
    println("Стоимость автомобилей 2020 года: $carsFrom2020")

    // Использование функции высшего порядка для фильтрации
    val expensiveCars = dealership.filterCars { it.price > 25000 }
    println("Дорогие автомобили: $expensiveCars")

    // Получение количества автомобилей
    val totalCars = +dealership
    println("Всего автомобилей в магазине: $totalCars")
}