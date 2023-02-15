package com.me.myquran.persentation.detail_tafsir

import android.graphics.text.LineBreaker
import android.os.Build
import android.view.Gravity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.me.myquran.R
import com.me.myquran._core.BaseActivity
import com.me.myquran.databinding.ADetailTafsirBinding
import com.me.myquran.domain.model.Tafsir
import com.me.myquran.utils.CommonUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailTafsirActivity : BaseActivity<ADetailTafsirBinding, DetailTafsirViewModel>() {

    override fun getLayoutResId() = R.layout.a_detail_tafsir

    override fun getViewModelClass() = DetailTafsirViewModel::class.java

    override fun initViews() {
        val number = intent.getIntExtra("nomor", 0)
        viewModel.savedStateHandle["nomor"] = number

        binding.lifecycleOwner = this
        binding.viewmodel = viewModel



        val adapter = TafsirAdapter(object : TafsirListener {
            override fun onShareClick(tafsir: Tafsir) {
                CommonUtils.shareText(
                    this@DetailTafsirActivity,
                    tafsir.teks.toString()
                )
            }

            override fun onBookmarkClick(tafsir: Tafsir) {
                Snackbar.make(
                    binding.root,
                    "onBookmarkClick", Snackbar.LENGTH_SHORT
                ).show()
            }
        })
        binding.rvList.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        val drawable = ContextCompat.getDrawable(this, R.drawable.divider)
        dividerItemDecoration.setDrawable(drawable!!)
        binding.rvList.addItemDecoration(dividerItemDecoration)

        lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                adapter.submitList(state.detailTafsir?.tafsir)
            }
        }

        viewModel.event.onEach { _event ->
            when (_event) {
                is DetailTafsirEvent.ShowToast -> {
                    Snackbar.make(
                        binding.root,
                        _event.message, Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }.launchIn(lifecycleScope)
    }
}