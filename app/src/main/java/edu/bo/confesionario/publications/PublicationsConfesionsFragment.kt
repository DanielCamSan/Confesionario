package edu.bo.confesionario.publications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.bo.domain.Publication
import java.util.*
import androidx.lifecycle.Observer
import edu.bo.confesionario.R


class PublicationsConfesionsFragment(private var mainViewModel : MainViewModel) : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewPublications: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.model.observe(this, Observer(::updateUi))
        mainViewModel.loadPublications()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewPublications = inflater.inflate(R.layout.fragment_publications_confesions, container, false)
        recyclerView = viewPublications.findViewById(R.id.recicler_confesions)
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        return viewPublications
    }
    private fun initRecyclerView(publications: List<Publication>){
        var listPublications = publications.filter { publication -> publication.category == "Confesiones" }
        recyclerView.adapter = PublicationsListAdapter(listPublications as ArrayList<Publication>)
    }
    private fun updateUi(model: MainViewModel.UiModel?){
        when ( model) {
            is MainViewModel.UiModel.Content -> initRecyclerView(model.publicationsList)
        }

    }
}