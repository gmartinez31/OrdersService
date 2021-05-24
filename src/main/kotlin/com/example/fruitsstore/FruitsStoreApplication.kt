package com.example.fruitsstore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class FruitsStoreApplication

fun main(args: Array<String>) {

	val ordersService = OrdersService()
	val scanner = Scanner(System.`in`)
	println("Welcome, customer. What fruits would you like to order? Currently, we only accept orders for Apples and Oranges.")

	val order = scanner.nextLine()
	val total = ordersService.calculateTotal(order)

	println(total)

	runApplication<FruitsStoreApplication>(*args)
}