package org.chs.lecturelens.domain.entities.auth

data class EmailAndCodeEntity(
    val email:Email,
    val code: Code
)