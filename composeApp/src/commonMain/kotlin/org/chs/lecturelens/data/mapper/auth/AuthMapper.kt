package org.chs.lecturelens.data.mapper.auth

import org.chs.lecturelens.data.remote.model.EmailRequest
import org.chs.lecturelens.domain.entities.auth.EmailEntity

fun EmailEntity.toDto(): EmailRequest = EmailRequest(email)

