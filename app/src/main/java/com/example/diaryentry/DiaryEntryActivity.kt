package com.example.diaryentry

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class DiaryEntryActivity : AppCompatActivity() {

    private lateinit var dbHelper: DiaryEntryDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary_entry)
        dbHelper = DiaryEntryDBHelper(this)
    }

    fun saveEntry(view: View) {
        val titleEditText = findViewById<EditText>(R.id.titleEditText)
        val contentEditText = findViewById<EditText>(R.id.contentEditText)

        val title = titleEditText.text.toString()
        val content = contentEditText.text.toString()

        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DiaryEntryDBHelper.COLUMN_TITLE, title)
            put(DiaryEntryDBHelper.COLUMN_CONTENT, content)
        }

        val newRowId = db.insert(DiaryEntryDBHelper.TABLE_ENTRIES, null, values)

        db.close()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}


