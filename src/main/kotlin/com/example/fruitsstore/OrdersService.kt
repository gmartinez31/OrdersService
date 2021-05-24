package com.example.fruitsstore

import org.springframework.stereotype.Service
import java.lang.RuntimeException
//import org.springframework.kafka.core.KafkaTemplate

@Service
class OrdersService {

//	private lateinit var kafkaTemplate: KafkaTemplate<String, String>
//
//	init {
//	    // need to figure out how to autowire in constructor or this init block
//		 kafkaTemplate = KafkaTemplate<String, String>()
//	}

	private val SUCCESS: String = "order-success-topic"
	private val FAILED: String = "order-failed-topic"

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


		// Send off notification to customer regarding its status and estimated delivery time.
		// Randomizing which message gets sent for simplicity's sake
		val random = (1..10).shuffled().first()
		if (random % 2 == 0) {
			sendMessage(SUCCESS,"Order is complete. It should be delivered here shortly.")
		} else {
			sendMessage(FAILED, "Order failed. One or more items are out of stock.")
			return 0.0
		}

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

	private fun sendMessage(topic: String, message: String) {
//		kafkaTemplate.send(topic, message)
		println("message")
	}
}