package edu.bo.confesionario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.google.android.material.bottomnavigation.BottomNavigationView

class Publications : AppCompatActivity() {
    lateinit var toolbar: ActionBar

    private val navigation_view: BottomNavigationView
        get() = findViewById(R.id.principal_bottom_navigation_view)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publications)
        //toolbar = supportActionBar!!
        //val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)
        navigation_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_navigation_all -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.principal_frame_layout, PublicationAllFragment())
                        .commit()
                    true
                }
                R.id.bottom_navigation_books -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.principal_frame_layout, PublicationsBooksFragment())
                        .commit()
                    true
                }
                R.id.bottom_navigation_parties -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.principal_frame_layout, PublicationsPartiesFragment())
                        .commit()
                    true
                }
                R.id.bottom_navigation_classes -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.principal_frame_layout, PublicationsClassesFragment())
                        .commit()
                    true
                }
                R.id.bottom_navigation_confesions -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.principal_frame_layout, PublicationsConfesionsFragment())
                        .commit()
                    true
                }
                else -> true
            }
        }

    }
}