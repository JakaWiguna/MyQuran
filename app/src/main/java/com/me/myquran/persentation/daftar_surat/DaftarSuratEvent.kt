package com.me.myquran.persentation.daftar_surat

sealed class DaftarSuratEvent {
    data class ShowToast(val message: String) : DaftarSuratEvent()
}