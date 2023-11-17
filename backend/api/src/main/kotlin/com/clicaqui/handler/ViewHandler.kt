package com.clicaqui.handler

import com.clicaqui.service.ProjectService
import com.clicaqui.util.htmlView
import com.clicaqui.views.index
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono


class ViewHandler(private val projectService: ProjectService) {

    fun handle(req: ServerRequest) =
        ServerResponse.ok()
            .htmlView(Mono.just(
                index("Hello ${req.queryParam("name").orElse("PacktUser")}",
                    projectService.fetchProjectsForView())
            ))

}