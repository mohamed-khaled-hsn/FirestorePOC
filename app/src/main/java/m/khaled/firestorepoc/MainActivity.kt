package m.khaled.firestorepoc

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.firebase.ui.auth.AuthUI
import com.google.firebase.firestore.FirebaseFirestore
import m.khaled.firestorepoc.helpers.extensions.getProgressDialog
import m.khaled.firestorepoc.helpers.extensions.navigate
import m.khaled.firestorepoc.helpers.extensions.showToast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseFirestore.setLoggingEnabled(true)
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.carsFragment, R.id.loginFragment, R.id.registerFragment) //Top-level fragments
        )
        setupActionBarWithNavController(findNavController(R.id.fragment_nav_host), appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragment_nav_host).navigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sign_out -> {
                val progressDialog = getProgressDialog()
                progressDialog.show()
                AuthUI.getInstance().signOut(this)
                    .addOnCompleteListener {
                        progressDialog.hide()
                        if (it.isSuccessful)
                            navigate(R.id.action_global_loginFragment)
                        else
                            showToast(R.string.logout_failed)
                    }
                true //Consume it here
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
