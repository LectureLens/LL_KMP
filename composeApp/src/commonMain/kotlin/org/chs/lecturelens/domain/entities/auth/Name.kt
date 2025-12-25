package org.chs.lecturelens.domain.entities.auth

data class Name(
    val value: String,
) {
    init {
        require(value.length in 2..20) { "이름 길이는 2자 이상 20자 이하"}
    }
}