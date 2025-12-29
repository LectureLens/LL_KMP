package org.chs.lecturelens.domain.entities.auth

data class LoginResponseEntity(
    val accessToken: String,
    val refreshToken: String,
    val user: UserInfoEntity,
)
