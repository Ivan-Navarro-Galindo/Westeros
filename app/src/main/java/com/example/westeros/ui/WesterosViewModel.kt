package com.example.westeros.ui

import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.westeros.data.ThronesApiService
import com.example.westeros.model.Personatge
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class WesterosViewModel : ViewModel() {
    private val api = ThronesApiService()

    var personatges by mutableStateOf(emptyList<Personatge>())
        private set
    var carregant by mutableStateOf(true)
        private set
    var missatgeError by mutableStateOf<String?>(null)
        private set

    var tema by mutableStateOf("SISTEMA")
        private set

    var configuracioOberta by mutableStateOf(false)
        private set

    var idDetall by mutableStateOf<Int?>(null)
        private set

    init {
        carregarPersonatges()
    }

    fun carregarPersonatges() {
        viewModelScope.launch {
            carregant = true
            missatgeError = null
            try {
                personatges = api.getPersonatges()
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                missatgeError = "No s'han pogut carregar els personatges"
            } finally {
                carregant = false
            }
        }
    }

    fun canviarTema(nouTema: String){
        tema = nouTema
    }

    fun obrirConfiguracio() {
        configuracioOberta = true
    }

    fun tancarConfiguracio() {
        configuracioOberta = false
    }

    fun obrirDetall(id: Int) {
        idDetall = id
    }

    fun tancarDetall() {
        idDetall = null
    }
}