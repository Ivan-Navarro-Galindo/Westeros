package com.example.westeros.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WesterosApp(viewModel: WesterosViewModel = viewModel()) {

    val temaFosc = when (viewModel.tema) {
        "CLAR" -> false
        "FOSC" -> true
        else -> isSystemInDarkTheme()
    }

    //DEFINE POR DEFECTO EL ESTILO DE LA APP
    MaterialTheme(
        colorScheme = if (temaFosc) darkColorScheme() else lightColorScheme()
    ) {
        if (viewModel.configuracioOberta) {
            ConfiguracioScreen(viewModel = viewModel)
        } else {
            when {
                viewModel.carregant -> Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }

                viewModel.missatgeError != null -> Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(viewModel.missatgeError!!)
                    Button(onClick = { viewModel.carregarPersonatges() }) { Text("Tornar a provar") }
                }

                else -> WesterosAdaptativaApp(viewModel)
            }
        }
    }
}