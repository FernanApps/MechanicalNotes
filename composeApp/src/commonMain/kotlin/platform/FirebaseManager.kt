package platform

import com.google.firebase.FirebasePlatform
import dev.gitlive.firebase.FirebaseOptions



object FirebaseManager {
    fun init(){
        FirebasePlatform.initializeFirebasePlatform(object : FirebasePlatform() {
            val storage = mutableMapOf<String, String>()
            override fun store(key: String, value: String) = storage.set(key, value)
            override fun retrieve(key: String) = storage[key]
            override fun clear(key: String) { storage.remove(key) }
            override fun log(msg: String) = println(msg)
        })
        initializeFirebase()
    }
}

internal val firebaseProjectId = "notes-mechanical"
internal val firebaseApiKey = "AIzaSyBJOHjiya9G1ro0GWMi77eOj88BwqbJ4BQ"
internal val firebaseAppId = "pe.fernan.app.notes.mechanical"

internal val firebaseOptions = FirebaseOptions(
    applicationId = firebaseAppId,
    apiKey = firebaseApiKey,
    projectId = firebaseProjectId
)


expect fun initializeFirebase()