package cl.duoc.ejercicio

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.stylusHoverIcon
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    username: String,
    onLogout: (() -> Unit)? = null // callback opcional
) {
    Card (modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Card {
                Column(Modifier.padding(16.dp)) {
                    Text("Nombre: $username")
                    Text("Rol: Estudiante")
                }
            }
            Text(
                "Perfil de Usuario", style =
                    MaterialTheme.typography.headlineMedium
            )
            Spacer(Modifier.height(24.dp))
            Text(
                "Nombre de usuario: $username", style =
                    MaterialTheme.typography.bodyLarge
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "Email: $username@ejemplo.com", style =
                    MaterialTheme.typography.bodyLarge
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "Teléfono: +56 9 1234 5678", style =
                    MaterialTheme.typography.bodyLarge
            )
            Spacer(Modifier.height(24.dp))
            Button(onClick = { onLogout?.invoke() }) {
                Text("Cerrar sesión")
            }
        }
    }
}