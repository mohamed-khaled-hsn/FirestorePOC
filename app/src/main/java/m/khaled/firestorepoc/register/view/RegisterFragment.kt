package m.khaled.firestorepoc.register.view


import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_register.*
import m.khaled.firestorepoc.R
import m.khaled.firestorepoc.User
import m.khaled.firestorepoc.helpers.getProgressDialog
import m.khaled.firestorepoc.helpers.navigate

class RegisterFragment : Fragment() {
    private lateinit var progressDialog: Dialog
    private val TAG = "register"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        progressDialog = getProgressDialog()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_register.setOnClickListener {
            progressDialog.show()
            register()
        }
    }

    private fun register() {
        val auth = FirebaseAuth.getInstance()
        val name = edt_name.text.toString()
        val email = edt_email.text.toString()
        val password = edt_password.text.toString()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    saveUserToFireStore(user, name, email)
                } else {
                    progressDialog.hide()
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", it.exception)
                    Toast.makeText(
                        context, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun saveUserToFireStore(user: FirebaseUser?, name: String, email: String) {
        FirebaseFirestore.getInstance().collection("Users")
            .add(User(user?.uid ?: "", name, email))
            .addOnSuccessListener {
                Toast.makeText(context, "successfully created profile", Toast.LENGTH_SHORT).show()
                progressDialog.hide()
                navigate(R.id.action_cars)
            }
            .addOnFailureListener {
                progressDialog.hide()
                Toast.makeText(context, "Failure profile", Toast.LENGTH_SHORT).show()
            }
    }


}
