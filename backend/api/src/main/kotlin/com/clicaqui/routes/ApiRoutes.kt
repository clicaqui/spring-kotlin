package com.clicaqui.routes

import com.clicaqui.FieldErrorDTO
import com.clicaqui.IHelloSayer
import com.clicaqui.ProjectDTO
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono
import javax.validation.Validator

class ApiRoutes(private val helloIHelloSayer: IHelloSayer, private val validator: Validator) {

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
                    POST("/") {req ->
                        req.bodyToMono<ProjectDTO>()
                            .map { project ->
                                val violations = validator.validate(project)
                                if (violations.isNotEmpty()) {
                                    project.fieldErrors = violations.map {
                                        FieldErrorDTO(it.propertyPath.toString(), it.message)
                                    }
                                }
                                project
                            }.flatMap {
                                when(it.fieldErrors) {
                                    null -> ServerResponse.ok().body(Mono.just(it))
                                    else -> ServerResponse.unprocessableEntity().body(Mono.just(it))
                                }
                            }
                    }
                }
            }
        }
}