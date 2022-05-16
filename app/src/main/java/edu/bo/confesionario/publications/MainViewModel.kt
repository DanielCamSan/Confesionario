package edu.bo.confesionario.publications
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import edu.bo.domain.Publication
import edu.bo.usecases.GetPublications
import kotlinx.coroutines.launch

class MainViewModel(private val publications: GetPublications) :ScopedViewModel()  {
    init {
        initScope()
    }
    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() = _model

    sealed class UiModel() {
        class Content(val publicationsList: List<Publication>) : UiModel()
    }
    fun loadPublications() {
        launch {
            _model.value = UiModel.Content(publications.invoke())
        }
    }
}