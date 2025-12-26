package org.chs.lecturelens.data.remote.model.request

import kotlinx.serialization.Serializable

@Serializable
data class EmailRequest(val email: String)