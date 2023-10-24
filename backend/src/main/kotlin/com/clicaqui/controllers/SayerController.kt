package com.clicaqui.controllers

import com.clicaqui.IHelloSayer
import kotlinx.html.*
import kotlinx.html.stream.createHTML
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SayerController(private val helloWorld: IHelloSayer) {

    @GetMapping(produces = arrayOf("text/html"))
    fun sayHello(@RequestParam(value = "name", required = false) name: String?) : String =
        createHTML(true).html {
            head {
                title = "Sabedoria é tudo"
            }
            body {
                h4 {
                     +helloWorld.sayHello(name ?: "User")
                }
                p {
                    + "Welcome dummies"
                }
            }
    }
}

