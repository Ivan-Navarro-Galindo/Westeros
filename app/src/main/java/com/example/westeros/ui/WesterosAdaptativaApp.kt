package com.example.westeros.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration


    @Composable
    fun WesterosAdaptativaApp(viewModel: WesterosViewModel) {
        BackHandler(enabled = viewModel.idDetall != null) { viewModel.tancarDetall() }
        val personatge = viewModel.personatges.firstOrNull { it.id == viewModel.idDetall }
        val pantallaAmpla = LocalConfiguration.current.screenWidthDp >= 600

        if (!pantallaAmpla && personatge != null) {
            DetallScreen(
                personatge = personatge,
                onTornar = viewModel::tancarDetall
            )
        } else {
            Row(Modifier.fillMaxSize()) {
                CatalegScreen(
                    viewModel = viewModel,
                    onPersonatgeClick = { viewModel.obrirDetall(it.id) },
                    modifier = if (pantallaAmpla) Modifier.weight(1f) else Modifier.fillMaxSize()
                )

                if (pantallaAmpla) {
                    VerticalDivider()
                    if (personatge != null) {
                        DetallScreen(
                            personatge = personatge,
                            onTornar = viewModel::tancarDetall,
                            modifier = Modifier.weight(1.5f)
                        )
                    } else {
                        Card(Modifier
                            .weight(1.5f)
                            .fillMaxHeight()) {
                            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Text(
                                    "Tria un personatge del catàleg",
                                    style = MaterialTheme.typography.titleLarge
                                )
                            }
                        }
                    }
                }
            }
        }
    }