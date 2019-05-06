package com.roihunter.facehunter.flow

import khttp.post
import khttp.structures.authorization.BasicAuthorization
import org.springframework.stereotype.Service

/**
 * https://api.slack.com/docs/oauth
 */
@Service
class ExchangeVerificationCodeFlow {

    private val clientId = System.getenv("SLACK_CLIENT_ID")
    private val clientSecret = System.getenv("SLACK_CLIENT_SECRET")

    fun handleVerification(code: String) {
        val oauthAccessResponse = post(
                url = "https://slack.com/api/oauth.access",
                headers = mapOf("content-type" to "application/x-www-form-urlencoded"),
                params = mapOf("code" to code, "redirect_uri" to "https://face.roihunter.com/oauth/redirect"),
                auth = BasicAuthorization(clientId, clientSecret)
        )
        println(oauthAccessResponse.text)
    }
}