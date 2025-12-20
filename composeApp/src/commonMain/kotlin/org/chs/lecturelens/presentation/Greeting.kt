package org.chs.lecturelens.presentation

import org.chs.lecturelens.getPlatform

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}