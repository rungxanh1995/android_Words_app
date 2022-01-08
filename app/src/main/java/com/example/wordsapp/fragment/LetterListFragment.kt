package com.example.wordsapp.fragment

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.R
import com.example.wordsapp.adapter.LetterAdapter
import com.example.wordsapp.databinding.FragmentLetterListBinding


/**
 * A simple [Fragment] subclass.
 * Use the [LetterListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LetterListFragment : Fragment() {

    private var _binding: FragmentLetterListBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private var isLinearLayoutManager = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerView
        chooseLayout(recyclerView, isLinearLayoutManager)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu, menu)

        val layoutButton = menu.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager
                chooseLayout(recyclerView, isLinearLayoutManager)
                setIcon(item)
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun chooseLayout(view: RecyclerView, isLinear: Boolean) {
        view.layoutManager =
            if (isLinear) LinearLayoutManager(context)
            else GridLayoutManager(context, 4)
        view.adapter = LetterAdapter()
    }


    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null) return

        menuItem.icon =
            if (isLinearLayoutManager) ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_linear_layout)
            else ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_grid_layout)
    }
}