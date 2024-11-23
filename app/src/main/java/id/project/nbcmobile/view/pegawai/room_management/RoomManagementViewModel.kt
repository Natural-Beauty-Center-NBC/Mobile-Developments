package id.project.nbcmobile.view.pegawai.room_management

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.project.nbcmobile.data.repository.Repository

class RoomManagementViewModel(private val repository: Repository) : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Room Management"
    }
    val text: LiveData<String> = _text
}