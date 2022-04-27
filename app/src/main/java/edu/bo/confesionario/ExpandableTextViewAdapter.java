package edu.bo.confesionario;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;

import org.w3c.dom.Text;

public class ExpandableTextViewAdapter extends BaseExpandableListAdapter {
    Context context;
    String[]sections={
            "SERVICIO AL CLIENTE",
            "POLITICA DE PRIVACIDAD",
            "ACERCA DE LA APLICACION",
            "CONTACTANOS"
    };
    String[][]description={
            {
                "Horario del centro de llamadas:\n" +
                        "Lun-vie 9 am-7pm EST\n" +
                        "El mejor momento para marcar:\n" +
                        "10:45 am\n" +
                        "Navega por el laberinto del teléfono a un humano:\n" +
                        "Presione 2 y luego 1\n" +
                        "Espera promedio\n" +
                        "Espera actual\n" +
                        "7 7\n" +
                        "Rango (entre números de teléfono):\n" +
                        "1\n" +
                        "Rango (general):\n" +
                        "1\n" +
                        "Métodos alternativos:\n" +
                        "teléfono, web\n" +
                        "Es recomendable enviar un correo a los mensajes de contactanos."
            },
            {
                "Esta Política de Privacidad se aplica a todos nuestros juegos, ya se jueguen en nuestro sitio web www.king.com.\n" +
                        "\n" +
                        "En dispositivos móviles, PC o en otras plataformas como Facebook. También se aplica a nuestras actividades de marketing y publicidad.\n" +
                        "\n" +
                        "En todas las plataformas y otros servicios que podemos proporcionarle de vez en cuando. En esta Política de Privacidad, nos referimos a nuestros"
            },
            {
                "Pulse el botón del Mapa del Reino situado en la esquina inferior izquierda para ir al Mapa del Reino, y luego pulse el botón del globo terráqueo para ir al Mapa del Mundo.\n" + "Pulsa sobre un reino para ver su mapa. Toca una casilla vacía y selecciona Reubicar o Migrar para transferir tu territorio. Los jugadores sólo pueden trasladarse a un reino en el que la migración esté restringida si es más antiguo que su reino de origen.\n"
            },
            {
                "Contactenos en:\n" +
                        "\n" +
                        "abc2234@gmail.com\n" +
                        "androidlover@gmail.com\n" +
                        "\n" +
                        "Visite nuestra pagina en:\n" +
                        "\n" +
                        "\n" +
                        "https://www.facebook.com/1182906556/videos/10220903002183147/\n" +
                        "\n" +
                        "Y nuestro canal de youtube:\u2028\u2028https://www.youtube.com/watch?v=riT4nl0T8_M"
            }
    };

    public ExpandableTextViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return sections.length;
    }

    @Override
    public int getChildrenCount(int i) {
        return description[i].length;
    }

    @Override
    public Object getGroup(int i) {
        return sections[i];
    }

    @Override
    public Object getChild(int i, int i1) {
        return description[i][i1];
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        String sectionFaq = (String)getGroup(i);
        if (view==null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.ayuda_collapse,null);
        }
        TextView sectionFaq2=view.findViewById(R.id.ayudaTitleView);
        sectionFaq2.setTypeface(null, Typeface.BOLD);
        sectionFaq2.setText(sectionFaq);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final String descriptionFaq = (String)getChild(i,i1);
        if (view==null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.ayuda_expand, null);
        }
        TextView descriptionFaq2=view.findViewById(R.id.descriptionAyudaView);
        descriptionFaq2.setText(descriptionFaq);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
