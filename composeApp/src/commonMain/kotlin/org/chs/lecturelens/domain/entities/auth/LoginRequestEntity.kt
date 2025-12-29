package org.chs.lecturelens.domain.entities.auth

data class LoginRequestEntity(
    val userId: UserId,
    val password: Password,
)
