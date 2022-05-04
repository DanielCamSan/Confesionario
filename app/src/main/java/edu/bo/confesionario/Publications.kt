package edu.bo.confesionario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import edu.bo.confesionario.publications.MainViewModel
import edu.bo.ucb.data.PublicationsRepository
import edu.bo.ucb.framework.PublicationDataSource
import edu.bo.ucb.framework.RetrofitBuilder
import edu.bo.ucb.usecases.GetPublications
import edu.bo.ucb.domain.Publication
import kotlinx.android.synthetic.main.activity_publications.*

class Publications : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var toolbar: ActionBar
    lateinit var recyclerView : RecyclerView
    lateinit var listPublicationsString : String
    private lateinit var pager: ViewPager2
    private lateinit var tabs: TabLayout
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

        mainViewModel = MainViewModel(GetPublications(PublicationsRepository(PublicationDataSource( RetrofitBuilder ), "patata")))
        mainViewModel.model.observe(this, Observer(::updateUi))
        mainViewModel.loadPublications()
        setUpTabBar()
        tabs = findViewById(R.id.tabs)
        pager = findViewById(R.id.viewPager)

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

        /*navigation_view.setOnNavigationItemSelectedListener {
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
        }*/
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
    private fun setUpTabBar()
    {
        val fragments: List<Fragment> = listOf<Fragment>(PublicationAllFragment(),PublicationsBooksFragment(),PublicationsPartiesFragment(),PublicationsClassesFragment(),PublicationsConfesionsFragment())
        val adapter = TabPageAdapter(this, tabs.tabCount, fragments)
        pager.adapter = adapter
        pager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback()
        {
            override fun onPageSelected(position: Int){
                tabs.selectTab(tabs.getTabAt(position))
            }
        })
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener
        {
            override fun onTabSelected(tab: TabLayout.Tab)
            {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab?){}
            override fun onTabReselected(tab: TabLayout.Tab?){}
        })
    }
    private fun updateUi(model: MainViewModel.UiModel?){

    }

}