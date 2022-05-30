package edu.bo.confesionario

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.*
import android.widget.ExpandableListView.OnGroupClickListener
import android.widget.ExpandableListView.OnGroupExpandListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth


class Help : AppCompatActivity() {
    private var expandableListView: ExpandableListView? = null
    private var expandableListAdapter: ExpandableListAdapter? = null
    private var titleList: List<String>? = null
    private val btnBack: Button
        get() = findViewById(R.id.go_back_btn)

    private val googleBtn: Button
        get() = findViewById(R.id.go_maps)

    private val toolBarLogoutBtn: ImageButton
        get() = findViewById(R.id.toolBarLogoutBtn)

    private val appInfoBtn: ImageButton
        get() = findViewById(R.id.infoBtn)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        setContentView(R.layout.activity_help)
        expandableListView = findViewById(R.id.eTV)
        if (expandableListView != null) {
            val listData = data
            titleList = ArrayList(listData.keys)
            expandableListAdapter =
                CustomExpandableListAdapter(this, titleList as ArrayList<String>, listData)
            expandableListView!!.setAdapter(expandableListAdapter)


            expandableListView!!.setOnGroupExpandListener(object : OnGroupExpandListener {
                var lastExpandedPosition = -1
                override fun onGroupExpand(i: Int) {
                    if (lastExpandedPosition != -1 && i != lastExpandedPosition) {
                        expandableListView!!.collapseGroup(lastExpandedPosition)
                    }
                    lastExpandedPosition = i
                }
            })

            expandableListView!!.setOnGroupCollapseListener { groupPosition ->
                val changeColorCollapse = ((expandableListView!!.get(groupPosition) as ViewGroup).getChildAt(0) as ViewGroup).getChildAt(0)
                changeColorCollapse.setBackgroundColor(getResources().getColor((R.color.light_yellow)))
                ((changeColorCollapse as ViewGroup).getChildAt(0) as TextView).setTextColor(getResources().getColor((R.color.black)))
                ((changeColorCollapse as ViewGroup).getChildAt(1) as ImageView).setImageResource(R.drawable.arrow_down_expandable_list)
            }
        }
        btnBack.findViewById<Button>(R.id.go_back_btn)
        btnBack.setOnClickListener {
            finish()
        }
        googleBtn.findViewById<Button>(R.id.go_back_btn)
        googleBtn.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
            this.overridePendingTransition(0, 0);
        }
        appInfoBtn.setOnClickListener {
            val intent = Intent(this, Help::class.java)
            startActivity(intent)
            this.overridePendingTransition(0, 0);
        }

        toolBarLogoutBtn.setOnClickListener {
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
    }

    val data: HashMap<String, List<String>>
        get() {
            val listData = HashMap<String, List<String>>()

            val serviceDescription = ArrayList<String>()
            serviceDescription.add(
                """
                    Horario del centro de llamadas:
                    Lun-vie 9 am-7pm BOLIVIA
                    Métodos alternativos.
                     
                        Teléfono: 77777777 
                        Pagina de Facebook: Lo que callamos los de la UCB
                        
                    Es recomendable enviar un correo a los mensajes de contactanos.
                    """.trimIndent()
            )

            val policeDescription = ArrayList<String>()
            policeDescription.add(
                """
                    Esta Política de Privacidad se aplica dentro de toda la aplicación.
                    
                    En dispositivos móviles, emuladores de PC y cualquier dispositivo Android. También se aplica a nuestras actividades de marketing y publicidad si es necesario.
                    """.trimIndent()
            )


            val aboutDescription = ArrayList<String>()
            aboutDescription.add(
                """
                    Pulse el botón de Ubicacion situado en la esquína superior derecha para ir al Mapa de nuestra ubicación
                    
                    Bolivia-Cochabamba.
                    """.trimIndent()
            )


            val contactDescription = ArrayList<String>()
            contactDescription.add(
                """
                    Contactenos en:
                    
                    Confesionario@gmail.com
                    
                    Visite nuestra pagina en:
                    
                    
                    facebook.com/Lo-Que-Callamos-Los-De-La-UCB-CBBA-346151905797925
                    """.trimIndent()
            )

            listData["SERVICIO AL CLIENTE"] = serviceDescription
            listData["POLITICA DE PRIVACIDAD"] = policeDescription
            listData["ACERCA DE LA APLICACION"] = aboutDescription
            listData["CONTACTANOS"] = contactDescription

            return listData
        }
}