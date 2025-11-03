package cl.duoc.entrega2

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.Person

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.remember
import androidx.core.content.ContextCompat
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch

@Composable
fun ContactsAccessScreen(navController: NavController) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            navController.navigate("selectContact")
        } else {
            scope.launch {
                snackbarHostState.showSnackbar("Permiso denegado. No se puede acceder a los contactos.")
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
                imageVector = Icons.Default.Person,
                contentDescription = "Contactos",
                modifier = Modifier.size(100.dp)
            )
            Spacer(Modifier.height(24.dp))
            Text(
                "Permítenos acceso a los contactos para poder enlazar",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = {
                    when (ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.READ_CONTACTS
                    )) {
                        PackageManager.PERMISSION_GRANTED -> {
                            navController.navigate("selectContact")
                        }
                        else -> {
                            scope.launch {
                                val result = snackbarHostState.showSnackbar(
                                    message = "Necesitamos permiso para acceder a tus contactos.",
                                    actionLabel = "Permitir"
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    launcher.launch(Manifest.permission.READ_CONTACTS)
                                }
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
