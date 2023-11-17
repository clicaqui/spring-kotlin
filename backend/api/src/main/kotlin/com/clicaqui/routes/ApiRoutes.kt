package com.clicaqui.routes

import com.clicaqui.handler.ApiHandler
import com.clicaqui.util.WithLogging
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.*

class ApiRoutes(private val apiHandler: ApiHandler): WithLogging() {

    @Bean
    fun apiRouter(): RouterFunction<ServerResponse> =
        router {
            (accept(MediaType.APPLICATION_JSON_UTF8) and "/api").nest {
                //GET("/hello") { req ->
                //    ServerResponse.ok()
                //        .json(Mono.just(
                //            helloIHelloSayer.sayHello(
                //                req.queryParam("name").orElse("Usar")
                //            )
                //        ))
                //}
                "/projects".nest {
                    POST("/", apiHandler::handle)
                    GET("/", apiHandler::getProjects)
                    GET("/owners", apiHandler::getOwners)
                    GET("/byOwner/{name}", apiHandler::getByOwner)
                    GET("/{id}", apiHandler::getProject)
                }
            }
        }.filter { request, next ->
            LOG.debug(request)
            next.handle(request)
        }
}