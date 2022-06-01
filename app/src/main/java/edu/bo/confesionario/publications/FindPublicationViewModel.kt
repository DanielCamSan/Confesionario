package edu.bo.confesionario.publications
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import edu.bo.usecases.FindPublication
import kotlinx.coroutines.launch
import edu.bo.domain.Publication
import java.util.*

class FindPublicationViewModel(val publicationFind: FindPublication):ScopedViewModel()  {
    init {
        initScope()
    }
    private lateinit var request:Pair<Boolean,Publication>
    private val _model = MutableLiveData<FindPublicationViewModel.UiModel>()
    val model: LiveData<FindPublicationViewModel.UiModel>
        get() = _model
    sealed class UiModel() {
        class Content(val publicationFind:Pair<Boolean,Publication>) : UiModel()
    }
    fun findPublication(query:String) {
        request = publicationFind.invoke(query)
        _model.value = UiModel.Content(request)
    }
}