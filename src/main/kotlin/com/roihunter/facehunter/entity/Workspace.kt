package com.roihunter.facehunter.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "workspace", schema = "public")
class Workspace(
        @Column(name = "access_token")
        val accessToken: String = "",
        @Column(name = "team_name")
        val teamName: String = "",
        @Column(name = "team_id")
        val teamId: String = "",
        @Column(name = "bot_id")
        val botId: String = "",
        @Column(name = "bot_token")
        val botToken: String = ""
) {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private var id: Long? = null
}