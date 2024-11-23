package id.project.nbcmobile.view.components.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.project.nbcmobile.authentication.AuthenticationViewModel
import id.project.nbcmobile.data.repository.Repository
import id.project.nbcmobile.di.Injection
import id.project.nbcmobile.view.components.splash.SplashViewModel
import id.project.nbcmobile.view.customer.history.HistoryViewModel
import id.project.nbcmobile.view.customer.home.HomeViewModel
import id.project.nbcmobile.view.customer.main.MainViewModel
import id.project.nbcmobile.view.customer.profile.CustomerProfileViewModel
import id.project.nbcmobile.view.pegawai.main.PegawaiViewModel
import id.project.nbcmobile.view.pegawai.profile.PegawaiProfileViewModel
import id.project.nbcmobile.view.pegawai.room_management.RoomManagementViewModel

class ViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
                SplashViewModel(repository) as T
            }

            modelClass.isAssignableFrom(AuthenticationViewModel::class.java) -> {
                AuthenticationViewModel(repository) as T
            }

            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }

            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }

            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> {
                HistoryViewModel(repository) as T
            }

            modelClass.isAssignableFrom(CustomerProfileViewModel::class.java) -> {
                CustomerProfileViewModel(repository) as T
            }

            modelClass.isAssignableFrom(PegawaiViewModel::class.java) -> {
                PegawaiViewModel(repository) as T
            }

            modelClass.isAssignableFrom(RoomManagementViewModel::class.java) -> {
                RoomManagementViewModel(repository) as T
            }

            modelClass.isAssignableFrom(PegawaiProfileViewModel::class.java) -> {
                PegawaiProfileViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}