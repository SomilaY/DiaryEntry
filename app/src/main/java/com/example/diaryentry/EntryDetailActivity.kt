package com.example.diaryentry

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.TextView

class EntryDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry_detail)

        val titleTextView = findViewById<TextView>(R.id.textViewEntryTitle)
        val contentTextView = findViewById<TextView>(R.id.textViewEntryContent)

        val intent = intent
        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")

        titleTextView.text = title
        contentTextView.text = content
    }
}
