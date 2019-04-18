package m.khaled.firestorepoc.helpers

import android.app.Dialog
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import m.khaled.firestorepoc.R

/**
 * Created by Mohamed Khaled on Wed, 17/Apr/2019 at 6:23 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
fun Fragment.getProgressDialog(): Dialog {
    val dialog = Dialog(context)
    dialog.setContentView(R.layout.prograss_bar_dialog)
    return dialog
}

fun Fragment.showToast(text: CharSequence) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(@StringRes resId: Int) {
    Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
}