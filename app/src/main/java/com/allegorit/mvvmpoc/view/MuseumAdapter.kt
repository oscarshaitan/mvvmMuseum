package com.allegorit.mvvmpoc.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.allegorit.mvvmpoc.data.model.Museum
import com.allegorit.mvvmpoc.databinding.RowMuseumBinding

class MuseumAdapter(private var musems: List<Museum>) : RecyclerView.Adapter<MuseumViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MuseumViewHolder {
        val itemBinding =
            RowMuseumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MuseumViewHolder(itemBinding)
    }

    override fun getItemCount() = musems.size

    override fun onBindViewHolder(holder: MuseumViewHolder, position: Int) {
        holder.bind(musems[position])
    }

    fun update(data: List<Museum>) {
        musems = data
        notifyDataSetChanged()
    }

}

class MuseumViewHolder(itemBinding: RowMuseumBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    private val textViewName: TextView = itemBinding.textViewName
    private val imageView: ImageView = itemBinding.imageView

    fun bind(museum: Museum) {
        textViewName.text = museum.name
        imageView.loadUrl(museum.photo)
    }
}
