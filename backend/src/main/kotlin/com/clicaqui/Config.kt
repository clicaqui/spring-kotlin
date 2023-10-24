package com.clicaqui

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

@SpringBootApplication
class Config {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val application = SpringApplication(Config::class.java)
            application.addInitializers(ApplicationContextInitializer<GenericApplicationContext> {  ctx ->
                beans {
                    bean {
                        ViewRoutes(ref())
                    }
                }.initialize(ctx)
            })
            application.run(*args)
        }
    }
}