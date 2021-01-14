package com.example.youviewexercise

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_overview.*

class MasterFragment : Fragment() {

    private val viewModel: MasterViewModel by viewModels()
    private lateinit var viewAdapter: PersonListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewModel.viewState.observe(viewLifecycleOwner, { state ->
            updateView(state)
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getListOfPeople()
    }

    private fun setupRecyclerView() {
        viewManager = LinearLayoutManager(context)
        viewAdapter = PersonListAdapter(emptyList(), requireContext())
        person_overview_recycler_view.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun updateView(state: MasterViewState) {
        viewAdapter.update(state.persons)
    }

    private fun getListOfPeople() {
        viewModel.getListOfPeople()
    }
}