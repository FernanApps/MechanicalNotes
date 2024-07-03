package platform

import com.google.firebase.Firebase


actual fun getFirebaseManager() = object : FirebaseManager {
    override fun initialize() {
        Firebase.initialize(
            options = firebaseOptions
        )
    }
}