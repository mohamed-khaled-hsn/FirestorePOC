package m.khaled.firestorepoc.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import m.khaled.firestorepoc.helpers.livedata.SingleLiveEvent

/**
 * Created by Mohamed Khaled on Thu, 30/May/2019 at 12:19 AM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */

open class BaseViewModel : ViewModel() {
    val errorMessageLiveEvent: SingleLiveEvent<String> = SingleLiveEvent()
    val dataLoading: MutableLiveData<Boolean> = MutableLiveData()
}