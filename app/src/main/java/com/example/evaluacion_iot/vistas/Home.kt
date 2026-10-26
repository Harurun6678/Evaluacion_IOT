package com.example.evaluacion_iot.vistas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.evaluacion_iot.R

@Preview(showBackground = true)
@Composable
fun Home(navController: NavHostController = rememberNavController()) {
    // Varaibles
    val temperatura = 24.5
    val humedad = 58
    val hayPresencia = false

    // Botones
    var luzEncendida by remember { mutableStateOf(false) }
    var ventiladorEncendido by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sensores",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))

        Dato("Temperatura", "$temperatura °C")
        Image(
            painter = painterResource(R.drawable.grafico_temperatura),
            contentDescription = "Temperatura de las últimas 12 horas",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Dato("Humedad", "$humedad %")
        Image(
            painter = painterResource(R.drawable.grafico_humedad),
            contentDescription = "Humedad de las últimas 12 horas",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Interruptor("Luz", luzEncendida) { luzEncendida = it }
        Interruptor("Ventilador", ventiladorEncendido) { ventiladorEncendido = it }
        Dato("Movimiento", if (hayPresencia) "Presencia detectada" else "Sin presencia")

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { navController.popBackStack() }
        ) {
            Text("Volver")
        }
    }
}

// Una fila con el nombre a la izquierda y el valor a la derecha
@Composable
fun Dato(nombre: String, valor: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(nombre)
        Text(valor, fontWeight = FontWeight.Bold)
    }
}

// Una fila con el nombre a la izquierda y el interruptor (prender/apagar) a la derecha
@Composable
fun Interruptor(nombre: String, encendido: Boolean, alCambiar: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(nombre)
        Switch(checked = encendido, onCheckedChange = alCambiar)
    }
}
