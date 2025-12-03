package cl.duoc.entrega2

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

object FirebaseService {

    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseFirestore.getInstance()

    // --------------------------
    // LOGIN
    // --------------------------
    suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // --------------------------
    // REGISTRO
    // --------------------------
    suspend fun register(email: String, password: String): Result<Unit> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // --------------------------
    // CRUD — GUARDAR UBICACIÓN
    // --------------------------
    suspend fun saveLocation(lat: Double, lng: Double) {
        val uid = auth.currentUser?.uid ?: return

        val data = mapOf(
            "lat" to lat,
            "lng" to lng,
            "timestamp" to System.currentTimeMillis()
        )

        database.collection("locations")
            .document(uid)
            .collection("history")
            .add(data)
            .await()
    }

    // --------------------------
    // CRUD — GUARDAR CONTACTO
    // --------------------------
    suspend fun saveContact(name: String, phone: String) {
        val uid = auth.currentUser?.uid ?: return

        val data = mapOf(
            "name" to name,
            "phone" to phone
        )

        database.collection("contacts")
            .document(uid)
            .collection("list")
            .add(data)
            .await()
    }

    // --------------------------
    // CRUD — GUARDAR ALERTA
    // --------------------------
    suspend fun saveAlert(message: String, lat: Double, lng: Double) {
        val uid = auth.currentUser?.uid ?: return

        val data = mapOf(
            "message" to message,
            "lat" to lat,
            "lng" to lng,
            "timestamp" to System.currentTimeMillis()
        )

        database.collection("alerts")
            .document(uid)
            .collection("sentAlerts")
            .add(data)
            .await()
    }
}
