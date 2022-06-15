package com.rr.testproject.mainUI

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.rr.testproject.R
import com.rr.testproject.base.BaseFragment
import com.rr.testproject.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment<FragmentHomeBinding, MainViewModel>() {

    override fun getLayout(): Int {
        return R.layout.fragment_home
    }

    override fun getVmClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        init()
    }

    private fun init() {
        val layoutManager = LinearLayoutManager(mContext)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerViewUserData.layoutManager = layoutManager
        binding.recyclerViewUserData.itemAnimator = DefaultItemAnimator()
        binding.recyclerViewUserData.adapter = viewModel.adapterMain
        handler()

        viewModel.getUserList()

        viewModel.adapterMain.selectedItem.observe(viewLifecycleOwner) {
            if (it.toString().isNotEmpty()) {
                viewModel.id.value = it
                val typeSCreen = viewModel.screen
                if (typeSCreen.equals("Home")) {
                    Toast.makeText(mContext, "Welcome to home screen", Toast.LENGTH_SHORT).show()
                } else {
                    val navDirections = HomeFragmentDirections.actionHomeFragmentToUserFragment()
                    Navigation.findNavController(recyclerViewUserData).navigate(navDirections)

                }
            }
        }

        binding.refresh.setOnClickListener {
            viewModel.refresh = "Refresh"
            viewModel.allpost.observe(viewLifecycleOwner) { list ->
                list?.let {
                    if (viewModel.refresh.equals("Refresh")) {
                        viewModel.deleteNoteAll(it)
                        viewModel.getUserList()
                    } else {
                        Toast.makeText(mContext, "Data Refreshed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.viewModel = null
    }

    private fun handler() { //        viewModel.allpost.observe(viewLifecycleOwner) { list ->
        //            list?.let {
        //                val size = it.size
        //                if (size != 0) {
        //                    viewModel.adapterMain.setData(it)
        //                } else {
        //                    viewModel.deleteNoteAll(it)
        //                    viewModel.getUserList()
        //                }
        //            }
        //        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() { //BackPress Disabled
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
}