package m.khaled.firestorepoc.cars.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_add_car.*
import m.khaled.firestorepoc.R
import m.khaled.firestorepoc.cars.model.Car
import m.khaled.firestorepoc.cars.viewmodel.CarsViewModel
import m.khaled.firestorepoc.helpers.extensions.getProgressDialog
import m.khaled.firestorepoc.helpers.extensions.showToast

class AddCarFragment : Fragment() {

    private lateinit var viewModel: CarsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_car, container, false)

        viewModel = ViewModelProviders.of(this).get(CarsViewModel::class.java)
        subscribeToDataLoadingEvent()
        subscribeToErrorMessageEvent()
        subscribeToCarAddedEvent()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_add_car.setOnClickListener {
            val car = Car(
                FirebaseAuth.getInstance().uid ?: "",
                edt_make.text.toString(),
                edt_model.text.toString(),
                edt_color.text.toString(),
                edt_price.text.toString().toInt()
            )
            viewModel.addCar(car)
        }
    }

    private fun subscribeToCarAddedEvent() {
        viewModel.carAddedLiveEvent.observe(viewLifecycleOwner, Observer {
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
