package org.chs.lecturelens.domain.entities.auth

data class UserId(
    val value: String,
) {
    init {
        require(value.matches(Regex("^[a-zA-Z0-9_]+$"))) { "영어 소문자 대문자 숫자만 입력 가능합니다." }
        require(value.length in 4..20) { "userId must be less than 20 characters" }
    }
}
