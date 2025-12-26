package org.chs.lecturelens.data.remote.model.response

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val userId: String,
    val email: String,
    val plan: String,
    val usageCount: Int,
    val usageLimit: Int,
    val lectureList: List<LectureResponse>,
)
