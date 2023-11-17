package com.clicaqui.handler

import com.clicaqui.FieldErrorDTO
import com.clicaqui.ProjectDTO
import com.clicaqui.service.ProjectService
import com.clicaqui.toDto
import com.clicaqui.toProject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono
import javax.validation.Validator


class ApiHandler(private val validator: Validator,
                 private val projectService: ProjectService
) {

    fun handle(req: ServerRequest) =
        req.bodyToMono<ProjectDTO>()
            .map { project ->
                val violations = validator.validate(project)
                if (violations.isNotEmpty()) {
                    project.fieldErrors = violations.map {
                        FieldErrorDTO(it.propertyPath.toString(), it.message)
                    }
                }
                project
            }
            .flatMap {
                when (it.fieldErrors) {
                    null -> ServerResponse.ok().body(Mono.just(
                        projectService.saveProject(it.toProject()).toDto()
                    ))
                    else -> ServerResponse.unprocessableEntity().body(Mono.just(it))
                }
            }

    fun getProjects(req: ServerRequest) =
        ServerResponse.ok().body(
            Mono.just(projectService.fetchProjects().map { it.toDto() })
        )

    fun getProject(req: ServerRequest): Mono<ServerResponse> {
        val id = req.pathVariable("id").toLong()
        val projectDTO: ProjectDTO? = projectService.fetchProject(id)?.toDto()
        return if (projectDTO != null) {
            ServerResponse.ok().body(Mono.just(projectDTO))
        } else {
            ServerResponse.notFound().build()
        }
    }

    fun getOwners(req: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok().body(Mono.just(projectService.fetchAllOwners()))

    fun getByOwner(req: ServerRequest): Mono<ServerResponse> {
        val name = req.pathVariable("name")
        return ServerResponse.ok().body(Mono.just(projectService.findByOwner(name).map { it.toDto() }))
    }
}