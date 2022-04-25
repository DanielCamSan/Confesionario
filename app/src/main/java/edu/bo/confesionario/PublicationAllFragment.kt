package edu.bo.confesionario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class PublicationAllFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_publication_all, container, false)
        listPublications = ListPublications.getList()
        listPublications = listPublications.filter { publication -> publication.category == "todos" }
        initRecyclerView(view)
        return view
    }
    private fun initRecyclerView(view: View){

        recyclerView = view.findViewById<RecyclerView>(R.id.recicler_all)
        recyclerView.adapter = PublicationsListAdapter(listPublications  as ArrayList<Publication>)
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager

    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PublicationAllFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}