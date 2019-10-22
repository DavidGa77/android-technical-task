package com.example.minimoneybox

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_individual_accounts.*

class IndividualAccountsActivity : AppCompatActivity() {

    lateinit var header: TextView
    lateinit var addButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_accounts)
        setSupportActionBar(toolbar)

        setupViews()
    }

    private fun setupViews() {
        header = findViewById(R.id.indivHeader)
        addButton = findViewById(R.id.addButton)
    }

}
