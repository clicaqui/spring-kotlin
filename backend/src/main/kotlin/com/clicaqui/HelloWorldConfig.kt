package com.clicaqui

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HelloWorldConfig {
    @Bean
    fun helloSayer(): IHelloSayer = HelloWorld()
}