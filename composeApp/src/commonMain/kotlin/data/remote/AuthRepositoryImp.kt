package data.remote

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.ActionCodeSettings
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import domain.model.User
import domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import platform.FirebaseManager

class AuthRepositoryImp : AuthRepository {

    private val auth: FirebaseAuth by lazy {
        FirebaseManager.init()
        Firebase.auth
    }
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override val currentUserId: String
        get() = auth.currentUser?.uid.toString()

    override val isAuthenticated: Boolean
        get() = auth.currentUser != null && auth.currentUser?.isAnonymous == false

    override val currentUser: Flow<User> =
        auth.authStateChanged.map { it?.let { User(it.uid, it.isAnonymous) } ?: User() }

    private suspend fun launchWithAwait(block : suspend  () -> Unit) {
        scope.async {
            block()
        }.await()
    }
    override suspend fun authenticate(email: String, password: String) {
        launchWithAwait {

            /*auth.signInWithCustomToken("eyJhbGciOiJSUzI1NiIsImtpZCI6ImMxNTQwYWM3MWJiOTJhYTA2OTNjODI3MTkwYWNhYmU1YjA1NWNiZWMiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vbm90ZXMtbWVjaGFuaWNhbCIsImF1ZCI6Im5vdGVzLW1lY2hhbmljYWwiLCJhdXRoX3RpbWUiOjE3MjEwMDIzOTIsInVzZXJfaWQiOiJvaG1tUVREZ1dUU2VacEt2NjM5YVVrRURodm4yIiwic3ViIjoib2htbVFURGdXVFNlWnBLdjYzOWFVa0VEaHZuMiIsImlhdCI6MTcyMTAwMjM5MiwiZXhwIjoxNzIxMDA1OTkyLCJlbWFpbCI6ImV4YW1wbGVAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7ImVtYWlsIjpbImV4YW1wbGVAZ21haWwuY29tIl19LCJzaWduX2luX3Byb3ZpZGVyIjoicGFzc3dvcmQifX0.OvuNsMKRcAlUATvs2eAWsfOPD1ZdiwAiI0b3NMS8QKxvM6O6gCzmHjRb2Pza8tXFqYIq0WK2q5XcuENhyF5JvCXTcKiq39IywhnRF5eyhSS_IKApqNV1jGHl1-t7gyI8p6mJRSTNju1gej7ksV5z11jXt27kQcqjo8Z0l-pHJXCoOzpXgNkr-gFX_11Wg-RXQXvzWGcxEzBA_EzwIaUMsb_6Q9xigXxQck2GHkxCfoW5UAlSYBUDNZLK2gsORnyZ0FE3D_bFWNsyVPH2Pazcr0sB9xNJTg8a7D9yJEfuUkf78SaJf_dj9Jemp9JPKOciAnltN839YzwbUP0gpw0WRg").let {
                println("user " + it.user)
                println(it.user?.email)
            }*/

            //return@launchWithAwait
            auth.signInWithEmailAndPassword(email, password)?.let {
                println("authenticate")
                println(it.user)
                it.user?.android?.getIdToken(true).let {
                    println("token")
                    println(it?.result?.token)
                }
                println(it.android)
            }
        }
    }
    override suspend fun createUser(email: String, password: String) {
        val result = launchWithAwait {
            auth.createUserWithEmailAndPassword(email, password)
        }
    }

    override suspend fun signOut() {

        if (auth.currentUser?.isAnonymous == true) {
            auth.currentUser?.delete()
        }

        auth.signOut()

        //create  new user anonymous session
    }
}