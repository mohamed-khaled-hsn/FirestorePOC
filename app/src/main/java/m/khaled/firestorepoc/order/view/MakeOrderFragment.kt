package m.khaled.firestorepoc.order.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_make_order.*
import m.khaled.firestorepoc.R
import m.khaled.firestorepoc.helpers.extensions.getProgressDialog
import m.khaled.firestorepoc.helpers.extensions.showToast
import m.khaled.firestorepoc.order.model.Order
import m.khaled.firestorepoc.order.viewmodel.OrderViewModel

class MakeOrderFragment : Fragment() {

    private lateinit var viewModel: OrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_make_order, container, false)

        viewModel = ViewModelProviders.of(this).get(OrderViewModel::class.java)
        subscribeToDataLoadingEvent()
        subscribeToErrorMessageEvent()
        subscribeToOrderAddedEvent()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_make_order.setOnClickListener {
            val order = Order(
                FirebaseAuth.getInstance().uid ?: "",
                edt_location.text.toString(),
                edt_time.text.toString(),
                edt_type.text.toString()
            )
            viewModel.addOrder(order)
        }
    }

    private fun subscribeToOrderAddedEvent() {
        viewModel.orderAddedLiveEvent.observe(viewLifecycleOwner, Observer {
            activity?.onBackPressed()
        })

    }

    private fun subscribeToErrorMessageEvent() {
        viewModel.errorMessageLiveEvent.observe(viewLifecycleOwner, Observer {
            showToast(it)
        })
    }

    private fun subscribeToDataLoadingEvent() {
        val progressDialog = getProgressDialog()
        viewModel.dataLoading.observe(viewLifecycleOwner, Observer { loading ->
            if (loading)
                progressDialog.show()
            else
                progressDialog.hide()
        })
    }

}
