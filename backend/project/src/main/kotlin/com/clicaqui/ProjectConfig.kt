package com.clicaqui

import com.clicaqui.repository.ProjectRepository
import com.clicaqui.service.ProjectService
import com.clicaqui.service.ProjectServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.support.GenericApplicationContext

@Configuration
class ProjectConfig(private val applicationContext: GenericApplicationContext) {

    @Primary
    @Bean
    fun helloSayer(): ProjectService =
        ProjectServiceImpl(applicationContext.getBean(ProjectRepository::class.java))
}