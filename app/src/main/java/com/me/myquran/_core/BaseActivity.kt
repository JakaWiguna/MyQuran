package com.me.myquran._core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.me.myquran.utils.CommonUtils

abstract class BaseActivity<T : ViewDataBinding, V : ViewModel> : AppCompatActivity() {

    protected lateinit var binding: T
    protected lateinit var viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!CommonUtils.isDarkMode(this)) {
            CommonUtils.setupStatusBar(this, window, android.R.color.transparent)
        }

        binding = DataBindingUtil.setContentView(this, getLayoutResId())
        viewModel = ViewModelProvider(this)[getViewModelClass()]

        initViews()
    }

    abstract fun getLayoutResId(): Int

    abstract fun getViewModelClass(): Class<V>

    abstract fun initViews()
}