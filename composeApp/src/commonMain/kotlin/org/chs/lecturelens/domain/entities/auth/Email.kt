package org.chs.lecturelens.domain.entities.auth

data class Email(
    val value: String,
) {
    init {
        require(value.contains("@")) { "이메일 형식이 올바르지 않습니다." }
        require(value.isNotEmpty()) { "이메일은 1자 이상"}
    }
}

