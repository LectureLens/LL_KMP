package org.chs.lecturelens

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform