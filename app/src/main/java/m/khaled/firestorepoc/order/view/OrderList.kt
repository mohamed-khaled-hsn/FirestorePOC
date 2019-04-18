package m.khaled.firestorepoc.order.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_cars.tv_empty_view
import kotlinx.android.synthetic.main.fragment_order_list.*
import m.khaled.firestorepoc.R
import m.khaled.firestorepoc.helpers.showToast
import m.khaled.firestorepoc.order.view.adapter.OrderListAdapter

/**
 * Created by Mohamed Khaled on Thu, 18/Apr/2019 at 4:16 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
class OrderList : Fragment() {
    private lateinit var adapter: OrderListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val mQuery = FirebaseFirestore.getInstance().collection("Orders")
            .whereEqualTo("userId", FirebaseAuth.getInstance().uid)
            .limit(20)
        initRecyclerView(mQuery)
    }

    private fun initRecyclerView(mQuery: Query) {
        adapter = object : OrderListAdapter(mQuery) {
            override fun onDataChanged() {
                // Show/hide content if the query returns empty.
                if (itemCount == 0) {
                    rv_orders.visibility = View.GONE
                    tv_empty_view.visibility = View.VISIBLE
                } else {
                    tv_empty_view.visibility = View.GONE
                    rv_orders.visibility = View.VISIBLE
                }
            }

            override fun onError(e: FirebaseFirestoreException) {
                // Show a snackbar on errors
                showToast("onError OrderList ${e.message}")
            }
        }
        val layoutManager = LinearLayoutManager(context)
        rv_orders.layoutManager = layoutManager
        rv_orders.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
        rv_orders.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}