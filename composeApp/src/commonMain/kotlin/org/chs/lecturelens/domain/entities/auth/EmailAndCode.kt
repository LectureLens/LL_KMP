package org.chs.lecturelens.domain.entities.auth

data class EmailAndCode(
    val email:Email,
    val code: Code
)