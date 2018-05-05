package com.simplyapp.plate.calculator.ui.calculator

import android.app.Activity

import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Subcomponent
interface CalculatorComponent : AndroidInjector<CalculatorActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<CalculatorActivity>()

    @Module(subcomponents = arrayOf(CalculatorComponent::class))
    abstract class CalculatorModule {
        @Binds
        @IntoMap
        @ActivityKey(CalculatorActivity::class)
        internal abstract fun bindActivityInjectorFactory(builder: CalculatorComponent.Builder): AndroidInjector.Factory<out Activity>

        @Binds
        internal abstract fun bindView(calculatorActivity: CalculatorActivity): CalculatorMvp.View

        @Binds
        internal abstract fun bindPresenter(calculatorPresenter: CalculatorPresenter): CalculatorMvp.Presenter
    }
}
