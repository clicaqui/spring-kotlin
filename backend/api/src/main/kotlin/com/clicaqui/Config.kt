package com.clicaqui

import com.clicaqui.handler.ApiHandler
import com.clicaqui.handler.ExceptionHandler
import com.clicaqui.handler.ViewHandler
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
                    bean { ViewHandler(ref()) }
                    bean { ViewRoutes(ref()) }
                    bean { ApiHandler(ref(), ref()) }
                    bean { ApiRoutes(ref()) }
                    bean<ExceptionHandler>()
                }.initialize(ctx)
            })
            application.run(*args)
        }
    }
}