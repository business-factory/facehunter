package com.roihunter.facehunter.dto.slack.block

data class SectionBlockDto(
        val type: String = "section",
        val text: TextBlockDto
) : StructuredBlock {
    constructor(content: String) : this(text = TextBlockDto(type = "mrkdwn", text = content))
}