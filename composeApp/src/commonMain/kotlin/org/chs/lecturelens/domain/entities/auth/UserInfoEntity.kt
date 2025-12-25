package org.chs.lecturelens.domain.entities.auth

data class UserInfoEntity (
    val userId: String,
    val email: String,
    val plan: String,
    val usageCount: Int,
    val usageLimit: Int,
    val lectureList: List<LectureEntity>
)


