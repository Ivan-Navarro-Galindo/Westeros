package com.example.westeros.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.westeros.R
import com.example.westeros.model.Personatge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalegScreen(
    viewModel: WesterosViewModel,
    onPersonatgeClick: (Personatge) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Westeros") },
                colors = TopAppBarDefaults.topAppBarColors(
                  containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = { viewModel.obrirConfiguracio()}) {
                        Icon(
                            painter = painterResource((R.drawable.ic_settings)),
                            contentDescription = "Configuracio",
                            tint = Color.White
                            )
                    }
                }

            )
        },
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            Modifier.fillMaxSize().padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(viewModel.personatges, key = {it.id}) {personatge ->
                PersonatgeItem(
                    personatge = personatge,
                    onClick = {onPersonatgeClick(personatge)}
                )
            }
        }
    }
}