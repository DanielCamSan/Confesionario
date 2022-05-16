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
import java.util.*
import androidx.lifecycle.Observer
import edu.bo.domain.Publication
import edu.bo.framework.PublicationDataSource
import edu.bo.framework.RetrofitBuilder
import edu.bo.usecases.GetPublications

class PublicationsPartiesFragment : Fragment() {

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
        viewPublications = inflater.inflate(R.layout.fragment_publications_parties, container, false)
        recyclerView = viewPublications.findViewById<RecyclerView>(R.id.recicler_parties)
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        return viewPublications
    }
    private fun initRecyclerView(publications: List<Publication>){
        var listPublications = publications.filter { publication -> publication.category == "fiestas" }
        recyclerView.adapter = PublicationsListAdapter(listPublications as ArrayList<Publication>)
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PublicationsPartiesFragment().apply {
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