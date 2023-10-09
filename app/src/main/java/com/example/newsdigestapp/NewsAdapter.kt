package com.example.newsdigestapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.net.URL

class NewsAdapter( private val listener: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {

    private val items: ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.news_rvitem,parent,false)
        val viewHolder=NewsViewHolder(view)
        view.setOnClickListener{
            listener.Onitemclicked(items[viewHolder.adapterPosition])

        }
       return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem=items[position]
         holder.newheading.text=currentItem.title
         holder.news.text=currentItem.cnt
         Glide.with(holder.itemView.context)
             .load(currentItem.imageUrl)
             .into(holder.newsImage)

    }
    fun updateNews(updatedNews: ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

}
class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val newsImage: ImageView= itemView.findViewById(R.id.imRv2)
    val newheading: TextView=itemView.findViewById(R.id.News_Heading)
    val news: TextView=itemView.findViewById(R.id.news_sub_heading)
//    val bookmark: ImageButton=itemView.findViewById(R.id.bookmarksid)

}
interface NewsItemClicked{
    fun Onitemclicked(item: News)
}