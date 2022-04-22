package edu.bo.confesionario

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class PublicationsClassesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var listPublications: List<Publication>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_publications_classes, container, false)
        listPublications = ListPublications.getList()
        listPublications = listPublications.filter { publication -> publication.category == "clases" }
        initRecyclerView(view)
        return view
    }
    private fun initRecyclerView(view: View){

        recyclerView = view.findViewById<RecyclerView>(R.id.recicler_classes)
        recyclerView.adapter = PublicationsListAdapter(listPublications as ArrayList<Publication>)
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager

    }
    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PublicationsClassesFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}