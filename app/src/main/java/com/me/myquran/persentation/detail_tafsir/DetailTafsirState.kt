package com.me.myquran.persentation.detail_tafsir

import com.me.myquran.domain.model.DetailTafsir

data class DetailTafsirState(
    var isLoading: Boolean = true,
    var detailTafsir: DetailTafsir? = null,
)
