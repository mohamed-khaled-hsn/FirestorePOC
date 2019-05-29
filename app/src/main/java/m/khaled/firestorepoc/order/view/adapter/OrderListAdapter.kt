package m.khaled.firestorepoc.order.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.item_order.view.*
import m.khaled.firestorepoc.R
import m.khaled.firestorepoc.base.FirestoreBaseAdapter
import m.khaled.firestorepoc.order.model.Order

/**
 * Created by Mohamed Khaled on Thu, 18/Apr/2019 at 4:16 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */

/**
 * RecyclerView adapter for a list of [Order].
 */
open class OrderListAdapter(query: Query) :
    FirestoreBaseAdapter<OrderListAdapter.ViewHolder>(query) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_order, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getSnapshot(position).toObject(Order::class.java))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(order: Order?) {
            if (order == null) {
                return
            }

            itemView.tv_location.text = "Location: ${order.location}"
            itemView.tv_time.text = "Time: ${order.time}"
            itemView.tv_type.text = "Type: ${order.type}"

        }
    }
}