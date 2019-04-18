package m.khaled.firestorepoc.cars.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_add_car.*
import m.khaled.firestorepoc.R
import m.khaled.firestorepoc.cars.model.Car
import m.khaled.firestorepoc.helpers.getProgressDialog
import m.khaled.firestorepoc.helpers.showToast

class AddCarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_car, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressDialog = getProgressDialog()
        btn_add_car.setOnClickListener {
            val car = Car(
                FirebaseAuth.getInstance().uid ?: "",
                edt_make.text.toString(),
                edt_model.text.toString(),
                edt_color.text.toString(),
                edt_price.text.toString().toInt()
            )
            progressDialog.show()
            FirebaseFirestore.getInstance().collection("Cars").add(car).addOnCompleteListener {
                progressDialog.hide()
                if (it.isSuccessful) {
                    showToast("Successfully added")
                    activity?.onBackPressed()
                } else {
                    showToast("Couldn't add car")
                }
            }
        }
    }
}
