package org.chs.lecturelens.data.mapper.auth

import org.chs.lecturelens.data.model.local.RefreshToken
import org.chs.lecturelens.data.remote.model.request.EmailRequest
import org.chs.lecturelens.data.remote.model.request.EmailVerifyRequest
import org.chs.lecturelens.data.remote.model.request.LoginRequest
import org.chs.lecturelens.data.remote.model.request.RefreshTokenRequest
import org.chs.lecturelens.data.remote.model.request.SignUpRequest
import org.chs.lecturelens.data.remote.model.response.LectureResponse
import org.chs.lecturelens.data.remote.model.response.LoginResponse
import org.chs.lecturelens.data.remote.model.response.UserResponse
import org.chs.lecturelens.domain.entities.auth.Email
import org.chs.lecturelens.domain.entities.auth.EmailAndCodeEntity
import org.chs.lecturelens.domain.entities.auth.LectureEntity
import org.chs.lecturelens.domain.entities.auth.LoginRequestEntity
import org.chs.lecturelens.domain.entities.auth.LoginResponseEntity
import org.chs.lecturelens.domain.entities.auth.SignUpEntity
import org.chs.lecturelens.domain.entities.auth.UserInfoEntity

fun Email.toDto(): EmailRequest = EmailRequest(value)

fun LoginRequestEntity.toDto(): LoginRequest = LoginRequest(userId.value, password.value)

fun LoginResponse.toEntity() = LoginResponseEntity(accessToken, refreshToken, user.toEntity())

fun EmailAndCodeEntity.toDto(): EmailVerifyRequest = EmailVerifyRequest(email.value, code.value)

fun SignUpEntity.toDto(): SignUpRequest = SignUpRequest(userId.value, email.value, name.value, password.value, phoneNumber.value)

fun RefreshToken.toDto(): RefreshTokenRequest = RefreshTokenRequest(value)

fun UserResponse.toEntity(): UserInfoEntity =
    UserInfoEntity(
        userId = userId,
        email = email,
        plan = plan,
        usageCount = usageCount,
        usageLimit = usageLimit,
        lectureList = lectureList.map { it.toEntity() },
    )

fun LectureResponse.toEntity(): LectureEntity = LectureEntity(id, title, status)
