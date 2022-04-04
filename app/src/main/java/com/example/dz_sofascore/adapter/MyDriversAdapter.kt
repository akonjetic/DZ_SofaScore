package com.example.dz_sofascore.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.dz_sofascore.R
import com.example.dz_sofascore.databinding.MyDriversItemViewBinding
import com.example.dz_sofascore.model.F1Driver
import com.example.dz_sofascore.my_drivers.ChosenDriver
import com.example.dz_sofascore.my_drivers.EXTRA_DRIVER

class MyDriversAdapter(
    private val context: Context,
    private val myDriversList: ArrayList<F1Driver>
) : RecyclerView.Adapter<MyDriversAdapter.MyDriversViewHolder>() {

    class MyDriversViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = MyDriversItemViewBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDriversViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.my_drivers_item_view, parent, false)
        return MyDriversViewHolder(view)
    }

    @SuppressLint("NewApi", "SetTextI18n")
    override fun onBindViewHolder(holder: MyDriversViewHolder, position: Int) {
        val f1Driver = myDriversList[position]
        holder.binding.driverFirstName.text = f1Driver.firstName + " " + f1Driver.lastName
        holder.binding.driverGender.text = f1Driver.gender
        holder.binding.driverAge.text = f1Driver.age.toString()
        holder.binding.driverTeam.text = f1Driver.team
        holder.binding.driverImage.load(f1Driver.imageURL) {
            placeholder(R.drawable.driver_profile_image)
            error(R.drawable.ic_racer)
            transformations(CircleCropTransformation())
        }

        when (f1Driver.gender) {
            "M" -> {
                holder.binding.root.backgroundTintList = ColorStateList.valueOf(context.getColor(R.color.teal_200))
            }
            "F" -> {
                holder.binding.root.backgroundTintList = ColorStateList.valueOf(context.getColor(R.color.purple_200))
            }
            "OTHER" -> {
                holder.binding.root.backgroundTintList = ColorStateList.valueOf(context.getColor(R.color.yellow_200))
            }
        }

        holder.binding.root.setOnClickListener {
            val intent = Intent(context, ChosenDriver::class.java).apply {
                putExtra(EXTRA_DRIVER, f1Driver)
            }

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return myDriversList.size
    }
}
