package m.khaled.firestorepoc.helpers.extensions

import android.app.Activity
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import m.khaled.firestorepoc.R

/**
 * Created by Mohamed Khaled on Wed, 17/Apr/2019 at 6:57 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
fun Fragment.navigate(@IdRes idRes: Int) {
    NavHostFragment.findNavController(this).navigate(idRes)
}

fun Activity.navigate(@IdRes idRes: Int) {
    findNavController(R.id.fragment_nav_host).navigate(idRes)
}