package m.khaled.firestorepoc.cars.model

/**
 * Created by Mohamed Khaled on Wed, 17/Apr/2019 at 7:41 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
data class Car(
    val userId: String = "",
    val make: String = "",
    val model: String = "",
    val color: String = "",
    val price: Int = 0
)