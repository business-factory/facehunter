package com.roihunter.facehunter.dto.slack.block

data class ButtonBlockDto(
        val type: String = "button",
        val text: TextBlockDto,
        val value: String
) : StructuredBlock {
    constructor(text: String, value: String) : this(value = value, text = TextBlockDto(text = text))
}