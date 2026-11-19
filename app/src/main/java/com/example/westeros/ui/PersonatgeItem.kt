package com.example.westeros.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.westeros.R
import com.example.westeros.model.Personatge


@Composable
    fun PersonatgeItem(
        personatge: Personatge,
        onClick: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        ElevatedCard(
            modifier = modifier.fillMaxWidth().clickable(onClick = onClick),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = personatge.imageUrl,
                    contentDescription = "Foto de ${personatge.fullName}",
                    placeholder = painterResource(R.drawable.ic_person),
                    error = painterResource(R.drawable.ic_error_outline),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(80.dp).clip(CircleShape)
                )
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(personatge.fullName, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    if(personatge.title.isNotBlank()) Text(personatge.title, style = MaterialTheme.typography.bodyMedium)
                    Text(personatge.family, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }

            }
        }
    }
