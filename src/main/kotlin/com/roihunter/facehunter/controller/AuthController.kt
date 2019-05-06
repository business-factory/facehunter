package com.roihunter.facehunter.controller

import com.roihunter.facehunter.flow.ExchangeVerificationCodeFlow
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * https://api.slack.com/docs/oauth
 */
@RestController
class AuthController(
        private val exchangeVerificationCodeFlow: ExchangeVerificationCodeFlow
) {

    @GetMapping(value = ["/oauth/redirect"])
    fun oauthRedirect(@RequestParam code: String) {
        exchangeVerificationCodeFlow.handleVerification(code)
    }
}