package id.project.nbcmobile.view.customer.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.project.nbcmobile.data.repository.Repository

class HistoryViewModel(private val repository: Repository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is history Fragment"
    }
    val text: LiveData<String> = _text
}