package dev.crash

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset
import javax.net.ssl.HttpsURLConnection


internal fun URL.get(): String {
    val con: HttpsURLConnection = this.openConnection() as HttpsURLConnection
    con.requestMethod = "GET"
    con.setRequestProperty("Content-Type", "application/json")
    con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
    return con.inputStream.readBytes().toString(Charset.defaultCharset())
}

internal fun URL.post(json: Boolean = false, params: HashMap<String, String> = hashMapOf()): String {
    val postData = if(json){
        jacksonObjectMapper().writeValueAsString(params)
    }else {
        params.asRequestString()
    }
    val con: HttpURLConnection = this.openConnection() as HttpURLConnection
    con.requestMethod = "POST"
    con.doOutput = true
    con.setRequestProperty("Content-Type", if(json)"application/json" else "application/x-www-form-urlencoded")
    con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
    con.setRequestProperty("Content-Length", postData.length.toString())
    con.useCaches = false
    DataOutputStream(con.outputStream).use { dos -> dos.writeBytes(postData) }
    return BufferedReader(InputStreamReader(con.inputStream)).readText()
}

internal fun HashMap<String, String>.asRequestString(): String {
    val builder = StringBuilder()
    this.forEach {
        builder.append(it.key).append("=").append(it.value).append("&")
    }
    return builder.toString().removeSuffix("&")
}

internal fun List<Any>.joinToNoSpaceString(): String = this.joinToString{it.toString()}.replace(" ", "")