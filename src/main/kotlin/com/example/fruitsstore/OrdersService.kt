package com.example.fruitsstore

import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class OrdersService {

	fun calculateTotal(order: String): Double {
		val fruits = validateOrder(order)
		var total = 0.00
		for (fruit in fruits) {
			if (fruit.toLowerCase() == "apple") total+=.60
			if (fruit.toLowerCase() == "orange") total+=.25
		}
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