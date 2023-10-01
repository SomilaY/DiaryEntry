package com.example.diaryentry

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class DiaryEntryAdapter(private val context: Context, private val entries: List<DiaryEntry>) : BaseAdapter() {

    override fun getCount(): Int {
        return entries.size
    }

    override fun getItem(position: Int): Any {
        return entries[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_entry, parent, false)

        val titleTextView = view.findViewById<TextView>(R.id.titleTextView)
        val contentTextView = view.findViewById<TextView>(R.id.contentTextView)

        val entry = getItem(position) as DiaryEntry
        titleTextView.text = entry.title
        contentTextView.text = entry.content

        return view
    }
}
