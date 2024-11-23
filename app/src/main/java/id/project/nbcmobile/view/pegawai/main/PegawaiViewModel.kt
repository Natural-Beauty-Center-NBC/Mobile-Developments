package id.project.nbcmobile.view.pegawai.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.project.nbcmobile.data.model.UserModel
import id.project.nbcmobile.data.repository.Repository

class PegawaiViewModel(private val repository: Repository) : ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
}