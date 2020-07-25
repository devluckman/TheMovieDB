package com.man.movies.base

import androidx.annotation.LayoutRes

interface Base {

    @LayoutRes
    fun getLayoutResource(): Int

    fun initComponent()

}