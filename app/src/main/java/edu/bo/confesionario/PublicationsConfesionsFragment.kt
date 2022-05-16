package edu.bo.confesionario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.bo.confesionario.publications.MainViewModel
import edu.bo.data.PublicationsRepository
import edu.bo.domain.Publication
import edu.bo.framework.PublicationDataSource
import edu.bo.framework.RetrofitBuilder
import edu.bo.usecases.GetPublications
import java.util.*
import androidx.lifecycle.Observer

class PublicationsConfesionsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    lateinit var mainViewModel: MainViewModel
    private lateinit var viewPublications: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        mainViewModel = MainViewModel(GetPublications(PublicationsRepository(PublicationDataSource( RetrofitBuilder ), "patata")))
        mainViewModel.model.observe(this, Observer(::updateUi))
        mainViewModel.loadPublications()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewPublications = inflater.inflate(R.layout.fragment_publications_confesions, container, false)
        recyclerView = viewPublications.findViewById<RecyclerView>(R.id.recicler_confesions)
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        return viewPublications
    }
    private fun initRecyclerView(publications: List<Publication>){
        var listPublications = publications.filter { publication -> publication.category == "confesiones" }
        recyclerView.adapter = PublicationsListAdapter(listPublications as ArrayList<Publication>)
    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PublicationsConfesionsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
    private fun updateUi(model: MainViewModel.UiModel?){
        when ( model) {
            is MainViewModel.UiModel.Content -> initRecyclerView(model.publicationsList)
        }

    }
}