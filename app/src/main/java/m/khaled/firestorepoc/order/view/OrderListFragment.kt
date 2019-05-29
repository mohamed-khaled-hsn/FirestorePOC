package m.khaled.firestorepoc.order.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_cars.tv_empty_view
import kotlinx.android.synthetic.main.fragment_order_list.*
import m.khaled.firestorepoc.R
import m.khaled.firestorepoc.helpers.extensions.showToast
import m.khaled.firestorepoc.order.view.adapter.OrderListAdapter

/**
 * Created by Mohamed Khaled on Thu, 18/Apr/2019 at 4:16 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
class OrderListFragment : Fragment() {
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
        activity?.title = getString(R.string.orders)
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
                    showEmptyView()
                } else {
                    showOrderList()
                }
            }

            override fun onError(e: FirebaseFirestoreException) {
                showToast("onError OrderListFragment ${e.message}")
            }
        }
        rv_orders.adapter = adapter
    }

    private fun showEmptyView() {
        rv_orders.visibility = View.GONE
        tv_empty_view.visibility = View.VISIBLE
    }

    private fun showOrderList() {
        tv_empty_view.visibility = View.GONE
        rv_orders.visibility = View.VISIBLE
    }

    override fun onStart() {
        super.onStart()
        adapter.apply {
            if (::adapter.isInitialized)
                startListening()
        }
    }

    override fun onStop() {
        super.onStop()
        adapter.apply {
            if (::adapter.isInitialized)
                stopListening()
        }
    }
}