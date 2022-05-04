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

        //mainViewModel = MainViewModel(GetPublications(PublicationsRepository(PublicationDataSource( RetrofitBuilder ), "patata")))
        //mainViewModel.model.observe(this, Observer(::updateUi))
        //mainViewModel.loadPublications()
        tabs = findViewById(R.id.tabs)
        pager = findViewById(R.id.viewPager)
        setUpTabBar()

        tabs.getTabAt(3)?.view?.visibility = View.GONE
        tabs.getTabAt(4)?.view?.visibility = View.GONE
        leftButton.visibility = View.INVISIBLE
        leftButton.setOnClickListener{
            if (menuView > 0){
                menuView -= 1
                if (menuView == 0) {
                    //itemClasses.isVisible = false
                    //itemAll.isVisible = true
                    tabs.getTabAt(3)?.view?.visibility = View.GONE
                    tabs.getTabAt(0)?.view?.visibility = View.VISIBLE
                    leftButton.visibility = View.INVISIBLE
                    rightButton.visibility = View.VISIBLE
                }
                if (menuView == 1) {
                    tabs.getTabAt(4)?.view?.visibility = View.GONE
                    tabs.getTabAt(1)?.view?.visibility = View.VISIBLE
                    //itemConfessions.isVisible = false
                    //itemParties.isVisible = true
                    rightButton.visibility = View.VISIBLE
                }
            }
        }
        rightButton.setOnClickListener{
            if (menuView < 2) {
                menuView += 1
                if (menuView == 1) {
                    tabs.getTabAt(0)?.view?.visibility = View.GONE
                    tabs.getTabAt(3)?.view?.visibility = View.VISIBLE
                    //itemAll.isVisible = false
                    //itemClasses.isVisible = true
                    leftButton.visibility = View.VISIBLE
                }
                if (menuView == 2) {
                    tabs.getTabAt(1)?.view?.visibility = View.GONE
                    tabs.getTabAt(4)?.view?.visibility = View.VISIBLE
                    //itemParties.isVisible = false
                    //itemConfessions.isVisible = true
                    rightButton.visibility = View.INVISIBLE
                    leftButton.visibility = View.VISIBLE
                }
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