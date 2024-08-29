package com.aditya1010.codeseekho

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class MyGridAdapter(context:Context , val GridItems: List<GridItem>): BaseAdapter() {
    val inflater:LayoutInflater = LayoutInflater.from(context)
    override fun getCount(): Int {
        return GridItems.size
    }

    override fun getItem(p0: Int): Any {
        return GridItems[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val itemView : View = p1 ?: inflater.inflate(R.layout.grid_wol_item ,p2 , false)

        val item = getItem(p0) as GridItem

        val gridText: TextView = itemView.findViewById<TextView>(R.id.grid_text)
        val gridImage: ImageView = itemView.findViewById<ImageView>(R.id.grid_image)

        gridText.text = item.gridtext
        gridImage.setImageResource(item.gridImage)

        return itemView

    }

}
