package org.chs.lecturelens.data.remote.model.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(val userId: String, val password: String)
