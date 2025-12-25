package org.chs.lecturelens.domain.entities.auth

data class PhoneNumber(val value: String) {
    // ≥ 1 characters    matches ^01[016789]-?\d{3,4}-?\d{4}$
    init {
        require(value.matches(Regex("^01[016789]-?\\d{3,4}-?\\d{4}$"))) {"한국 휴대폰 번호 형식이 아닙니다"}
        require(value.isNotEmpty()) {"1자 이상"}
    }
}