package id.project.nbcmobile.di

import android.content.Context
import id.project.nbcmobile.data.preference.UserPreference
import id.project.nbcmobile.data.preference.dataStore
import id.project.nbcmobile.data.repository.Repository
import id.project.nbcmobile.data.source.remote.ApiConfig

object Injection {
    fun provideRepository(context: Context): Repository {
        val preference = UserPreference.getInstance(context.dataStore)
//        val tokenSession = runBlocking {
//            preference.getSession().first()
//        }
        val apiService = ApiConfig.getApiService()

        return Repository(preference, apiService)
    }
}