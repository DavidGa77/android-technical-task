package com.example.minimoneybox

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import org.jetbrains.anko.doAsync
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection


class UserAccountsActivity : AppCompatActivity() {

    lateinit var header: TextView
    lateinit var accButton1: Button
    lateinit var accButton2: Button
    lateinit var accButton3: Button
    private val moneyBoxAPIString = "https://api-test01.moneyboxapp.com/"
    private var authorisationString = "Bearer TsMWRkbrcu3NGrpf84gi2+pg0iOMVymyKklmkY0oI84="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_accounts)
        setupViews()
        doAsync {run()}

    }

    private fun setupViews() {
        header = findViewById(R.id.textView)
        accButton1 = findViewById(R.id.accButton1)
        accButton2 = findViewById(R.id.accButton2)
        accButton3 = findViewById(R.id.accButton3)
    }

    fun run() = try {
        val url = URL(moneyBoxAPIString)
        val con = url.openConnection() as HttpsURLConnection
        con.requestMethod = "GET"
        var headers: Map<String, List<String>> = mapOf("Authorization" to listOf(authorisationString),
            "AppId" to listOf("3a97b932a9d449c981b595"), "Content-Type" to listOf("application/json"),
            "appVersion" to listOf("5.10.0"), "apiVersion" to listOf("3.0.0"))
        print("Header fields: " + con.headerFields)
//        con.setRequestProperty("Authorization", authorisationString)
//        con.setRequestProperty("AppId", "3a97b932a9d449c981b595")
//        con.setRequestProperty("Content-Type", "application/json")
//        con.setRequestProperty("appVersion", "5.10.0")
//        con.setRequestProperty("apiVersion", "3.0.0")
        val input = BufferedReader(InputStreamReader(con.inputStream))
        val response = StringBuilder()
        var line: String
        while ((input.readLine()) != null) {
            line = input.readLine()
            response.append(line)
        }
        //print result
        println("Message here!")
        println(response.toString())

    } catch (ex: IOException) {
        println("Could not get requested URL")
    }


}
