package com.clicaqui

interface IHelloSayer {
    fun sayHello(s: String): String
}

//@Service
internal class HelloWorld : IHelloSayer {
    override fun sayHello(s: String): String {
        return "Hello $s"
    }
}