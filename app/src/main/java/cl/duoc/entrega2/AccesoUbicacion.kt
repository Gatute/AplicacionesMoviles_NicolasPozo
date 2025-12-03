package cl.duoc.entrega2

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import kotlinx.coroutines.launch

@Composable
fun AccesoUbicacion(navController: NavController) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            navController.navigate("contacts")
        } else {
            scope.launch {
                snackbarHostState.showSnackbar("Permiso de ubicación denegado.")
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Explore,
                contentDescription = "Ubicación",
                modifier = Modifier.size(100.dp)
            )
            Spacer(Modifier.height(24.dp))
            Text("Danos acceso a la ubicación", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = {
                    val fineLocationPermission = Manifest.permission.ACCESS_FINE_LOCATION
                    val permissionStatus = ContextCompat.checkSelfPermission(context, fineLocationPermission)

                    if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
                        navController.navigate("contacts")
                    } else {
                        scope.launch {
                            val result = snackbarHostState.showSnackbar(
                                message = "Se necesita acceso a tu ubicación para continuar.",
                                actionLabel = "Permitir"
                            )
                            if (result == SnackbarResult.ActionPerformed) {
                                locationPermissionLauncher.launch(fineLocationPermission)
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Siguiente")
            }
        }
    }
}
