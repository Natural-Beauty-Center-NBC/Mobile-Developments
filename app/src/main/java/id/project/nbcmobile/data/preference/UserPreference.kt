package id.project.nbcmobile.data.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import id.project.nbcmobile.data.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {
    suspend fun saveSession(user: UserModel) {
        dataStore.edit { preference ->
            preference[ID_KEY] = user.id
            preference[NAME_KEY] = user.name
            preference[TYPE_KEY] = user.type
            preference[EMAIL_KEY] = user.email
            preference[TOKEN_KEY] = user.token
            preference[IS_LOGIN_KEY] = true
        }
    }

    fun getSession(): Flow<UserModel> {
        return dataStore.data.map { preference ->
            UserModel(
                id = preference[ID_KEY] ?: "",
                name = preference[NAME_KEY] ?: "",
                type = preference[TYPE_KEY] ?: "",
                email = preference[EMAIL_KEY] ?: "",
                token = preference[TOKEN_KEY] ?: "",
                isLogin = preference[IS_LOGIN_KEY] ?: false
            )
        }
    }

    suspend fun logout() {
        dataStore.edit { preference ->
            preference.clear()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        private val ID_KEY = stringPreferencesKey("id")
        private val NAME_KEY = stringPreferencesKey("name")
        private val TYPE_KEY = stringPreferencesKey("type")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val IS_LOGIN_KEY = booleanPreferencesKey("isLogin")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore = dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}