package org.chs.lecturelens.domain.entities.auth

data class Password(
    val value: String,
) {
    // [8, 20] characters   matches ^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&]).+$
    init {
        require(value.length in 8..20) { "비밀번호는 8자 이상"}
        require(value.matches(Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&]).+$"))) {
            "영어, 숫자, @\$!%#?&중 특수문자 포함"
        }
    }
}