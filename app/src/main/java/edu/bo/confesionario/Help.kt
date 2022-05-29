package edu.bo.confesionario

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.*
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
                        val changeColorCollapse = ((expandableListView!!.get(lastExpandedPosition) as ViewGroup).getChildAt(0) as ViewGroup).getChildAt(0)
                        changeColorCollapse.setBackgroundColor(getResources().getColor((R.color.light_yellow)))
                        ((changeColorCollapse as ViewGroup).getChildAt(0) as TextView).setTextColor(getResources().getColor((R.color.black)))
                        expandableListView!!.collapseGroup(lastExpandedPosition)
                    }
                    Log.d("Position", i.toString())
                    val changeViewG=(expandableListView!!.get(i) as ViewGroup)
                    val changeChildUno = (changeViewG.getChildAt(0) as ViewGroup)
                    val changeChildDos = changeChildUno.getChildAt(0)
                    changeChildDos.setBackgroundColor(getResources().getColor((R.color.primary_blue)))
                    ((changeChildDos as ViewGroup).getChildAt(0) as TextView).setTextColor(getResources().getColor((R.color.white)))
                    lastExpandedPosition = i
                }
            })

            expandableListView!!.setOnGroupCollapseListener { groupPosition ->
                val changeColorCollapse = ((expandableListView!!.get(groupPosition) as ViewGroup).getChildAt(0) as ViewGroup).getChildAt(0)
                changeColorCollapse.setBackgroundColor(getResources().getColor((R.color.light_yellow)))
                ((changeColorCollapse as ViewGroup).getChildAt(0) as TextView).setTextColor(getResources().getColor((R.color.black)))
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
                    Lun-vie 9 am-7pm EST
                    El mejor momento para marcar:
                    10:45 am
                    Navega por el laberinto del teléfono a un humano:
                    Presione 2 y luego 1
                    Espera promedio
                    Espera actual
                    7 7
                    Rango (entre números de teléfono):
                    1
                    Rango (general):
                    1
                    Métodos alternativos:
                    teléfono, web
                    Es recomendable enviar un correo a los mensajes de contactanos.
                    """.trimIndent()
            )

            val policeDescription = ArrayList<String>()
            policeDescription.add(
                """
                    Esta Política de Privacidad se aplica a todos nuestros juegos, ya se jueguen en nuestro sitio web www.king.com.
                    
                    En dispositivos móviles, PC o en otras plataformas como Facebook. También se aplica a nuestras actividades de marketing y publicidad.
                    
                    En todas las plataformas y otros servicios que podemos proporcionarle de vez en cuando. En esta Política de Privacidad, nos referimos a nuestros
                    """.trimIndent()
            )


            val aboutDescription = ArrayList<String>()
            aboutDescription.add(
                """
                    Pulse el botón del Mapa del Reino situado en la esquina inferior izquierda para ir al Mapa del Reino, y luego pulse el botón del globo terráqueo para ir al Mapa del Mundo.
                    Pulsa sobre un reino para ver su mapa. Toca una casilla vacía y selecciona Reubicar o Migrar para transferir tu territorio. Los jugadores sólo pueden trasladarse a un reino en el que la migración esté restringida si es más antiguo que su reino de origen.
                    
                    """.trimIndent()
            )


            val contactDescription = ArrayList<String>()
            contactDescription.add(
                """
                    Contactenos en:
                    
                    abc2234@gmail.com
                    androidlover@gmail.com
                    
                    Visite nuestra pagina en:
                    
                    
                    https://www.facebook.com/1182906556/videos/10220903002183147/
                    
                    Y nuestro canal de youtube:  https://www.youtube.com/watch?v=riT4nl0T8_M
                    """.trimIndent()
            )

            listData["SERVICIO AL CLIENTE"] = serviceDescription
            listData["POLITICA DE PRIVACIDAD"] = policeDescription
            listData["ACERCA DE LA APLICACION"] = aboutDescription
            listData["CONTACTANOS"] = contactDescription

            return listData
        }
}