package com.me.myquran.persentation.detail_surat

import com.me.myquran.domain.model.DetailSurat

data class DetailSuratState(
    var isLoading: Boolean = true,
    var detailSurat: DetailSurat? = null,
)
