package com.rr.testproject.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rr.testproject.modelFactory.ViewModelFactory

abstract class BaseActivity<VDB : ViewDataBinding, VM : ViewModel> : AppCompatActivity(){

    var mContext: Context? = null
    lateinit var viewModelFactory: ViewModelFactory
    internal lateinit var binding: VDB
    internal lateinit var viewModel: VM

    @LayoutRes
    protected abstract fun getLayout(): Int

    protected abstract fun getVmClass(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        viewModelFactory = ViewModelFactory(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[getVmClass()]
        binding = DataBindingUtil.setContentView(this, getLayout())
    }
}