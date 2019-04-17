package m.khaled.firestorepoc

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "mainTag"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Access a Cloud Firestore instance from your Activity
        val db = FirebaseFirestore.getInstance()
        val task = db.collection("products").whereEqualTo("name", "IphoneX")

        task.get().addOnSuccessListener { result: QuerySnapshot ->
            hideProgressBar()
            tv_product_name.text = result.documents[0].getString("name")
            tv_product_price.text = result.documents[0].get("price")?.toString()
        }
            .addOnFailureListener { exception ->
                hideProgressBar()
                Log.w(TAG, "Error getting documents.", exception)
            }

    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
        group_text.visibility = View.VISIBLE
    }

    private fun showProgressBar() {
        group_text.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }
}
