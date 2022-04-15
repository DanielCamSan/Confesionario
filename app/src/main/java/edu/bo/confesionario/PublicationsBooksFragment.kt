package edu.bo.confesionario

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PublicationsBooksFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PublicationsBooksFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_publications_books, container, false)

        initRecyclerView(view)
        return view
        // Inflate the layout for this fragment
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

        recyclerView = view.findViewById<RecyclerView>(R.id.recicler_books)
        recyclerView.adapter = PublicationsListAdapter(listPublications)
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager

    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PublicationsBooksFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}