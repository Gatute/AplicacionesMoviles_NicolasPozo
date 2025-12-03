package cl.duoc.entrega2

import android.annotation.SuppressLint
import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@SuppressLint("MissingPermission")
@Composable
fun PantallaPrincipal(navController: NavController) {

    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    var userLocation by remember { mutableStateOf<LatLng?>(null) }

    // Obtener ubicación real una vez
    LaunchedEffect(true) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                userLocation = LatLng(it.latitude, it.longitude)
            }
        }
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            userLocation ?: LatLng(-33.4489, -70.6693),
            15f
        )
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // MAPA
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(zoomControlsEnabled = true)
        ) {
            userLocation?.let {
                Marker(
                    state = MarkerState(position = it),
                    title = "Tu ubicación",
                    snippet = "Ubicación actual detectada"
                )
            }
        }

        // BARRA INFERIOR
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(0xFFDADADA))
                .border(width = 2.dp, color = Color.Black)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = { navController.navigate("editMessage") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDADADA)),
                modifier = Modifier.width(80.dp).height(80.dp),
                shape = RectangleShape
            ) {
                Icon(Icons.Default.Email, contentDescription = "Mensaje")
                Spacer(Modifier.width(8.dp))
                Text("Mensaje")
            }

            Spacer(Modifier.width(40.dp))

            Button(
                onClick = { /* Navegar a pantalla de notificaciones */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDADADA)),
                modifier = Modifier.width(80.dp).height(80.dp),
                shape = RectangleShape
            ) {
                Icon(Icons.Default.Notifications, contentDescription = "Notificaciones")
                Spacer(Modifier.width(8.dp))
                Text("Notificaciones")
            }
        }

        // BOTÓN FLOTANTE
        FloatingActionButton(
            onClick = { navController.navigate("editMessage") },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 90.dp)
                .size(120.dp)
                .border(3.dp, Color.Black, CircleShape),
            containerColor = Color(0xFFE53935),
            contentColor = Color.White,
            shape = CircleShape
        ) {
            Text("Compartir")
        }
    }
}
