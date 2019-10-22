package com.example.minimoneybox

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import org.jetbrains.anko.doAsync
import org.json.JSONArray
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

        accButton1.setOnClickListener {
            openIndividualAccPage()
        }
        accButton2.setOnClickListener {
            openIndividualAccPage()
        }
        accButton3.setOnClickListener {
            openIndividualAccPage()
        }
    }

    fun run() = try {
        val url = URL(moneyBoxAPIString)
        val con = url.openConnection() as HttpsURLConnection
        con.requestMethod = "GET"
        con.setRequestProperty("Authorization", authorisationString)
        con.setRequestProperty("AppId", "3a97b932a9d449c981b595")
        con.setRequestProperty("Content-Type", "application/json")
        con.setRequestProperty("appVersion", "5.10.0")
        con.setRequestProperty("apiVersion", "3.0.0")
        val input = BufferedReader(InputStreamReader(con.inputStream))
        val response = StringBuilder()
        var line: String
        while ((input.readLine()) != null) {
            line = input.readLine()
            response.append(line)
        }
        //format result
        val jsonArray = JSONArray(response)
        for (i in 0..jsonArray.length()) {
            val item = jsonArray.getJSONObject(i)
            val accName = item.get("accountName") as String
            val value = item.get("value") as String
            val moneybox = item.get("moneybox") as String

            val buttons = arrayOf(accButton1, accButton2, accButton3)
            for (button in buttons) {
                button.text = "$accName\nPlan Value: $value\nMoneybox: $moneybox"
            }
        }

    } catch (ex: IOException) {
        println("Could not get requested URL")
    }

    private fun openIndividualAccPage() {
        val intent = Intent(this, IndividualAccountsActivity::class.java)
        startActivity(intent)
    }


}
