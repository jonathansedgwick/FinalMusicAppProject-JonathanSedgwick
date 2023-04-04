package com.jonathansedgwick.finalproject_musicapp.DataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class DataStoreManager(val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "serverData")
    companion object {

        val USER_DATA = stringPreferencesKey("USERDATA")

    }

    suspend fun savetoDataStore(string: String) {
        context.dataStore.edit {

            it[USER_DATA] = string

        }
    }

    suspend fun getFromDataStore() = context.dataStore.data.map {
        it[USER_DATA]
    }



}