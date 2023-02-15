package com.me.myquran.persentation.detail_tafsir

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.me.myquran.domain.use_case.GetDetailTafsir
import com.me.myquran.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailTafsirViewModel @Inject constructor(
    private val getDetailTafsir: GetDetailTafsir,
    val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val nomor: Int = checkNotNull(savedStateHandle["nomor"])

    private val _state = MutableStateFlow(DetailTafsirState())
    val state: StateFlow<DetailTafsirState> get() = _state

    private val _event = MutableSharedFlow<DetailTafsirEvent>()
    val event: SharedFlow<DetailTafsirEvent> get() = _event

    init {
        doGetDaftarTafsir(nomor)
    }

    private fun doGetDaftarTafsir(nomor: Int) {
        viewModelScope.launch {
            getDetailTafsir
                .execute(nomor)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { response ->
                                _state.value = _state.value.copy(
                                    detailTafsir = response
                                )
                            }
                        }
                        is Resource.Error -> {
                            result.message?.let { error ->
                                _event.emit(DetailTafsirEvent.ShowToast(error))
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