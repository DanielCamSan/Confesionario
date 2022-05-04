package edu.bo.confesionario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.bo.confesionario.publications.MainViewModel
import edu.bo.ucb.data.PublicationsRepository
import java.util.*
import androidx.lifecycle.Observer
import edu.bo.ucb.domain.Publication
import edu.bo.ucb.framework.PublicationDataSource
import edu.bo.ucb.framework.RetrofitBuilder
import edu.bo.ucb.usecases.GetPublications

class PublicationsAllFragment : Fragment() {
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
        viewPublications = inflater.inflate(R.layout.fragment_publication_all, container, false)
        recyclerView = viewPublications.findViewById<RecyclerView>(R.id.recicler_all)
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        return viewPublications
    }
    private fun initRecyclerView(publications: List<Publication>){
        recyclerView.adapter = PublicationsListAdapter(publications  as ArrayList<Publication>)
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PublicationsAllFragment().apply {
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