package com.example.youviewexercise

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youviewexercise.models.Person

interface PersonListClickListener {
    fun onPersonClicked(person: Person)
}

class PersonListAdapter(private var personList: List<Person>, private val listener: PersonListClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MasterRowViewHolder(inflater.inflate(R.layout.person_recycler_master_row, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as MasterRowViewHolder
        if (personList.isNotEmpty()) {
            holder.bindView(person = personList[position], listener = listener)
        }
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    fun update(persons: List<Person>) {
        personList = persons
        notifyDataSetChanged()
    }
}
