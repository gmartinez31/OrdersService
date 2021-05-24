package com.example.fruitsstore

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class MailService {

    @KafkaListener(topics = ["order-success-topic"], groupId = "order-success")
    fun consumeMessage(message: String) {
        println("Message received: $message")
    }
}