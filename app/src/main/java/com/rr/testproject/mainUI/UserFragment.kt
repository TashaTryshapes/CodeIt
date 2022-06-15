package com.rr.testproject.mainUI

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.rr.testproject.R
import com.rr.testproject.base.BaseFragment
import com.rr.testproject.data.UserIdData.UserIdData
import com.rr.testproject.databinding.FragmentUserBinding
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_user.*


class UserFragment : BaseFragment<FragmentUserBinding, MainViewModel>() {

    override fun getLayout(): Int {
        return R.layout.fragment_user
    }

    override fun getVmClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.idData()
        init()


    }

    @SuppressLint("SetTextI18n")
    fun init() {
        viewModel.userIdData.observe(viewLifecycleOwner) {
            it as UserIdData
            val name = it.name
            binding.name.text = it.name
            binding.tvEmail.text = it.email
            val split: Array<String> = it.phone.split("x").toTypedArray()
            val phone = split[0]
            binding.tvPhoneNumber.text = "Phone number:- $phone"
            binding.tvAddress.text = "Address:- " + it.address.street + ", " + it.address.suite + ", " + it.address.city + ", " + it.address.zipcode
            binding.tvWebSite.text = "Website:- " + it.website
            binding.tvCompanyName.text = "Company Name:- " + it.company.name
            binding.tvCatchPhrase.text = "Catch Phrase:- " + it.company.catchPhrase
            binding.tvBs.text = "bs:- " + it.company.bs
        }

        binding.button.setOnClickListener {
            val str = viewModel.adapterMain.selectedItemDelete.value
            viewModel.deleteNoteSingle(str!!)
            Toast.makeText(mContext, "Post Deleted", Toast.LENGTH_SHORT).show()
            afterDeleting()
        }
    }

    fun afterDeleting() {
        val navDirections = UserFragmentDirections.actionUserFragmentToHomeFragment()
        Navigation.findNavController(button).navigate(navDirections)
        viewModel.screen = "Home"
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.viewModel = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                //BackPress Disabled
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
}