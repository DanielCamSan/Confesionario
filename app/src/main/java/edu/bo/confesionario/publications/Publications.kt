package edu.bo.confesionario.publications

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import edu.bo.confesionario.Confesion
import edu.bo.confesionario.Help
import edu.bo.confesionario.IndividualConfession
import edu.bo.confesionario.R
import edu.bo.confesionario.adapter.RedirectToPublicationAdapter
import edu.bo.confesionario.login.Login
import edu.bo.data.PublicationsRepository
import edu.bo.domain.Publication
import edu.bo.framework.DatabaseRef
import edu.bo.usecases.FindPublication
import edu.bo.usecases.GetPublications
import kotlinx.android.synthetic.main.activity_publications.*
import java.util.*
import kotlin.collections.ArrayList

class Publications : AppCompatActivity() {
    private lateinit var searcher : SearchView
    private val pager: ViewPager2
        get() = findViewById(R.id.viewPager)
    private val tabs: TabLayout
        get() = findViewById(R.id.tabs)
    private lateinit var mainViewModel: MainViewModel
    private lateinit var findViewModel: FindPublicationViewModel
    private lateinit var redirectorToPublication : RedirectToPublicationAdapter
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
    private lateinit var adapter : TabPageAdapter
    private fun tabsDisplacement()
    {
        tabs.getTabAt(3)?.view?.visibility = View.GONE
        tabs.getTabAt(4)?.view?.visibility = View.GONE
        leftButton.visibility = View.INVISIBLE
        leftButton.setOnClickListener{
            var position = viewPager.currentItem
            rightButton.visibility = if (position == 4) View.GONE else View.VISIBLE
            if (position > 0)
            {
                position -= 1
                tabs.selectTab(tabs.getTabAt(position))
                viewPager.currentItem = position
            }
        }
        rightButton.setOnClickListener{
            var position = viewPager.currentItem
            leftButton.visibility = if (position == 0) View.GONE else View.VISIBLE
            if (position < 4)
            {
                position += 1
                tabs.selectTab(tabs.getTabAt(position))
                viewPager.currentItem = position
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publications)
        val repository = PublicationsRepository(DatabaseRef())
        mainViewModel = MainViewModel(GetPublications(repository))
        mainViewModel.loadPublications()
        findViewModel = FindPublicationViewModel(FindPublication(repository))
        pager.setPageTransformer(ZoomOutPageTransformer())
        redirectorToPublication = RedirectToPublicationAdapter(this)
        tabsDisplacement()
        publicateBtn.setOnClickListener{
            val intent = Intent(this, Confesion::class.java)
            startActivity(intent)
            this.overridePendingTransition(0, 0)
        }
        toolBarLogoutBtn.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            GoogleSignIn.getClient(
                this,
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
            ).signOut()
            Toast.makeText(this, R.string.logout_msg, Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
            this.overridePendingTransition(0, 0);

        }
        appInfoBtn.setOnClickListener{
            val intent = Intent(this, Help::class.java)
            startActivity(intent)
            this.overridePendingTransition(0, 0);

        }
        searcher = findViewById(R.id.searcher)
        searcher.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    findAndRedirect(query)
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })

        setUpTabBar()
    }
    private fun findAndRedirect(query: String)
    {
        findViewModel.findPublication(query)
        var model = findViewModel.model.value
        when ( model) {
            is FindPublicationViewModel.UiModel.Content ->
            {
                val request = model.publicationFind
                if (request.first)
                {
                    redirectorToPublication.redirectToPublication(request.second)
                }else{
                    Toast.makeText(this@Publications,"No se hallo la publicacion",Toast.LENGTH_SHORT)
                }

            }
        }
    }
    private fun redirectToPublication(publication: Publication)
    {
        //ContextCompat.startActivity(this@Publications, intent, bundle)
    }

    private fun onSlidePage(numberPage: Int)
    {
        if (numberPage == 0) {
            tabs.getTabAt(3)?.view?.visibility = View.GONE
            tabs.getTabAt(0)?.view?.visibility = View.VISIBLE
            rightButton.visibility = View.VISIBLE
            leftButton.visibility = View.INVISIBLE
        }
        if (numberPage == 1) {
            tabs.getTabAt(4)?.view?.visibility = View.GONE
            tabs.getTabAt(1)?.view?.visibility = View.VISIBLE
            rightButton.visibility = View.VISIBLE
            leftButton.visibility = View.VISIBLE
        }
        if (numberPage == 3) {
            tabs.getTabAt(0)?.view?.visibility = View.GONE
            tabs.getTabAt(3)?.view?.visibility = View.VISIBLE
            rightButton.visibility = View.VISIBLE
            leftButton.visibility = View.VISIBLE
        }
        if (numberPage == 4) {
            tabs.getTabAt(1)?.view?.visibility = View.GONE
            tabs.getTabAt(4)?.view?.visibility = View.VISIBLE
            rightButton.visibility = View.INVISIBLE
            leftButton.visibility = View.VISIBLE
        }
    }
    private fun setUpTabBar()
    {
        val fragments: List<Fragment> = listOf(
            PublicationsAllFragment(mainViewModel),
            PublicationsBooksFragment(mainViewModel),
            PublicationsPartiesFragment(mainViewModel),
            PublicationsClassesFragment(mainViewModel),
            PublicationsConfesionsFragment(mainViewModel)
        )
        adapter = TabPageAdapter(this, tabs.tabCount, fragments)
        pager.adapter = adapter
        pager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback()
        {
            override fun onPageSelected(position: Int){
                if (position in 0..4) {
                    onSlidePage(position)
                    tabs.selectTab(tabs.getTabAt(position))
                }
            }
        })
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener
        {
            override fun onTabSelected(tab: TabLayout.Tab)
            {
                leftButton.visibility = if (tab.position == 0) View.GONE else View.VISIBLE
                rightButton.visibility = if (tab.position == 4) View.GONE else View.VISIBLE
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab?){}
            override fun onTabReselected(tab: TabLayout.Tab?){}
        })
    }

    ///visual
    ///animacion del visual pager que realiza un zoom out cuando cambiamos de tab
    class ZoomOutPageTransformer : ViewPager2.PageTransformer {
        private val MIN_SCALE = 0.85f
        private val MIN_ALPHA = 0.5f

        override fun transformPage(view: View, position: Float) {
            view.apply {
                val pageWidth = width
                val pageHeight = height
                when {
                    position < -1 -> { // [-Infinity,-1)
                        // This page is way off-screen to the left.
                        alpha = 0f
                    }
                    position <= 1 -> { // [-1,1]
                        // Modify the default slide transition to shrink the page as well
                        val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                        val vertMargin = pageHeight * (1 - scaleFactor) / 2
                        val horzMargin = pageWidth * (1 - scaleFactor) / 2
                        translationX = if (position < 0) {
                            horzMargin - vertMargin / 2
                        } else {
                            horzMargin + vertMargin / 2
                        }

                        // Scale the page down (between MIN_SCALE and 1)
                        scaleX = scaleFactor
                        scaleY = scaleFactor

                        // Fade the page relative to its size.
                        alpha = (MIN_ALPHA +
                                (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                    }
                    else -> { // (1,+Infinity]
                        // This page is way off-screen to the right.
                        alpha = 0f
                    }
                }
            }
        }
    }
}