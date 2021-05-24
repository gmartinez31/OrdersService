package com.example.fruitsstore

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.Exception

internal class OrdersServiceTest {

    val ordersService = OrdersService()

    @Test
    fun `validateOrder() for empty order should throw an Exception`() {
        val emptyOrder = ""
        val exception = assertThrows(Exception::class.java) {
            ordersService.validateOrder(emptyOrder)
        }
        assertEquals("Order is blank. Please try again.", exception.message)
    }

    @Test
    fun `validateOrder() for invalid order should throw an Exception`() {
        val badOrder = "apple, orange, banana"
        val exception = assertThrows(Exception::class.java) {
            ordersService.validateOrder(badOrder)
        }
        assertEquals("Not A Valid Order. Please try again.", exception.message)
    }

    @Test
    fun `validateOrder() should return a list of valid fruits`() {
        val order = "ApPle, Orange, orange, APPlE"
        val fruits = listOf("ApPle", "Orange", "orange", "APPlE")
        val validatedOrder = ordersService.validateOrder(order)

        assertTrue(validatedOrder.size == 4)
        assertEquals(fruits, validatedOrder)
    }

    @Test
    fun `calculateTotal() for empty order should throw an Exception`() {
        val emptyOrder = ""
        val exception = assertThrows(Exception::class.java) {
            ordersService.calculateTotal(emptyOrder)
        }
        assertEquals("Order is blank. Please try again.", exception.message)
    }

    @Test
    fun `calculateTotal() for invalid order should throw an Exception`() {
        val badOrder = "apple, strawberry, apple"
        val exception = assertThrows(Exception::class.java) {
            ordersService.calculateTotal(badOrder)
        }
        assertEquals("Not A Valid Order. Please try again.", exception.message)
    }

    @Test
    fun `calculateTotal() should return total price for valid order`() {
        val order = "ApPle, Orange, orange, orange, APPlE"
        val total: Double = ordersService.calculateTotal(order)
        val formattedTotal = String.format("%.2f", total)
        assertEquals("1.10", formattedTotal)
    }
}