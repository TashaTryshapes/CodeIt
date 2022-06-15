package com.rr.testproject.base

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

abstract class BaseFragment<VDB : ViewDataBinding, VM : ViewModel> : Fragment() {

     var mDialog: Dialog? = null

    protected var mContext: Context? = null
    protected var activity: Activity? = null

    internal lateinit var binding: VDB
    internal lateinit var viewModel: VM

    @LayoutRes
    protected abstract fun getLayout(): Int

    protected abstract fun getVmClass(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = getActivity()
        activity = getActivity()
        viewModel = ViewModelProvider((activity as ViewModelStoreOwner?)!!)[getVmClass()]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        return binding.root
    }
}