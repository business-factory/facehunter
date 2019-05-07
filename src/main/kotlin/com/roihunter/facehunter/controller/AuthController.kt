package com.roihunter.facehunter.controller

import com.roihunter.facehunter.flow.ExchangeVerificationCodeFlow
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.view.RedirectView

/**
 * https://api.slack.com/docs/oauth
 */
@RestController
class AuthController(
        private val exchangeVerificationCodeFlow: ExchangeVerificationCodeFlow
) {

    private val clientId = System.getenv("SLACK_CLIENT_ID")

    @GetMapping(value = ["/oauth/redirect"])
    fun oauthRedirect(@RequestParam code: String): String {
        return exchangeVerificationCodeFlow.handleVerification(code)
    }

    /**
     * https://api.slack.com/slack-apps#direct_install
     */
    @GetMapping(value = ["/oauth/install"])
    @ResponseStatus(code = HttpStatus.FOUND)
    fun installRedirect(): RedirectView {
        return RedirectView("https://slack.com/oauth/authorize?client_id=$clientId&scope=bot%20commands&redirect_uri=https://face.roihunter.com/oauth/redirect")
    }
}