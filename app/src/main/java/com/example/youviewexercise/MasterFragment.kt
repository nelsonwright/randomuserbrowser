package com.example.youviewexercise

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youviewexercise.models.Person
import com.example.youviewexercise.viewmodels.MasterViewModel
import com.example.youviewexercise.viewmodels.MasterViewModelFactory
import kotlinx.android.synthetic.main.fragment_overview.*
import javax.inject.Inject

class MasterFragment : Fragment(), PersonListClickListener {

    private lateinit var viewAdapter: PersonListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    @Inject
    lateinit var viewModelFactory: MasterViewModelFactory
    private val viewModel: MasterViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        (context.applicationContext as YouViewApplication).appComponent.inject(this)
        super.onAttach(context)
        getListOfPeople()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.viewState.observe(viewLifecycleOwner, { state ->
            updateView(state)
        })
    }

    override fun onPersonClicked(person: Person) {
        val action = MasterFragmentDirections.actionOverviewFragmentToDetailsFragment(person)
        findNavController().navigate(action)
    }

    private fun setupRecyclerView() {
        viewManager = LinearLayoutManager(context)
        viewAdapter = PersonListAdapter(emptyList(), this)
        person_overview_recycler_view.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun updateView(state: MasterViewState) {
        with(state) {
            when {
                loading -> {
                    progress_bar.isVisible = true
                    person_overview_recycler_view.isVisible = false
                    loading_error_message.isVisible = false
                }
                loadingError -> {
                    progress_bar.isVisible = false
                    person_overview_recycler_view.isVisible = false
                    loading_error_message.isVisible = true
                }
                else -> {
                    progress_bar.isVisible = false
                    person_overview_recycler_view.isVisible = true
                    loading_error_message.isVisible = false
                }
            }
        }

        viewAdapter.update(state.persons)
    }

    private fun getListOfPeople() {
        viewModel.getListOfPeople()
    }
}