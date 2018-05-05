package com.simplyapp.plate.calculator.ui.settings

import com.simplyapp.plate.calculator.ui.BasePresenter
import javax.inject.Inject

class SettingsPresenter @Inject constructor(settingsView: SettingsMvp.View) : BasePresenter<SettingsMvp.View>(settingsView), SettingsMvp.Presenter
