package edu.bo.confesionario.publications
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import edu.bo.domain.Publication
import edu.bo.usecases.GetPublications
import kotlinx.coroutines.launch
import org.json.JSONArray

class MainViewModel(val publications: GetPublications) :ScopedViewModel()  {
    init {
        initScope()
    }
    private lateinit var listPublication : List<Publication>
    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() = _model

    sealed class UiModel() {
        class Content(val publicationsList: List<Publication>) : UiModel()
    }
    fun loadPublications() {
        launch {
            listPublication = publications.invoke()
            _model.value = UiModel.Content(listPublication)
        }
    }
    fun getPublications() : List<Publication>
    {
        return listPublication
    }
}