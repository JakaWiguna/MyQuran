package com.me.myquran.persentation.daftar_surat

import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.me.myquran.R
import com.me.myquran._core.BaseActivity
import com.me.myquran.databinding.ActivityMainBinding
import com.me.myquran.domain.model.DaftarSurat
import com.me.myquran.persentation.detail_surat.DetailSuratActivity
import com.me.myquran.persentation.detail_tafsir.DetailTafsirActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DaftarSuratActivity : BaseActivity<ActivityMainBinding, DaftarSuratViewModel>() {

    override fun getLayoutResId() = R.layout.activity_main

    override fun getViewModelClass() = DaftarSuratViewModel::class.java

    override fun initViews() {
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        val adapter = DaftarSuratAdapter(object : DaftarSuratListener {
            override fun onDaftarSuratClick(daftarSurat: DaftarSurat) {
                val intent = Intent(this@DaftarSuratActivity, DetailSuratActivity::class.java)
                intent.putExtra("nomor", daftarSurat.nomor)
                startActivity(intent)
            }

            override fun onDaftarTafsirClick(daftarSurat: DaftarSurat) {
                val intent = Intent(this@DaftarSuratActivity, DetailTafsirActivity::class.java)
                intent.putExtra("nomor", daftarSurat.nomor)
                startActivity(intent)
            }
        })
        binding.rvList.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        val drawable = ContextCompat.getDrawable(this, R.drawable.divider)
        dividerItemDecoration.setDrawable(drawable!!)
        binding.rvList.addItemDecoration(dividerItemDecoration)

        lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                adapter.submitList(state.daftarSurahList)
            }
        }

        viewModel.event.onEach { _event ->
            when (_event) {
                is DaftarSuratEvent.ShowToast -> {
                    Snackbar.make(
                        binding.root,
                        _event.message, Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }.launchIn(lifecycleScope)
    }
}