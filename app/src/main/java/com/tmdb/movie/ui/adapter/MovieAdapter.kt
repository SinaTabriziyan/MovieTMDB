package com.tmdb.movie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tmdb.movie.databinding.ItemMovieBinding
import com.tmdb.movie.model.ui.MovieItem

class MovieAdapter(
    private val onclick: (MovieItem) -> Unit,
    private val onLongClick: (MovieItem) -> Unit
) :
    ListAdapter<MovieItem, MovieAdapter.ViewHolder>(DiffCallback()) {
    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.apply {
                setOnClickListener { onclick(getItem(adapterPosition)) }
                setOnLongClickListener {
                    onLongClick(getItem(adapterPosition))
                    true
                }
            }
        }

        fun bind(item: MovieItem) {
            with(binding) {
                tvItemTitle.text = item.title
                Glide.with(binding.root).load(item.posterPath).into(itemMoviePoster)
            }
        }


    }

    class DiffCallback : DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))
}