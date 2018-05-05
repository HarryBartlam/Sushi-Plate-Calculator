package com.simplyapp.plate.calculator.ui

import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import dagger.android.AndroidInjection

abstract class AbsBaseActivity<out T : BaseMvp.Presenter> : RxAppCompatActivity(), BaseMvp.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
    }

    protected abstract fun presenter(): T

    override fun onDestroy() {
        presenter().onDestroy()
        super.onDestroy()
    }
}
