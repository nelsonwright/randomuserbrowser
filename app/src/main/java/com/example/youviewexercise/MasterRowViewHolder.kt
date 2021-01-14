package com.example.youviewexercise

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.youviewexercise.models.Person
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.person_recycler_master_row.view.*

class MasterRowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(person: Person) {
        itemView.person_name.text = person.title + " " + person.firstName + " " + person.lastName
        Picasso.get()
            .load(person.thumbnailUrl)
            .resize(48, 48)
            .centerCrop()
            .placeholder(R.drawable.ic_person)
            .error(R.drawable.ic_error)
            .into(itemView.person_image_thumbnail)
    }
}

