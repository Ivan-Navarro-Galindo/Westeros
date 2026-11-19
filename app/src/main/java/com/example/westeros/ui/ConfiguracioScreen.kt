package com.example.westeros.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.westeros.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfiguracioScreen(viewModel: WesterosViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Configuració") },
                navigationIcon = {
                    IconButton(onClick = { viewModel.tancarConfiguracio() }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = "Tornar"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Tema de l'aplicació")

            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = viewModel.tema == "SISTEMA",
                    onClick = { viewModel.canviarTema("SISTEMA") }
                )
                Text("Seguir el sistema")
            }
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = viewModel.tema == "CLAR",
                    onClick = { viewModel.canviarTema("CLAR") }
                )
                Text("Clar")
            }
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = viewModel.tema == "FOSC",
                    onClick = { viewModel.canviarTema("FOSC") }
                )
                Text("Fosc")
            }
        }
    }
}
