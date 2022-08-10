package com.example.juda

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView


class ListViewAdapter (private val items: MutableList<ListViewItem>): BaseAdapter(){
    override fun getCount(): Int = items.size

    override fun getItem(position: Int): ListViewItem = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertview: View?, parent: ViewGroup): View {
        var view = convertview
        val context = parent.context

        if (view == null) {
            val vi = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = vi.inflate(R.layout.activity_post_list, parent, false)
        }

        val textTitle = view?.findViewById(R.id.text_title) as TextView
        textTitle.text = items[position].name
        val textName = view?.findViewById(R.id.text_name) as TextView
        textName.text = items[position].name

        return view
    }
}