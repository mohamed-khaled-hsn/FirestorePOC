package m.khaled.firestorepoc.order.viewmodel

import com.google.firebase.firestore.FirebaseFirestore
import m.khaled.firestorepoc.base.BaseViewModel
import m.khaled.firestorepoc.helpers.livedata.SingleLiveEvent
import m.khaled.firestorepoc.order.model.Order

/**
 * Created by Mohamed Khaled on Wed, 29/May/2019 at 6:37 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
class OrderViewModel : BaseViewModel() {
    val orderAddedLiveEvent: SingleLiveEvent<Unit> = SingleLiveEvent()

    fun addOrder(order: Order) {
        dataLoading.value = true
        FirebaseFirestore.getInstance().collection("Orders")
            .add(order).addOnCompleteListener {
                dataLoading.value = false
                if (it.isSuccessful) {
                    orderAddedLiveEvent.call()
                } else {
                    errorMessageLiveEvent.value = "Couldn't add order"
                }
            }
    }

}