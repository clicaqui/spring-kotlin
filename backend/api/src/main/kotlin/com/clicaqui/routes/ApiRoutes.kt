package com.clicaqui.routes

import com.clicaqui.IHelloSayer
import com.clicaqui.util.json
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono

class ApiRoutes(private val helloIHelloSayer: IHelloSayer) {

    @Bean
    fun apiRouter(): RouterFunction<ServerResponse> =
        router {
            (accept(MediaType.APPLICATION_JSON_UTF8) and "/api").nest {
                GET("/hello") { req ->
                    ServerResponse.ok()
                        .json(Mono.just(
                            helloIHelloSayer.sayHello(
                                req.queryParam("name").orElse("Usar")
                            )
                        ))
                }
            }
        }
}