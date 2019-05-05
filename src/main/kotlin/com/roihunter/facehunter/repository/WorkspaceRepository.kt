package com.roihunter.facehunter.repository

import com.roihunter.facehunter.entity.Workspace
import org.springframework.data.jpa.repository.JpaRepository

interface WorkspaceRepository : JpaRepository<Workspace, Long> {
}