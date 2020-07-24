package com.man.movies.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.man.movies.App
import com.man.movies.di.component.ActivityComponent
import com.man.movies.di.component.ApplicationComponent
import com.man.movies.di.component.DaggerActivityComponent
import com.man.movies.di.module.ActivityModule
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity : AppCompatActivity(), Base {

    private val compositeDisposable: CompositeDisposable?
    protected lateinit var activityComponent: ActivityComponent
    init {
        this.compositeDisposable = CompositeDisposable()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            setContentView(getLayoutResource())
            activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(ActivityModule())
                .build()
            initComponent()
        }catch (e: OutOfMemoryError) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        compositeDisposable?.clear()
        super.onDestroy()
    }

    fun getApplicationComponent(): ApplicationComponent {
        return (application as App).getApplicationComponent()
    }

}