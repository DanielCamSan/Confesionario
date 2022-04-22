package edu.bo.confesionario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class Publications : AppCompatActivity() {
    lateinit var toolbar: ActionBar
    lateinit var recyclerView : RecyclerView
    lateinit var listPublicationsString : String
    private lateinit var adapter : PublicationsListAdapter
    private val navigation_view: BottomNavigationView
        get() = findViewById(R.id.principal_bottom_navigation_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publications)
        val rar = findViewById<BottomNavigationView>(R.id.principal_bottom_navigation_view)


        navigation_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_navigation_all -> {
                    var fragmentAll = PublicationAllFragment()
                    //fragmentAll.arguments = bundle
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.principal_frame_layout,fragmentAll)
                        .commit()
                    true
                }
                R.id.bottom_navigation_books -> {
                    var fragmentBooks = PublicationsBooksFragment()
                    // fragmentBooks.arguments = bundle
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.principal_frame_layout, fragmentBooks)
                        .commit()
                    true
                }
                R.id.bottom_navigation_parties -> {
                    var fragmentParties = PublicationsPartiesFragment()
                    //fragmentParties.arguments = bundle
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.principal_frame_layout, fragmentParties)
                        .commit()
                    true
                }
                R.id.bottom_navigation_classes -> {
                    var fragmentPublications = PublicationsClassesFragment()
                    //fragmentPublications.arguments = bundle
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.principal_frame_layout, fragmentPublications)
                        .commit()
                    true
                }
                R.id.bottom_navigation_confesions -> {
                    var fragmentConfesions = PublicationsConfesionsFragment()
                    //fragmentConfesions .arguments = bundle
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.principal_frame_layout, fragmentConfesions )
                        .commit()
                    true
                }
                else -> true
            }
        }


    }
}