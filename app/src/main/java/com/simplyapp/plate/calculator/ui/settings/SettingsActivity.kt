package com.simplyapp.plate.calculator.ui.settings

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.simplyapp.plate.calculator.R
import com.simplyapp.plate.calculator.ui.NavigationBaseActivity
import kotlinx.android.synthetic.main.activity_settings.*
import javax.inject.Inject

class SettingsActivity : NavigationBaseActivity<SettingsMvp.Presenter>(), SettingsMvp.View {
    @Inject
    lateinit var presenter: SettingsMvp.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)//See Layout and custom view/dialogs for Managing pref values
        supportActionBar?.let {
            it.title = getString(R.string.toolbar_settings_title)
        }
    }

    override fun presenter(): SettingsMvp.Presenter = presenter

    override fun getToolbar(): Toolbar = settings_toolbar

    override val navId: Int
        get() = R.id.nav_settings

    companion object {
        @JvmStatic
        fun start(context: Activity) {
            val intent = Intent(context, SettingsActivity::class.java)
            context.startActivity(intent)
        }
    }
}
