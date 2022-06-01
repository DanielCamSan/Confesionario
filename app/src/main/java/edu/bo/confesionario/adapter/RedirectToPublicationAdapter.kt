package edu.bo.confesionario.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import edu.bo.confesionario.IndividualConfession
import edu.bo.domain.Publication
import java.util.*

class RedirectToPublicationAdapter(val parentContext: Context) {
    fun redirectToPublication(publication:Publication)
    {
        val bundle = getBundleOfPublication(publication)
        redirectWith(bundle)
    }
    fun getBundleOfPublication(publication: Publication): Bundle
    {
        val bundle = Bundle()
        var date = publication.date.get(Calendar.YEAR).toString()
        date = date + "-" + (if (publication.date.get(Calendar.MONTH)+1<10) "0" else "") + (publication.date.get(
            Calendar.MONTH)+1).toString()
        date = date + "-" + (if (publication.date.get(Calendar.DAY_OF_MONTH)<10) "0" else "") + publication.date.get(
            Calendar.DAY_OF_MONTH).toString()
        bundle.putString("category", publication.category)
        bundle.putString("title", publication.title)
        bundle.putString("description", publication.description)
        bundle.putString("date", date)
        bundle.putString("userName", publication.userName)
        bundle.putString("id", publication.id.toString())
        bundle.putString("commentaries", publication.numberOfCommentaries.toString())
        return bundle
    }
    fun redirectWith(bundle:Bundle)
    {
        var intent = Intent(parentContext, IndividualConfession::class.java)
        intent.putExtras(bundle)
        ContextCompat.startActivity(parentContext, intent, bundle)
    }
}