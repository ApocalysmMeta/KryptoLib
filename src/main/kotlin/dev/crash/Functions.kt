package dev.crash

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset
import java.util.*
import javax.net.ssl.HttpsURLConnection
import kotlin.collections.HashMap


fun URL.get(headerParams: HashMap<String, String> = hashMapOf()): String {
    val con: HttpsURLConnection = this.openConnection() as HttpsURLConnection
    con.requestMethod = "GET"
    con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
    headerParams.forEach {
        con.setRequestProperty(it.key, it.value)
        println(it.key)
        println(it.value)
    }
    return con.inputStream.readBytes().toString(Charset.defaultCharset())
}

fun URL.post(params: HashMap<String, Any> = hashMapOf(), headerParams: HashMap<String, String> = hashMapOf(), json: Boolean = true): String {
    return post(dataToString(json, params), headerParams, json)
}

fun URL.post(requestString: String, headerParams: HashMap<String, String> = hashMapOf(), json: Boolean = true): String {
    val con: HttpURLConnection = this.openConnection() as HttpURLConnection
    con.requestMethod = "POST"
    con.doOutput = true
    con.setRequestProperty("Content-Type", if(json)"application/json" else "application/x-www-form-urlencoded")
    con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
    con.setRequestProperty("Content-Length", requestString.length.toString())
    headerParams.forEach {
        con.setRequestProperty(it.key, it.value)
    }
    con.useCaches = false
    DataOutputStream(con.outputStream).use { dos -> dos.writeBytes(requestString) }
    return BufferedReader(InputStreamReader(con.inputStream)).readText()
}

fun URL.put(params: HashMap<String, Any> = hashMapOf(), headerParams: HashMap<String, String> = hashMapOf(), json: Boolean = true): String {
    return put(dataToString(json, params), headerParams, json)
}

fun URL.put(requestString: String, headerParams: HashMap<String, String> = hashMapOf(), json: Boolean = true): String {
    val con: HttpURLConnection = this.openConnection() as HttpURLConnection
    con.requestMethod = "PUT"
    con.doOutput = true
    con.setRequestProperty("Content-Type", if(json)"application/json" else "application/x-www-form-urlencoded")
    con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
    con.setRequestProperty("Content-Length", requestString.length.toString())
    headerParams.forEach {
        con.setRequestProperty(it.key, it.value)
    }
    con.useCaches = false
    DataOutputStream(con.outputStream).use { dos -> dos.writeBytes(requestString) }
    return BufferedReader(InputStreamReader(con.inputStream)).readText()
}

fun HashMap<String, String>.asRequestString(): String {
    val builder = StringBuilder()
    this.forEach {
        builder.append(it.key).append("=").append(it.value).append("&")
    }
    return builder.toString().removeSuffix("&")
}

fun List<Any>.joinToNoSpaceString(): String = this.joinToString{it.toString()}.replace(" ", "")

fun ByteArray.toHexString() = asUByteArray().joinToString("") { it.toString(16).padStart(2, '0') }

internal fun String.asHexByteArray(): ByteArray {
    val len = this.length
    val data = ByteArray(len / 2)
    var i = 0
    while (i < len) {
        data[i / 2] = ((Character.digit(this[i], 16) shl 4)
                + Character.digit(this[i + 1], 16)).toByte()
        i += 2
    }
    return data
}

fun dataToString(json: Boolean, params: HashMap<String, Any>): String {
    return if(json){
        jacksonObjectMapper().writeValueAsString(params)
    }else {
        params.values.forEach {
            if(it !is String){
                throw InputMismatchException("Nothing else than Strings can be put in POST non-json request!")
            }
        }
        val newParams = hashMapOf<String, String>()
        params.forEach {
            newParams[it.key] = it.value.toString()
        }
        newParams.asRequestString()
    }
}