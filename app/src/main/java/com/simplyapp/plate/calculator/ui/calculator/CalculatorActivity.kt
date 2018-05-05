package com.simplyapp.plate.calculator.ui.calculator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.Toolbar
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import com.simplyapp.plate.calculator.R
import com.simplyapp.plate.calculator.data.model.Plate
import com.simplyapp.plate.calculator.ui.NavigationBaseActivity
import com.simplyapp.plate.calculator.ui.views.DiscountDialog
import com.simplyapp.plate.calculator.utils.androidExt.getCompatColor
import kotlinx.android.synthetic.main.activity_calculator.*
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

class CalculatorActivity : NavigationBaseActivity<CalculatorMvp.Presenter>(), CalculatorMvp.View {

    @Inject
    lateinit var calculatorPresenter: CalculatorMvp.Presenter

    override fun presenter(): CalculatorMvp.Presenter = calculatorPresenter

    private lateinit var calculatorPlateAdapter: CalculatorPlateAdapter

    private var formatter: NumberFormat = NumberFormat.getCurrencyInstance(Locale.UK)

    private var animate: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        calculatorPlateAdapter = CalculatorPlateAdapter()
        calculatorPlateAdapter.setOnClick { plate: Plate, isPositive: Boolean, plateLocation: IntArray ->

            if (animate) {
                //make new image view
                val plateImageView = ImageView(this)
                calculator_layout.addView(plateImageView)

                //set size of image view
                val plateSize = resources.getDimension(R.dimen.plate_image_size)
                plateImageView.layoutParams.height = plateSize.toInt()
                plateImageView.layoutParams.width = plateSize.toInt()

                //elevate over card views
                plateImageView.elevation = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, resources.displayMetrics)

                //set image
                plateImageView.setImageResource(plate.plateType.image)
                plateImageView.setColorFilter(getCompatColor(plate.plateType.color))

                //display
                plateImageView.visibility = View.VISIBLE
                plateImageView.scaleX = 1f
                plateImageView.scaleY = 1f

                if (plateLocation.isNotEmpty() && isPositive) {

                    //set start minus status bar
                    val fromX = plateLocation[0].toFloat()
                    val fromY = plateLocation[1].toFloat() - getStatusBarHeight()

                    plateImageView.x = fromX
                    plateImageView.y = fromY

                    val endViewLocation = IntArray(2)
                    plate_total_textview.getLocationOnScreen(endViewLocation)
                    plateImageView.animate()
                            .translationX(endViewLocation[0].toFloat())
                            .translationY(endViewLocation[1].toFloat())
                            .scaleX(0f)
                            .scaleY(0f)
                            .alpha(0.25f)
                            .setInterpolator(AccelerateInterpolator())
                            .withEndAction {
                                calculator_layout.removeView(plateImageView)
                            }
                            .setDuration(1000)
                } else if (!isPositive) {
                    val endViewLocation = IntArray(2)
                    plate_total_textview.getLocationOnScreen(endViewLocation)

                    plateImageView.x = endViewLocation[0].toFloat()
                    plateImageView.y = endViewLocation[1].toFloat() - getStatusBarHeight()

                    plateImageView.animate()
                            .translationX(plateImageView.x)
                            .translationY(plateImageView.y - 250f)
                            .scaleX(0.75f)
                            .scaleY(0.75f)
                            .alpha(0f)
                            .setInterpolator(DecelerateInterpolator())
                            .withEndAction {
                                calculator_layout.removeView(plateImageView)
                            }
                            .setDuration(1500)
                }
            }
            calculatorPresenter.plateValueChanged(if (isPositive) plate.plateType.value else -(plate.plateType.value))
        }
        plate_recyclerview.adapter = calculatorPlateAdapter
        val displayMetrics = this.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        val noOfColumns = (dpWidth / 138).toInt()
        plate_recyclerview.layoutManager = GridLayoutManager(this, noOfColumns)

        plate_discount_layout.setOnClickListener {
            DiscountDialog(this, calculatorPresenter.getDiscount(), { calculatorPresenter.setDiscount(it) }).show()
        }

        calculatorPresenter.onCreate()

    }

    override fun setAnimate(animate: Boolean) {
        this.animate = animate
    }

    override fun onStop() {
        super.onStop()
        calculatorPresenter.savePlates(calculatorPlateAdapter.getData())
    }

    override fun setPlates(plates: List<Plate>) {
        calculatorPlateAdapter.setData(plates)
    }

    override fun setDiscount(discount: Int) {
        plate_discount_textview.text = discount.toString()

        plate_discount_prefix.visibility = if (discount.toInt() < 1) View.GONE else View.VISIBLE
    }

    override fun setTotal(plateTotal: Float) {
        plate_total_textview.text = formatter.format(Math.abs(plateTotal))
    }

    override fun showError() {
        Snackbar.make(calculator_layout, "There was an error please reinstall", Snackbar.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.calculator_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_clear -> {
                calculatorPresenter.clearPlates(calculatorPlateAdapter.getData())
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    companion object {
        @JvmStatic
        fun start(context: Activity) {
            val intent = Intent(context, CalculatorActivity::class.java)
            context.startActivity(intent)
        }
    }

    private fun getStatusBarHeight(): Int {
        //Todo this should/can be replaced!! with:
        // Comparing usable screen / real screen size
        //OR more correctly using setOnApplyWindowInsetsListener and get inset getSystemWindowInsetTop for true size
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    override fun getToolbar(): Toolbar = calculator_toolbar

    override val navId: Int
        get() = R.id.nav_calc

}