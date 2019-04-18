package m.khaled.firestorepoc

import android.app.Application

/**
 * Created by Mohamed Khaled on Wed, 17/Apr/2019 at 4:51 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
class App : Application() {
    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}