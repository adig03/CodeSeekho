package com.aditya1010.codeseekho

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerAdapter(context: MainActivity, val rvItems:List<RecyclerItem>)
    :RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>(){


       class MyViewHolder(val itemView: View):RecyclerView.ViewHolder(itemView){
            val itemTitle = itemView.findViewById<TextView>(R.id.cardTitleText)
           val itemdesc = itemView.findViewById<TextView>(R.id.cardDesc)
           val itemImage = itemView.findViewById<ImageView>(R.id.cardImageView)
       }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_recycler, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return rvItems.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = rvItems[position]
        holder.itemImage.setImageResource(item.topicImage)
        holder.itemdesc.text = item.topicDesc
        holder.itemTitle.text =item.topicName


    }

}