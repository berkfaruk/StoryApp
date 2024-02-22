package com.bfc.storyapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(val storyTitles : Array<String>, val storyContents : Array<String>, val storyImages : Array<String> ) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardTitle : TextView = itemView.findViewById(R.id.cardTitle)
        val cardContent : TextView = itemView.findViewById(R.id.cardContent)
        val cardImage : ImageView = itemView.findViewById(R.id.cardImage)
        val view = itemView
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.cardTitle.text = storyTitles[position]
        holder.cardContent.text = storyContents[position]
        Picasso.get().load(storyImages[position]).into(holder.cardImage)

        holder.view.setOnClickListener {
            val intent = Intent(it.context,DetailsActivity::class.java)
            intent.putExtra("storyTitle",storyTitles[position])
            intent.putExtra("storyContent",storyContents[position])
            intent.putExtra("storyImage",storyImages[position])
            holder.view.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return storyTitles.size
    }
}