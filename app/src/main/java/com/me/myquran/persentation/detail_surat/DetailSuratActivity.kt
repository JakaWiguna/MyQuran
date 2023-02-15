package com.me.myquran.persentation.detail_surat

import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.me.myquran.R
import com.me.myquran._core.BaseActivity
import com.me.myquran.databinding.ADetailSuratBinding
import com.me.myquran.domain.model.Ayat
import com.me.myquran.utils.CommonUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailSuratActivity : BaseActivity<ADetailSuratBinding,DetailSuratViewModel>() {

    override fun getLayoutResId() = R.layout.a_detail_surat

    override fun getViewModelClass() = DetailSuratViewModel::class.java

    override fun initViews() {
        val number = intent.getIntExtra("nomor", 0)
        viewModel.savedStateHandle["nomor"] = number

        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        val adapter = AyatAdapter(object : AyatListener {
            override fun onShareClick(ayat: Ayat) {
                CommonUtils.shareText(
                    this@DetailSuratActivity,
                    ayat.teksArab + "\n\n"
                            + ayat.teksLatin + "\n\n"
                            + ayat.teksIndonesia
                )
            }

            override fun onPlayClick(ayat: Ayat) {
                Snackbar.make(
                    binding.root,
                    "onPlayClick", Snackbar.LENGTH_SHORT
                ).show()
            }

            override fun onBookmarkClick(ayat: Ayat) {
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
                adapter.submitList(state.detailSurat?.ayat)
            }
        }

        viewModel.event.onEach { _event ->
            when (_event) {
                is DetailSuratEvent.ShowToast -> {
                    Snackbar.make(
                        binding.root,
                        _event.message, Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }.launchIn(lifecycleScope)
    }
}