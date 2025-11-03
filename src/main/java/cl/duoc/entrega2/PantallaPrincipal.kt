package cl.duoc.entrega2

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*


@Composable
fun PantallaPrincipal(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(LatLng(-33.4489, -70.6693), 12f)
            }

            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                uiSettings = MapUiSettings(zoomControlsEnabled = true)
            ) {
                Marker(
                    state = MarkerState(position = LatLng(-33.4489, -70.6693)),
                    title = "Tu ubicación",
                    snippet = "Aquí estás tú"
                )
            }

        }


        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(0xFFDADADA))
                .border(
                    width = 2.dp,
                    color = Color.Black,
                )
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { navController.navigate("editMessage") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDADADA)),
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp),
                shape = RectangleShape
            ) {
                Icon(Icons.Default.Email, contentDescription = "Mensaje")
                Spacer(Modifier.width(8.dp))
                Text("Enviar mensaje")
            }

            Spacer(Modifier.width(40.dp))

            Button(
                onClick = { /* Navegar a pantalla de notificaciones */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDADADA)),
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp),
                shape = RectangleShape
            ) {
                Icon(Icons.Default.Notifications, contentDescription = "Notificaciones")
                Spacer(Modifier.width(8.dp))
                Text("Ajustar notificaciones")
            }

        }
        FloatingActionButton(
            onClick = { navController.navigate("editMessage") },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 90.dp)
                .size(120.dp)
                .border(
                    width = 3.dp,
                    color = Color.Black,
                    shape = CircleShape
                ),
            containerColor = Color(0xFFE53935),
            contentColor = Color.White,
            shape = CircleShape
        ) {
            Text("Compartir")
        }
    }
}
