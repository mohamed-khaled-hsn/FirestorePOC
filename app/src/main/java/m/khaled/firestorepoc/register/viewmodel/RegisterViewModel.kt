package m.khaled.firestorepoc.register.viewmodel

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import m.khaled.firestorepoc.base.BaseViewModel
import m.khaled.firestorepoc.helpers.livedata.SingleLiveEvent
import m.khaled.firestorepoc.register.model.RegistrationFields
import m.khaled.firestorepoc.register.model.User

/**
 * Created by Mohamed Khaled on Wed, 29/May/2019 at 8:28 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */

class RegisterViewModel : BaseViewModel() {
    val registrationCompletedLiveEvent: SingleLiveEvent<String> = SingleLiveEvent()

    fun register(registrationFields: RegistrationFields) {
        val auth = FirebaseAuth.getInstance()
        val name = registrationFields.name
        val email = registrationFields.email
        val password = registrationFields.password
        dataLoading.value = true
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    saveUserToFireStore(auth.currentUser, name, email)
                } else {
                    // If sign in fails, display a message to the user.
                    dataLoading.value = false
                    errorMessageLiveEvent.value = "Error in registration"
                }
            }
    }

    private fun saveUserToFireStore(user: FirebaseUser?, name: String, email: String) {
        FirebaseFirestore.getInstance().collection("Users")
            .add(User(user?.uid ?: "", name, email))
            .addOnCompleteListener {
                dataLoading.value = false
                if (it.isSuccessful) {
                    registrationCompletedLiveEvent.call()
                } else
                    errorMessageLiveEvent.value = "Error saving to FireStore"
            }
    }
}