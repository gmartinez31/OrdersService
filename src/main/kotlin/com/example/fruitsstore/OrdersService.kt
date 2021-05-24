package com.example.fruitsstore

import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class OrdersService {

	fun calculateTotal(order: String): Double {
		val fruits = validateOrder(order)
		val total: Double
		var appleCount = 0
		var orangeCount = 0

		for (fruit in fruits) {
			if (fruit.toLowerCase() == "apple") {
				appleCount++
			}
			if (fruit.toLowerCase() == "orange") {
				orangeCount++
			}
		}

		if (appleCount % 2 != 0) {
			appleCount = appleCount/2 + 1
		} else {
			appleCount/=2
		}
		val applePrice = appleCount * .60


		if (orangeCount > 2) {
			orangeCount = (orangeCount*2) / 3
		}
		val orangePrice = orangeCount * .25

		total = applePrice + orangePrice
		return total
	}

	fun validateOrder(order: String): List<String> {
		if (order.isEmpty()) {
			println("Order is blank...")
			throw RuntimeException("Order is blank. Please try again.")
		}

		val orders = order.split(", ")

		for (fruit in orders) {
			if (fruit.toLowerCase() != "apple" && fruit.toLowerCase() != "orange") {
				println("Bad order. Check that only apples and/or oranges are included in the order.")
				throw RuntimeException("Not A Valid Order. Please try again.")
			}
		}

		return orders
	}
}