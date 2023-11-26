package com.shist.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.shist.domain.ScientistItem
import com.shist.ui.adapters.ScientistsAdapter
import com.shist.ui.databinding.FragmentScientistsBinding
import org.koin.core.component.KoinComponent

class ScientistsFragment : Fragment(), KoinComponent {

    companion object {
        private const val KEY_SCIENTIST_LIST = "scientist_list"

        fun newInstance(scientistList: List<ScientistItem?>?)
        = ScientistsFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList(KEY_SCIENTIST_LIST, ArrayList(scientistList?.toMutableList() ?: emptyList()))
            }
        }
    }

    private var _binding: FragmentScientistsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScientistsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val scientists = arguments?.getParcelableArrayList<ScientistItem>(KEY_SCIENTIST_LIST)?.toList()
        val adapter = ScientistsAdapter(){
           /* val prefs = requireContext().getSharedPreferences("SCIENTIST_PREFS", Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("SCIENTIST_ID", it.id)
            editor.apply()
            requireActivity().supportFragmentManager.popBackStack()*/
            (requireActivity().supportFragmentManager.fragments[requireActivity().supportFragmentManager.fragments.lastIndex - 1] as MapFragment).showScientistMarkers(it.id)
            requireActivity().supportFragmentManager.popBackStack()
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        adapter.submitList(scientists)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}