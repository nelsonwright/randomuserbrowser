package com.example.youviewexercise

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.youviewexercise.models.Person
import kotlinx.android.synthetic.main.person_recycler_master_row.view.*

class MasterRowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(person: Person) {
        itemView.person_title.text = person.title
        itemView.person_first_name.text = person.firstName
        itemView.person_last_name.text = person.lastName
    }
}

