package edu.bo.confesionario

import android.content.Context
import android.graphics.Typeface
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomExpandableListAdapter internal constructor(
    private val context: Context,
    private val titleList: List<String>,
    private val dataList: HashMap<String, List<String>>
) : BaseExpandableListAdapter() {

    override fun getChild(listPosition: Int, expandedListPosition: Int): Any {
        return this.dataList[this.titleList[listPosition]]!![expandedListPosition]
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun getChildView(
        listPosition: Int,
        expandedListPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var convertView = convertView
        val expandedListText = getChild(listPosition, expandedListPosition) as String
        if (convertView == null) {
            val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            Log.d("Position", listPosition.toString())
            convertView = layoutInflater.inflate(R.layout.ayuda_expand, null)
        }

        val expandedListTextView = convertView!!.findViewById<TextView>(R.id.descriptionAyudaView)
        expandedListTextView.text = expandedListText
        return convertView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return this.dataList[this.titleList[listPosition]]!!.size
    }

    override fun getGroup(listPosition: Int): Any {
        return this.titleList[listPosition]
    }

    override fun getGroupCount(): Int {
        return this.titleList.size
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(
        listPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var convertView = convertView
        val listTitle = getGroup(listPosition) as String
        if (convertView == null) {
            val layoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.ayuda_collapse, null)
        }

        val listTitleTextView = convertView!!.findViewById<TextView>(R.id.ayudaTitleView)
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.text = listTitle
        if(isExpanded){
            val changeViewG = (convertView as ViewGroup)
            val changeChildUno = (changeViewG.getChildAt(0) as ViewGroup)
            val changeChildDos = changeChildUno.getChildAt(0)
            changeChildDos.setBackgroundColor(context.getResources().getColor((R.color.primary_blue)))
            ((changeChildDos as ViewGroup).getChildAt(0) as TextView).setTextColor(context.getResources().getColor((R.color.white)))
            ((changeChildDos as ViewGroup).getChildAt(1) as ImageView).setImageResource(R.drawable.arrow_up_expandable_list)
        }

        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }
}