package com.clicaqui

import kotlinx.html.*
import kotlinx.html.stream.createHTML
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

class ViewRoutes(private val helloWorld: IHelloSayer) {
    private val links = mapOf(
        "Kotlin" to "http://github.com/JetBrains/kotlin",
        "Kotlin1" to "http://github.com/JetBrains/kotlin",
        "Kotlin2" to "http://github.com/JetBrains/kotlin",
        "Kotlin3" to "http://github.com/JetBrains/kotlin",
    )
    @Bean
    fun viewRouter() : RouterFunction<ServerResponse> =
        router {
            accept(MediaType.TEXT_HTML).nest {
                GET("/hello") {req ->
                    val name = req.queryParam("name").orElse("User")
                    ServerResponse.ok()
                        .contentType(MediaType.TEXT_HTML)
                        .body(Mono.just(
                            createHTML(true).html {
                                head {
                                    title = "Sabedoria é tudo"
                                }
                                body {
                                    h4 {
                                        +helloWorld.sayHello(name)
                                    }
                                    p {
                                        +"Welcome dummies"
                                    }
                                    p {
                                        ul {
                                            links.map { (name, url) ->
                                                li {
                                                    a(url) {
                                                        target = ATarget.blank
                                                        +name
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        ))
                }
            }
            resources( "/**", ClassPathResource("/static") )
        }
}