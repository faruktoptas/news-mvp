package me.toptas.rssreader.features.rss

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import me.toptas.rssconverter.RssItem
import me.toptas.rssreader.R
import java.util.*

class RssItemsAdapter(private val mContext: Context,
                      private val listener: OnItemClickListener) : RecyclerView.Adapter<RssItemsAdapter.ViewHolder>() {


    private val items = ArrayList<RssItem>()


    fun setItems(rssItems: List<RssItem>) {
        items.clear()
        items.addAll(rssItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RssItemsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_rss_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: RssItemsAdapter.ViewHolder, position: Int) {
        if (items.size <= position) {
            return
        }
        val item = items[position]
        holder.textTitle.text = item.title
        holder.textPubDate.text = item.publishDate

        if (item.image?.isNotEmpty() == true) {
            Picasso.get()
                    .load(item.image).fit()
                    .centerCrop()
                    .into(holder.imageThumb)
        }

        holder.itemView.setOnClickListener { listener.onItemSelected(item) }

    }


    override fun getItemCount(): Int {
        return items.size
    }


    interface OnItemClickListener {
        fun onItemSelected(rssItem: RssItem)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textTitle = itemView.findViewById<TextView>(R.id.tvTitle)!!

        var textPubDate = itemView.findViewById<TextView>(R.id.tvPubDate)!!

        var imageThumb = itemView.findViewById<ImageView>(R.id.ivThumb)!!

    }
}