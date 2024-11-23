package id.project.nbcmobile.view.components.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.project.nbcmobile.data.repository.Repository
import id.project.nbcmobile.data.model.UserModel

class SplashViewModel(private val repository: Repository) : ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
}