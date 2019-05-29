package m.khaled.firestorepoc.cars.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_cars.*
import m.khaled.firestorepoc.R
import m.khaled.firestorepoc.cars.model.Car
import m.khaled.firestorepoc.cars.view.adapter.CarsListBaseAdapter
import m.khaled.firestorepoc.helpers.extensions.navigate
import m.khaled.firestorepoc.helpers.extensions.showToast

class CarsFragment : Fragment(), CarsListBaseAdapter.OnCarSelectedListener {
    private lateinit var adapter: CarsListBaseAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cars, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val mQuery = FirebaseFirestore.getInstance().collection("Cars")
            .whereEqualTo("userId", FirebaseAuth.getInstance().uid)
            .limit(20)
        initRecyclerView(mQuery)

        fab_add_car.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_carsFragment_to_addCarFragment)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_cars_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.my_orders -> {
                navigate(R.id.action_carsFragment_to_orderList)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initRecyclerView(mQuery: Query) {
        adapter = object : CarsListBaseAdapter(mQuery, this@CarsFragment) {
            override fun onDataChanged() {
                // Show/hide content if the query returns empty.
                if (itemCount == 0) {
                    showEmptyView()
                } else {
                    showCarsList()
                }
            }

            override fun onError(e: FirebaseFirestoreException) {
                // Show a snackbar on errors
                showToast("onError carsAdapter")
            }
        }
        rv_cars.adapter = adapter
    }

    private fun showEmptyView() {
        rv_cars.visibility = View.GONE
        tv_empty_view.visibility = View.VISIBLE
    }

    private fun showCarsList() {
        tv_empty_view.visibility = View.GONE
        rv_cars.visibility = View.VISIBLE
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

    override fun onCarSelected(carSelected: Car) {
        navigate(R.id.action_carsFragment_to_makeOrderFragment)
    }
}
