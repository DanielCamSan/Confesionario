package edu.bo.confesionario.publish
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import edu.bo.confesionario.publications.ScopedViewModel
import edu.bo.domain.Publication
import edu.bo.usecases.PostPublication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val publication: PostPublication) : ScopedViewModel()  {
    init {
        initScope()
    }
    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() = _model
    //Publication tiene un id user ligarlo con el que inicia sesion
    sealed class UiModel() {
        class Content(val publicationSingle: Publication) : UiModel()
    }
    fun postPublication(publicationObject: Publication) {
        launch(Dispatchers.IO) {
            publication.invoke(publicationObject)
        }
    }
}