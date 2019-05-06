package com.roihunter.facehunter.dto.slack.block

data class TextBlockDto(
        val type: String = "plain_text",
        val text: String
) : StructuredBlock