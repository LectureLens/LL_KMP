package org.chs.lecturelens.data.remote.model.response

import kotlinx.serialization.Serializable
import org.chs.lecturelens.domain.entities.auth.UserInfoEntity

@Serializable
data class LoginResponse(val accessToken: String, val refreshToken: String, val user: UserResponse)