package org.chs.lecturelens.domain.entities.auth

data class SignUpEntity(
    val userId: UserId,
    val email: Email,
    val name: Name,
    val password: Password,
    val phoneNumber: PhoneNumber,
)
