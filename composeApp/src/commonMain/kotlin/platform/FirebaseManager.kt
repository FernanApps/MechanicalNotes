package platform

import dev.gitlive.firebase.FirebaseOptions

interface FirebaseManager {

    fun initialize()
}

internal val firebaseProjectId = "notes-mechanical"
internal val firebaseApiKey = "AIzaSyBJOHjiya9G1ro0GWMi77eOj88BwqbJ4BQ"
internal val firebaseAppId = "pe.fernan.app.notes.mechanical"

internal val firebaseOptions = FirebaseOptions(
    applicationId = firebaseAppId,
    apiKey = firebaseApiKey,
    projectId = firebaseProjectId
)


expect fun getFirebaseManager(): FirebaseManager