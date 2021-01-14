package com.example.youviewexercise

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youviewexercise.models.Person

class PersonListAdapter(private var personList: List<Person>, private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MasterRowViewHolder(inflater.inflate(R.layout.person_recycler_master_row, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as MasterRowViewHolder
        if (personList.isNotEmpty()) {
            holder.bindView(person = personList[position])
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
