package org.chs.lecturelens.data.remote.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(val userId: String, val email: String, val name: String, val password: String, val phoneNumber: String)

