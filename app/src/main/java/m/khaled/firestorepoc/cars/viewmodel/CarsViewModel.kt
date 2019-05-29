package m.khaled.firestorepoc.cars.viewmodel

import com.google.firebase.firestore.FirebaseFirestore
import m.khaled.firestorepoc.base.BaseViewModel
import m.khaled.firestorepoc.cars.model.Car
import m.khaled.firestorepoc.helpers.livedata.SingleLiveEvent

/**
 * Created by Mohamed Khaled on Thu, 30/May/2019 at 12:58 AM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
class CarsViewModel : BaseViewModel() {
    val carAddedLiveEvent: SingleLiveEvent<Unit> = SingleLiveEvent()

    fun addCar(car: Car) {
        FirebaseFirestore.getInstance().collection("Cars")
            .add(car).addOnCompleteListener {
                dataLoading.value = false
                if (it.isSuccessful) {
                    carAddedLiveEvent.call()
                } else {
                    errorMessageLiveEvent.value = "Couldn't add car"
                }
            }
    }
}