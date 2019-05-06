package com.roihunter.facehunter.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.roihunter.facehunter.dto.slack.interactive.PayloadDto
import com.roihunter.facehunter.flow.EvaluateGuessFlow
import com.roihunter.facehunter.flow.NewGuessFlow
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.lang.IllegalStateException

@RestController
class InteractiveController(
        private val mapper: ObjectMapper,
        private val evaluateGuessFlow: EvaluateGuessFlow,
        private val newGuessFlow: NewGuessFlow
) {

    @PostMapping(
            value = ["/interactive"],
            consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE],
            produces = [MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.OK)
    fun slackInteractiveResponse(
            @RequestParam payload: PayloadDto
    ) {
        val pickedAction = payload.actions.first()
        if (pickedAction.value != "next") { // Means we're evaluating a guess.
            val splitParts = pickedAction.value?.split("..") ?: throw IllegalStateException("Missing clicked button value!")
            val correct = splitParts[0]
            val correctName = splitParts[1]
            val position = splitParts[2]
            evaluateGuessFlow.evaluateGuess(correct == "correct", correctName, position, payload.user.id, payload.team.id)
        } else {
            newGuessFlow.newGuess(payload.user.id, payload.team.id)
        }
    }
}