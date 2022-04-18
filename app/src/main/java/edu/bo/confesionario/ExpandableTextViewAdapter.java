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
                "Esto es servicio al cliente"
            },
            {
                "Esto es Politica de privacidad"
            },
            {
                "Esto es acerca de la aplicacion"
            },
            {
                "Esto es contactanos"
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
            view = inflater.inflate(R.layout.activity_ayuda,null);
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
