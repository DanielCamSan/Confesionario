package edu.bo.confesionario.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import edu.bo.confesionario.publications.ScopedViewModel
import edu.bo.domain.Comment
import edu.bo.usecases.GetComments
import edu.bo.usecases.PostComment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val comments: GetComments, private val createComment: PostComment): ScopedViewModel() {
    init{
        initScope()
    }
    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() = _model

    sealed class UiModel() {
        class Content(val commentsList: List<Comment?>) : UiModel()
    }
    fun loadComments() {
        launch(Dispatchers.IO) {
            _model.postValue(UiModel.Content(comments.invoke()))
        }
    }
    fun createComment(newComment: Comment){
        launch(Dispatchers.IO) {
            createComment.invoke(newComment)
        }
    }
}