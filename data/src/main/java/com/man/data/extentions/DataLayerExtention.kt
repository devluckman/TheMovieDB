package com.man.data.extentions

fun String?.replaceIfNull(replacementValue: String = ""): String {
    if (this == null)
        return replacementValue
    return this
}

fun Int?.replaceIfNull(replacementValue: Int = 0): Int {
    if (this == null)
        return replacementValue
    return this
}

fun Boolean?.replaceIfNull(replacementValue: Boolean = false): Boolean {
    if (this == null) return replacementValue
    return this
}

fun Float?.replaceIfNull(replacementValue: Float = 0F): Float {
    if (this == null) return replacementValue
    return this
}