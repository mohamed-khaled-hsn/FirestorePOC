package m.khaled.firestorepoc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseFirestore.setLoggingEnabled(true)
        //navigation handles fragments transactions
    }

    override fun onSupportNavigateUp(): Boolean {
        val nav = NavController(this)
        return nav.navigateUp()
    }
}
