package com.me.myquran.persentation.detail_surat

sealed class DetailSuratEvent {
    data class ShowToast(val message: String) : DetailSuratEvent()
}