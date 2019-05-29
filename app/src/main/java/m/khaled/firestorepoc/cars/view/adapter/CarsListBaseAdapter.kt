package m.khaled.firestorepoc.cars.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.item_car.view.*
import m.khaled.firestorepoc.R
import m.khaled.firestorepoc.base.FirestoreBaseAdapter
import m.khaled.firestorepoc.cars.model.Car

/**
 * RecyclerView adapter for a list of [Car].
 */
open class CarsListBaseAdapter(query: Query, private val listener: OnCarSelectedListener) :
    FirestoreBaseAdapter<CarsListBaseAdapter.ViewHolder>(query) {
    interface OnCarSelectedListener {
        fun onCarSelected(carSelected: Car)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_car, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getSnapshot(position).toObject(Car::class.java), listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(car: Car?, listener: OnCarSelectedListener) {
            if (car == null) {
                return
            }
            itemView.setOnClickListener {
                listener.onCarSelected(car)
            }
            itemView.tv_make.text = "Make: ${car.make}"
            itemView.tv_model.text = "Model: ${car.model}"
            itemView.tv_color.text = "Color: ${car.color}"
            itemView.tv_price.text = "Price: ${car.price}"

        }
    }
}