package com.me.myquran.persentation.detail_tafsir

sealed class DetailTafsirEvent {
    data class ShowToast(val message: String) : DetailTafsirEvent()
}