package edu.bo.confesionario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PublicationAllFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PublicationAllFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*arguments?.let {

        }*/

        // Inflate the layout for this fragment

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_publication_all, container, false)

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

        recyclerView = view.findViewById<RecyclerView>(R.id.recicler_all)
        recyclerView.adapter = PublicationsListAdapter(listPublications)
        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PublicationAllFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PublicationAllFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}