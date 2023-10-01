package com.example.diaryentry

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DiaryEntryDBHelper
    private lateinit var listViewEntries: ListView
    private lateinit var AddButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHelper = DiaryEntryDBHelper(this)
        listViewEntries = findViewById(R.id.listViewEntries)
        loadDiaryEntries()

        AddButton = findViewById(R.id.btnAddEntry)
        AddButton.setOnClickListener {
            val intent = Intent(this, DiaryEntryActivity::class.java)
            startActivity(intent)
        }

        listViewEntries.setOnItemClickListener { _, _, position, _ ->
            val entry = listViewEntries.getItemAtPosition(position) as DiaryEntry
            val intent = Intent(this, EntryDetailActivity::class.java).apply {
                putExtra("title", entry.title)
                putExtra("content", entry.content)
            }
            startActivity(intent)
        }
    }



    private fun loadDiaryEntries() {
        val db = dbHelper.readableDatabase
        val entries = mutableListOf<DiaryEntry>()

        val cursor = db.rawQuery(
            "SELECT ${DiaryEntryDBHelper.COLUMN_ID} AS id, " +
                    "${DiaryEntryDBHelper.COLUMN_TITLE} AS title, " +
                    "${DiaryEntryDBHelper.COLUMN_CONTENT} AS content " +
                    "FROM ${DiaryEntryDBHelper.TABLE_ENTRIES}", null
        )

        while (cursor.moveToNext()) {
            val idIndex = cursor.getColumnIndex("id")
            val titleIndex = cursor.getColumnIndex("title")
            val contentIndex = cursor.getColumnIndex("content")

            val id = cursor.getLong(idIndex)
            val title = cursor.getString(titleIndex)
            val content = cursor.getString(contentIndex)


            Log.d("CursorDebug", "id: $id, title: $title, content: $content")

            entries.add(DiaryEntry(id, title, content))
        }


        cursor.close()
        db.close()

        val adapter = DiaryEntryAdapter(this, entries)
        listViewEntries.adapter = adapter
    }






}
