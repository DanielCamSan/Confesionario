package edu.bo.confesionario

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class PublicationsPartiesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_publications_parties, container, false)

        initRecyclerView(view)
        return view
    }
    private fun initRecyclerView(view: View){
        val listPublications = arrayListOf<Publication>()
        listPublications.add(Publication(
            "Maestros",
            "1",
            "Wea1",
            "Esta descripcion habla mucho sobre como crear una app madre mia cuantas clases",
            Date(2,2,2),
            "Tom"))
        listPublications.add(Publication(
            "Maestros",
            "2",
            "Wea1",
            "Esta descripcion habla mucho sobre como crear una app madre mia cuantas clases",
            Date(2,2,2),
            "Tom"))
        listPublications.add(Publication(
            "Maestros",
            "3",
            "Wea1",
            "Esta descripcion habla mucho sobre como crear una app madre mia cuantas clases",
            Date(2,2,2),
            "Tom"))
        listPublications.add(Publication(
            "Maestros",
            "4",
            "Wea1",
            "Esta descripcion habla mucho sobre como crear una app madre mia cuantas clases",
            Date(2,2,2),
            "Tom"))

        recyclerView = view.findViewById<RecyclerView>(R.id.recicler_parties)
        recyclerView.adapter = PublicationsListAdapter(listPublications)
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