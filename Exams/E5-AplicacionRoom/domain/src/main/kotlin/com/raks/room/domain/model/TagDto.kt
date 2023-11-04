package com.raks.room.domain.model

data class TagDto(
    val tid: Int,
    val tag: String,
) {

    companion object {
        fun empty() = TagDto(0, "")
    }

}