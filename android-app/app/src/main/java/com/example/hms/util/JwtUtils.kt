package com.example.hms.util

import android.util.Base64
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

object JwtUtils {
    private val gson = Gson()

    fun extractRolesFromToken(token: String?): List<String> {
        if (token.isNullOrBlank()) return emptyList()
        val parts = token.split(".")
        if (parts.size < 2) return emptyList()
        return try {
            val payloadJson = String(Base64.decode(parts[1], Base64.URL_SAFE or Base64.NO_WRAP or Base64.NO_PADDING))
            val json = gson.fromJson(payloadJson, JsonObject::class.java)
            val rolesElement: JsonElement? = json.get("roles")
            when {
                rolesElement == null || rolesElement.isJsonNull -> emptyList()
                rolesElement.isJsonArray -> rolesElement.asJsonArray.mapNotNull { it.asString }
                rolesElement.isJsonPrimitive -> listOf(rolesElement.asString)
                else -> emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}

