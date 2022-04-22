package edu.bo.confesionario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.Serializable

class Publications : AppCompatActivity() {
    lateinit var toolbar: ActionBar
    lateinit var recyclerView : RecyclerView
    lateinit var listPublicationsString : String
    var menuView : Int = 0
    private lateinit var adapter : PublicationsListAdapter
    private val navigation_view: BottomNavigationView
        get() = findViewById(R.id.principal_bottom_navigation_view)

    private val leftButton : ImageButton
        get() = findViewById(R.id.buttonLeft)

    private val rightButton : ImageButton
        get() = findViewById(R.id.buttonRight)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publications)
        val rar = findViewById<BottomNavigationView>(R.id.principal_bottom_navigation_view)

        leftButton.visibility = View.INVISIBLE
        leftButton.setOnClickListener{
            if (menuView > 0){
                menuView -= 1
                if (menuView == 0) {
                    navigation_view.getMenu().clear();
                    navigation_view.inflateMenu(R.menu.publication_menu0);
                    leftButton.visibility = View.INVISIBLE
                    rightButton.visibility = View.VISIBLE
                }
                if (menuView == 1) {
                    navigation_view.getMenu().clear();
                    navigation_view.inflateMenu(R.menu.publication_menu1);
                    rightButton.visibility = View.VISIBLE
                }
                if (menuView == 2) {
                    navigation_view.getMenu().clear();
                    navigation_view.inflateMenu(R.menu.publication_menu2);
                    rightButton.visibility = View.VISIBLE
                }
            }
        }
        rightButton.setOnClickListener{
            if (menuView < 2) {
                menuView += 1
                if (menuView == 1) {
                    navigation_view.getMenu().clear();
                    navigation_view.inflateMenu(R.menu.publication_menu1);
                    leftButton.visibility = View.VISIBLE
                }
                if (menuView == 2) {
                    navigation_view.getMenu().clear();
                    navigation_view.inflateMenu(R.menu.publication_menu2);
                    rightButton.visibility = View.INVISIBLE
                    leftButton.visibility = View.VISIBLE
                }
            }
        }
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