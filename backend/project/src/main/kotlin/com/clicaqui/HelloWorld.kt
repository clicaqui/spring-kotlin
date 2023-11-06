package com.clicaqui

import org.springframework.stereotype.Service

interface IHelloSayer {
    fun sayHello(s: String): String
}

@Service
internal class HelloWorld : IHelloSayer {
    override fun sayHello(s: String): String {
        return "Hello $s"
    }
}