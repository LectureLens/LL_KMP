package org.chs.lecturelens.data.remote.model.response

import kotlinx.serialization.Serializable

@Serializable
data class LectureResponse(
    val id: Int,
    val title: String,
    val status: String,
)
