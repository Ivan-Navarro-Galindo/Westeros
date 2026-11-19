package com.example.westeros.data

import com.example.westeros.model.Personatge
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import org.json.JSONArray

class ThronesApiService {

    private val client = HttpClient(Android) {
        expectSuccess = true
    }

    suspend fun getPersonatgeJson(): String = client.get("https://thronesapi.com/api/v2/Characters").bodyAsText()

    fun parsePersonatge(resposta: String): List<Personatge>{
        val array = JSONArray(resposta)
        val personatges = mutableListOf<Personatge>()

        for (index in 0 until array.length()) {
            val json = array.getJSONObject(index)
            personatges.add(
                Personatge(
                    id = json.getInt("id"),
                    fullName = json.getString("fullName"),
                    firstName = json.optString("firstName"),
                    lastName = json.optString("lastName"),
                    title = json.optString("title"),
                    family = json.optString("family"),
                    imageUrl = json.optString("imageUrl")
                )
            )
        }
        return personatges
    }
    suspend fun getPersonatges(): List<Personatge> = parsePersonatge(getPersonatgeJson())
}