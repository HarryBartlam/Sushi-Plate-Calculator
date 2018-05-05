package com.simplyapp.plate.calculator.ui.settings

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Subcomponent
interface SettingsComponent : AndroidInjector<SettingsActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SettingsActivity>()

    @Module(subcomponents = arrayOf(SettingsComponent::class))
    abstract class SettingsModule {
        @Binds
        @IntoMap
        @ActivityKey(SettingsActivity::class)
        internal abstract fun bindActivityInjectorFactory(builder: Builder): AndroidInjector.Factory<out Activity>

        @Binds
        internal abstract fun bindView(settingsActivity: SettingsActivity): SettingsMvp.View

        @Binds
        internal abstract fun bindPresenter(settingsPresenter: SettingsPresenter): SettingsMvp.Presenter
    }
}
