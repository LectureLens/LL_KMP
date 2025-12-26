package org.chs.lecturelens.data.remote.model.request

import kotlinx.serialization.Serializable

@Serializable
data class EmailVerifyRequest(
    val email: String,
    val code: String,
)
