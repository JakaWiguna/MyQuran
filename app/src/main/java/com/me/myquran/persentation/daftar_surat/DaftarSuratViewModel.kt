package com.me.myquran.persentation.daftar_surat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.me.myquran.domain.use_case.GetDaftarSurat
import com.me.myquran.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DaftarSuratViewModel @Inject constructor(
    private val getDaftarSurat: GetDaftarSurat,
) : ViewModel() {

    private val _state = MutableStateFlow(DaftarSuratState())
    val state: StateFlow<DaftarSuratState> get() = _state

    private val _event = MutableSharedFlow<DaftarSuratEvent>()
    val event: SharedFlow<DaftarSuratEvent> get() = _event

    init {
        doGetDaftarSurat()
    }

    private fun doGetDaftarSurat(
        fetchFromRemote: Boolean = false,
    ) {
        viewModelScope.launch {
            getDaftarSurat
                .execute(fetchFromRemote)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { response ->
                                _state.value = _state.value.copy(
                                    daftarSurahList = response
                                )
                            }
                        }
                        is Resource.Error -> {
                            result.message?.let { error ->
                                _event.emit(DaftarSuratEvent.ShowToast(error))
                            }
                        }
                        is Resource.Loading -> {
                            result.isLoading.let {
                                _state.value = _state.value.copy(
                                    isLoading = it
                                )
                            }
                        }
                    }
                }
        }
    }
}