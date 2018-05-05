package com.simplyapp.plate.calculator.ui.calculator

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.NO_POSITION
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.simplyapp.plate.calculator.R
import com.simplyapp.plate.calculator.data.model.Plate
import com.simplyapp.plate.calculator.utils.androidExt.getColor
import kotlinx.android.synthetic.main.element_plate.view.*

class CalculatorPlateAdapter constructor() : RecyclerView.Adapter<CalculatorPlateAdapter.ViewHolder>() {

    lateinit var click: (Plate, Boolean, IntArray) -> Unit

    var plateList = listOf<Plate>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.element_plate, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        plateList.get(holder.adapterPosition).let { plate ->
            val plateType = plate.plateType

            holder.itemView.plate_imageview.setImageResource(plateType.image)

            holder.itemView.plate_imageview.setColorFilter(holder.getColor(plateType.color))

            holder.itemView.plate_value_text.text = plate.name
            holder.itemView.plate_counter.text = plate.numberOfPlates.toString()
            holder.itemView.plate_plus.setOnClickListener {
                if (holder.adapterPosition != NO_POSITION) {
                    plateChange(holder.adapterPosition, true, holder.itemView)
                }
            }
            holder.itemView.plate_neg.setOnClickListener {
                if (holder.adapterPosition != NO_POSITION) {
                    plateChange(holder.adapterPosition, false, holder.itemView)
                }
            }

        }
    }

    fun plateChange(position: Int, isPositive: Boolean, view: View) {
        val locationArray = IntArray(2)
        view.plate_imageview.getLocationOnScreen(locationArray)
        val plate = plateList.get(position) ?: return

        if (plate.numberOfPlates == 0 && !isPositive) {
            return
        }

        plate.numberOfPlates += if (isPositive) 1 else -1
        view.plate_counter.text = (plate.numberOfPlates).toString()

        click(plate, isPositive, locationArray)
    }

    override fun getItemCount(): Int = plateList.size

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    fun setOnClick(click: (Plate, Boolean, IntArray) -> Unit) {
        this.click = click
    }

    fun setData(plateList: List<Plate>) {
        this.plateList = plateList
        notifyDataSetChanged()
    }

    fun getData(): List<Plate> = plateList
}