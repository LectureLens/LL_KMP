package org.chs.lecturelens.data.remote.model.response

import kotlinx.serialization.Serializable

@Serializable
data class EmailVerifyResponse(
    val verified: Boolean,
)
