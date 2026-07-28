package com.example.moodmosaic.db.repository

import com.example.moodmosaic.db.dao.MoodDefinitionDao

class MoodDefinitionRepository(private val definitionDao: MoodDefinitionDao) {

    fun getMoodDefinitions() =
        definitionDao.getAll()
}