package id.project.nbcmobile.room_management

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RoomManagementViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Room Management"
    }
    val text: LiveData<String> = _text
}