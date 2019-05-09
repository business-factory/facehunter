package com.roihunter.facehunter.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.roihunter.facehunter.dto.slack.event.ChallengeResponseDto
import com.roihunter.facehunter.dto.slack.event.EventDto
import com.roihunter.facehunter.manager.SlackManager
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class EventsController(
        private val slackManager: SlackManager,
        private val mapper: ObjectMapper
) {

    @PostMapping(value = ["/event"])
    @ResponseStatus(HttpStatus.OK)
    fun slackInteractiveResponse(
            @RequestBody body: String,
            @RequestHeader("X-Slack-Request-Timestamp") timestamp: String,
            @RequestHeader("X-Slack-Signature") signature: String
    ): ChallengeResponseDto? {
        slackManager.verifyRequest(body, timestamp, signature)
        val event: EventDto = mapper.readValue(body)
        if (event.type == "url_verification") {
            return ChallengeResponseDto(event.challenge ?: throw IllegalStateException("Challenge request without challenge value. $event"))
        } else {
            return null
        }
    }
}