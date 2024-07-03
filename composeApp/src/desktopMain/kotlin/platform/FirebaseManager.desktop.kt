package platform

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize



actual fun getFirebaseManager() = object : FirebaseManager {
    override fun initialize() {
        Firebase.initialize(
            options = firebaseOptions
        )
    }
}