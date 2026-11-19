package com.example.westeros.ui


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.westeros.R
import com.example.westeros.model.Personatge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetallScreen(
    personatge: Personatge?,
    onTornar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {Text(personatge?.fullName?: "Detall")},
                navigationIcon = {
                    IconButton(onClick = onTornar) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = "Tornar"
                        )
                    }
                }
            )
        }
    ) { innerPaddinga ->
        if(personatge == null) {
            Text("No s'ha trobat el personatge", Modifier.padding(innerPaddinga).padding(16.dp))
        } else {
            Column(
                Modifier
                    .padding(innerPaddinga)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AsyncImage(
                    model = personatge.imageUrl,
                    contentDescription = "Foto de ${personatge.fullName}",
                    placeholder = painterResource(R.drawable.ic_person),
                    error = painterResource(R.drawable.ic_error_outline),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().aspectRatio(0.8f).clip(RoundedCornerShape(16.dp))
                )
                Text(personatge.fullName, style = MaterialTheme.typography.headlineSmall)
                if(personatge.title.isNotBlank()) Text(personatge.title, style = MaterialTheme.typography.titleMedium)
                Text(personatge.family)
            }
        }
    }
}