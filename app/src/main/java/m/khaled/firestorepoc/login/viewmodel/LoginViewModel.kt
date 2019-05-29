package m.khaled.firestorepoc.login.viewmodel

import com.google.firebase.auth.FirebaseAuth
import m.khaled.firestorepoc.base.BaseViewModel
import m.khaled.firestorepoc.helpers.livedata.SingleLiveEvent

/**
 * Created by Mohamed Khaled on Wed, 29/May/2019 at 6:38 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
class LoginViewModel : BaseViewModel() {
    val signedInLiveEvent: SingleLiveEvent<Unit> = SingleLiveEvent()

    fun login(email: String, password: String) {
        dataLoading.value = true
        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                dataLoading.value = false
                if (it.isSuccessful) {
                    signedInLiveEvent.call()
                } else {
                    errorMessageLiveEvent.value = it.exception?.toString()
                }
            }
    }

}