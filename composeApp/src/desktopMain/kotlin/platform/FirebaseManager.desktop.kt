package platform

import android.app.Application
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize



actual fun getFirebaseManager() = object : FirebaseManager {
    override fun initialize() {
        var applicationContext: Application = Application()

        Firebase.initialize(
            context = applicationContext,
            options = firebaseOptions
        )
    }
}