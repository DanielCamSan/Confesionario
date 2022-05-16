package edu.bo.confesionario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import edu.bo.confesionario.login.Login

class Publications : AppCompatActivity() {

    lateinit var toolbar: ActionBar
    lateinit var recyclerView : RecyclerView
    lateinit var listPublicationsString : String
    var menuView : Int = 0
    private lateinit var adapter : PublicationsListAdapter
    private val navigation_view: BottomNavigationView
        get() = findViewById(R.id.principal_bottom_navigation_view)

    private val leftButton : ImageView
        get() = findViewById(R.id.buttonLeft)


    private  val publicateBtn: Button
        get() = findViewById(R.id.publicate_btn)

    private val rightButton : ImageView
        get() = findViewById(R.id.buttonRight)

    private val goBackBtn : Button
        get() =  findViewById(R.id.go_back_btn)


    private val toolBarLogoutBtn : ImageButton
        get() =  findViewById(R.id.toolBarLogoutBtn)

    private val appInfoBtn : ImageButton
        get() =  findViewById(R.id.infoBtn)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publications)
        var fragmentAll = PublicationAllFragment()
        val menu = navigation_view.menu
        val itemAll = menu.findItem(R.id.bottom_navigation_all)
        val itemBooks = menu.findItem(R.id.bottom_navigation_books)
        val itemParties = menu.findItem(R.id.bottom_navigation_parties)
        val itemClasses = menu.findItem(R.id.bottom_navigation_classes)
        val itemConfessions = menu.findItem(R.id.bottom_navigation_confesions)

        itemClasses.isVisible = false
        itemConfessions.isVisible = false

        supportFragmentManager.beginTransaction()
            .replace(R.id.principal_frame_layout,fragmentAll)
            .commit()
        val rar = findViewById<BottomNavigationView>(R.id.principal_bottom_navigation_view)
        leftButton.visibility = View.INVISIBLE
        leftButton.setOnClickListener{
            if (menuView > 0){
                menuView -= 1
                if (menuView == 0) {
                    itemClasses.isVisible = false
                    itemAll.isVisible = true
                    leftButton.visibility = View.INVISIBLE
                    rightButton.visibility = View.VISIBLE
                }
                if (menuView == 1) {
                    itemConfessions.isVisible = false
                    itemParties.isVisible = true
                    rightButton.visibility = View.VISIBLE
                }
            }
        }
        rightButton.setOnClickListener{
            if (menuView < 2) {
                menuView += 1
                if (menuView == 1) {
                    itemAll.isVisible = false
                    itemClasses.isVisible = true
                    leftButton.visibility = View.VISIBLE
                }
                if (menuView == 2) {
                    itemParties.isVisible = false
                    itemConfessions.isVisible = true
                    rightButton.visibility = View.INVISIBLE
                    leftButton.visibility = View.VISIBLE
                }
            }
        }

        navigation_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_navigation_all -> {

                    var fragmentAll = PublicationAllFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.principal_frame_layout,fragmentAll)
                        .commit()
                    true
                }
                R.id.bottom_navigation_books -> {
                    var fragmentBooks = PublicationsBooksFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.principal_frame_layout, fragmentBooks)
                        .commit()
                    true
                }
                R.id.bottom_navigation_parties -> {
                    var fragmentParties = PublicationsPartiesFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.principal_frame_layout, fragmentParties)
                        .commit()
                    true
                }
                R.id.bottom_navigation_classes -> {
                    var fragmentPublications = PublicationsClassesFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.principal_frame_layout, fragmentPublications)
                        .commit()
                    true
                }
                R.id.bottom_navigation_confesions -> {
                    var fragmentConfesions = PublicationsConfesionsFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.principal_frame_layout, fragmentConfesions )
                        .commit()
                    true
                }
                else -> true
            }
        }
        publicateBtn.setOnClickListener{
            val intent = Intent(this, Confesion::class.java)
            startActivity(intent)
            this.overridePendingTransition(0, 0);

        }
        toolBarLogoutBtn.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            this.overridePendingTransition(0, 0);

        }
        appInfoBtn.setOnClickListener{
            val intent = Intent(this, Help::class.java)
            startActivity(intent)
            this.overridePendingTransition(0, 0);

        }

    }

}