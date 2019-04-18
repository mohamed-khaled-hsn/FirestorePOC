package m.khaled.firestorepoc.order.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_make_order.*
import m.khaled.firestorepoc.R
import m.khaled.firestorepoc.helpers.getProgressDialog
import m.khaled.firestorepoc.helpers.showToast
import m.khaled.firestorepoc.order.model.Order

class MakeOrderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_make_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressDialog = getProgressDialog()
        btn_make_order.setOnClickListener {
            val order = Order(
                FirebaseAuth.getInstance().uid ?: "",
                edt_location.text.toString(),
                edt_time.text.toString(),
                edt_type.text.toString()
            )
            progressDialog.show()
            FirebaseFirestore.getInstance().collection("Orders")
                .add(order).addOnCompleteListener {
                    progressDialog.hide()
                    if (it.isSuccessful) {
                        showToast("Successfully added order")
                        activity?.onBackPressed()
                    } else {
                        showToast("Couldn't add order")
                    }
                }
        }
    }
}
