package com.clicaqui.util

import org.reactivestreams.Publisher
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

inline fun <reified T : Any> ServerResponse.BodyBuilder.json(content: Publisher<T>): Mono<ServerResponse> =
    contentType(MediaType.APPLICATION_JSON_UTF8)
        .body(content)

inline fun <reified T : Any> ServerResponse.BodyBuilder.htmlView(content: Publisher<T>): Mono<ServerResponse> =
    contentType(MediaType.TEXT_HTML)
        .body(content)