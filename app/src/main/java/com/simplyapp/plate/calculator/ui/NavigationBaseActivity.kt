package com.simplyapp.plate.calculator.ui

import android.annotation.SuppressLint
import android.content.Context
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.simplyapp.plate.calculator.R
import com.simplyapp.plate.calculator.ui.calculator.CalculatorActivity
import com.simplyapp.plate.calculator.ui.settings.SettingsActivity
import kotlinx.android.synthetic.main.nav_header_navigation_base.*

abstract class NavigationBaseActivity<T : BaseMvp.Presenter> : BaseActivity<T>() {

    private var navigationView: NavigationView? = null
    private var drawerLayout: DrawerLayout? = null
    private var navigationHeader: View? = null
    private var navigationMenu: Menu? = null

    protected abstract fun getToolbar(): Toolbar

    private val drawerListener: DrawerLayout.DrawerListener
        get() = object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            }

            override fun onDrawerOpened(drawerView: View) {
                nav_cat.animate().rotationBy(180f).duration = 3000
            }

            override fun onDrawerClosed(drawerView: View) {
                nav_cat.animate().rotationBy(180f).duration = 3000
                // Animation will not correctly complete but i like the effect, simply lower animation speed or correct on close
            }

            override fun onDrawerStateChanged(newState: Int) {
                val view = this@NavigationBaseActivity.currentFocus
                if (view != null) {
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                }
            }
        }

    protected abstract val navId: Int

    override fun setContentView(layoutResID: Int) {
        @SuppressLint("InflateParams")
        val view = layoutInflater.inflate(R.layout.navigation_base, null) as ViewGroup
        val content = layoutInflater.inflate(layoutResID, view, false)
        view.addView(content, 0)
        super.setContentView(view)

        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationHeader = navigationView!!.getHeaderView(0)
        navigationMenu = navigationView!!.menu

        val toolbar = getToolbar()
        toolbar.let {
            setSupportActionBar(toolbar)
            toolbar.setNavigationIcon(R.drawable.ic_ham_menu)
            toolbar.setNavigationOnClickListener { _ -> drawerLayout!!.openDrawer(GravityCompat.START) }
        }

        drawerLayout!!.addDrawerListener(drawerListener)

        navigationView!!.itemIconTintList = null
        navigationView!!.setNavigationItemSelectedListener { item ->
            drawerLayout!!.closeDrawers()
            if (item.itemId == navId) {
                return@setNavigationItemSelectedListener true
            }
            drawerLayout!!.postDelayed({
                when (item.itemId) {
                    R.id.nav_calc -> {
                        CalculatorActivity.start(this)
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    }
                    R.id.nav_settings -> {
                        SettingsActivity.start(this)
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                        finish()
                    }
                }
            }, 300)
            true
        }
    }

    override fun onStart() {
        super.onStart()
        navigationView!!.setCheckedItem(navId)
    }

}
