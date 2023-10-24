package com.clicaqui

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

@Configuration
class HelloWorldConfig {
    @Bean
    fun helloSayer(): IHelloSayer = HelloWorld()
}