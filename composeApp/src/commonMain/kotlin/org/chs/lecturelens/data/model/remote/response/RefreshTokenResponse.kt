package org.chs.lecturelens.data.remote.model.response

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponse(val accessToken: String, val refreshToken: String)