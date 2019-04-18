package m.khaled.firestorepoc.helpers

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

/**
 * Created by Mohamed Khaled on Wed, 17/Apr/2019 at 6:57 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
fun Fragment.navigate(@IdRes idRes: Int) {
    NavHostFragment.findNavController(this).navigate(idRes)
}