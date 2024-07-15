package platform

import android.os.Build
import applicationContext
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseOptions
import dev.gitlive.firebase.initialize


actual fun initializeFirebase() {
    Firebase.initialize(
        applicationContext,
        options = firebaseOptions
    )
}