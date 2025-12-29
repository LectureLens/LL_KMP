package org.chs.lecturelens.domain.entities.auth

data class Code(
    val value: String,
) {
    init {
        require(value.length == 6) { "코드는 6자여야 합니다." }
    }
}
