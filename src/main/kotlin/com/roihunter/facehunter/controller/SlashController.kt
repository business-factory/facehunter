package com.roihunter.facehunter.controller

import com.roihunter.facehunter.flow.NewGuessFlow
import com.roihunter.facehunter.manager.SlackManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class SlashController(
        private val newGuessFlow: NewGuessFlow,
        private val slackManager: SlackManager
) {

    @PostMapping(
            value = ["slash"],
            consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE],
            produces = [MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun handleSlashCommand(
            @RequestParam user_id: String,
            @RequestParam command: String,
            @RequestParam channel_id: String,
            @RequestParam team_id: String,
            @RequestBody body: String,
            @RequestHeader("X-Slack-Request-Timestamp") timestamp: String,
            @RequestHeader("X-Slack-Signature") signature: String
    ) {
        slackManager.verifyRequest(body, timestamp, signature)
        if (command.contains("facehunt", ignoreCase = true)) {
            GlobalScope.launch {
                newGuessFlow.newGuess(user_id, team_id)
            }
        }
    }
}