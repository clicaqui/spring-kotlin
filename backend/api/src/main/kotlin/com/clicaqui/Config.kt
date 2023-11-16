package com.clicaqui

import com.clicaqui.routes.ApiRoutes
import com.clicaqui.routes.ViewRoutes
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
                    bean {
                        ApiRoutes(ref(), ref())
                    }
                }.initialize(ctx)
            })
            application.run(*args)
        }
    }
}