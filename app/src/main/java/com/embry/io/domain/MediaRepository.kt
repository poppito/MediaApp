package com.embry.io.domain

/**
 * Represents the repos of
 * media sources
 * @author harshoverseer
 */
interface MediaRepository {
    fun getSourceByName(name: String)
    fun getAllRepos()
    fun addASource()
}