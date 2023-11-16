package com.clicaqui

import com.fasterxml.jackson.annotation.JsonInclude
import org.hibernate.validator.constraints.URL
import javax.validation.constraints.Size

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ProjectDTO(
    @get:Size(min = 2)
    val name: String,

    @get:URL
    val url: String,

    @get:Size(min = 2)
    val owner: String,

    val language: Language,
    val id: Long? = null
) : Validatable()

fun ProjectDTO.toProject() = Project(name, url, owner, language, id)
fun Project.toDto() = ProjectDTO(name, url, owner, language, id)

@JsonInclude(JsonInclude.Include.NON_NULL)
open class Validatable(
    var fieldErrors: List<FieldErrorDTO>? = null,
    var genericError: String? = null
)

data class FieldErrorDTO(val field: String, val message: String)