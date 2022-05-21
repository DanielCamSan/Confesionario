package edu.bo.confesionario.publications

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import edu.bo.confesionario.*
import edu.bo.confesionario.R
import edu.bo.data.PublicationsRepository
import edu.bo.domain.Publication
import edu.bo.framework.DatabaseRef
import edu.bo.framework.PublicationDataSource
import edu.bo.framework.RetrofitBuilder
import edu.bo.usecases.GetPublications
import kotlinx.android.synthetic.main.activity_publications.*


class Publications : AppCompatActivity() {
    private val pager: ViewPager2
        get() = findViewById(R.id.viewPager)
    private val tabs: TabLayout
        get() = findViewById(R.id.tabs)
    private lateinit var mainViewModel: MainViewModel
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
    //private lateinit var binding : ActivityReadDataBinding

    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publications)
        mainViewModel = MainViewModel(GetPublications(PublicationsRepository(DatabaseRef(), "patata")))
        //mainViewModel.model.observe(this, Observer(::updateUi))
        mainViewModel.loadPublications()
        /*
        database = FirebaseDatabase.getInstance().getReference().child("publications")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (snap in dataSnapshot.children) {
                    //val info = snap.getValue<Publication>()
                    Log.d("s1",snap.child("id").value.toString())
                    Log.d("s1",snap.child("title").value.toString())
                    Log.d("s1",snap.child("description").value.toString())
                    Log.d("s1",snap.child("date").value.toString())
                    Log.d("s1",snap.child("idUser").value.toString())
                    Log.d("meess","aaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("fail", "Failed to read value.", error.toException())
            }
        })*/
        setUpTabBar()

        tabs.getTabAt(3)?.view?.visibility = View.GONE
        tabs.getTabAt(4)?.view?.visibility = View.GONE
        leftButton.visibility = View.INVISIBLE
        leftButton.setOnClickListener{
            if (menuView > 0){
                menuView -= 1
                if (menuView == 0) {
                    tabs.getTabAt(3)?.view?.visibility = View.GONE
                    tabs.getTabAt(0)?.view?.visibility = View.VISIBLE
                    leftButton.visibility = View.INVISIBLE
                    rightButton.visibility = View.VISIBLE
                }
                if (menuView == 1) {
                    tabs.getTabAt(4)?.view?.visibility = View.GONE
                    tabs.getTabAt(1)?.view?.visibility = View.VISIBLE
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
                    leftButton.visibility = View.VISIBLE
                }
                if (menuView == 2) {
                    tabs.getTabAt(1)?.view?.visibility = View.GONE
                    tabs.getTabAt(4)?.view?.visibility = View.VISIBLE
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
    }
    private fun setUpTabBar()
    {
        val fragments: List<Fragment> = listOf<Fragment>(
            PublicationsAllFragment(),
            PublicationsBooksFragment(),
            PublicationsPartiesFragment(),
            PublicationsClassesFragment(),
            PublicationsConfesionsFragment()
        )
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
    /*
    private fun updateUi(model: MainViewModel.UiModel?){

    }
    */
}