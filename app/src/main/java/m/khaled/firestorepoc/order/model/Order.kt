package m.khaled.firestorepoc.order.model

/**
 * Created by Mohamed Khaled on Thu, 18/Apr/2019 at 3:27 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
data class Order(
    val userId: String = "",
    val location: String = "",
    val time: String = "",
    val type: String = ""
)