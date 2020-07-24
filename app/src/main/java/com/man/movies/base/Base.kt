package com.man.movies.base

import androidx.annotation.LayoutRes

interface Base : BaseView {

    @LayoutRes
    fun getLayoutResource(): Int

    fun initComponent()

}