package com.clicaqui

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Service

@Service
open class HelloWorld {
    fun sayHello() {
        println("Hello world")
    }
}