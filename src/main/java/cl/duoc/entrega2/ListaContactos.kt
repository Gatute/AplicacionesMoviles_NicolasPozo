package cl.duoc.entrega2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class Contact(val name: String, val phone: String)

@Composable
fun ListaContactos(navController: NavController) {
    val contacts = listOf(
        Contact("Madre", "+56 9 9999 9999"),
        Contact("Abuelo", "+56 9 8888 8888"),
        Contact("Primo", "+56 9 7777 7777"),
        Contact("Hermana", "+56 9 6666 6666"),
        Contact("Amigo", "+56 9 5555 5555"),
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Elige el contacto con el que quieres compartir tu ubicación",
            style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(contacts) { contact ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { navController.navigate("mapa") }
                ) {
                    Column(Modifier.padding(12.dp)) {
                        Text(contact.name, style = MaterialTheme.typography.titleMedium)
                        Text(contact.phone, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}

