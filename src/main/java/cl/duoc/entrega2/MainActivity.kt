package cl.duoc.entrega2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.*
import cl.duoc.entrega2.ui.theme.RightNowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RightNowTheme {
                val navController = rememberNavController()
                Scaffold { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "login",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("login") { LoginScreen(navController) }
                        composable("location") { AccesoUbicacion(navController) }
                        composable("contacts") { ContactsAccessScreen(navController) }
                        composable("selectContact") {ListaContactos(navController) }
                        composable("mapa") { PantallaPrincipal(navController) }
                        composable("editMessage") { EditarMensaje(navController) }
                    }
                }
            }
        }
    }
}
