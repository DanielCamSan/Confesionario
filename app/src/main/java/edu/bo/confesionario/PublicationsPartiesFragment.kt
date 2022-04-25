package edu.bo.confesionario

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class PublicationsPartiesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var listPublications: List<Publication>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_publications_parties, container, false)
        listPublications = ListPublications.getList()
        listPublications = listPublications.filter { publication -> publication.category == "fiestas" }
        initRecyclerView(view)
        return view
    }
    private fun initRecyclerView(view: View){
        recyclerView = view.findViewById<RecyclerView>(R.id.recicler_parties)
        recyclerView.adapter = PublicationsListAdapter(listPublications as ArrayList<Publication>)
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager

    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PublicationsPartiesFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}