package m.khaled.firestorepoc.login.view


import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import m.khaled.firestorepoc.R
import m.khaled.firestorepoc.helpers.getProgressDialog
import m.khaled.firestorepoc.helpers.navigate
import m.khaled.firestorepoc.helpers.showToast

class LoginFragment : Fragment() {
    private lateinit var progressDialog: Dialog
    private val TAG = "mainTag"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        progressDialog = getProgressDialog()
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (FirebaseAuth.getInstance().currentUser != null) {
            navigate(R.id.action_loginFragment_to_carsFragment)
        } else {
            btn_register.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_register))
            btn_login.setOnClickListener {
                progressDialog.show()
                login()
            }
        }
    }

    private fun login() {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            edt_email.text.toString(),
            edt_password.text.toString()
        ).addOnCompleteListener {
            progressDialog.hide()
            if (it.isSuccessful) {
                navigate(R.id.action_loginFragment_to_carsFragment)
            } else {
                showToast("login failure")
            }
        }
    }
}
