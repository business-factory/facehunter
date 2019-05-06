package com.roihunter.facehunter.repository

import com.roihunter.facehunter.entity.Workspace
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface WorkspaceRepository : JpaRepository<Workspace, Long> {

    /**
     * Retrieves given workspace, if existing.
     */
    fun getByTeamId(teamId: String): Optional<Workspace>
}