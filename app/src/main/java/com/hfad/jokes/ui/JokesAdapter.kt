package com.hfad.jokes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hfad.jokes.databinding.ItemJokeBinding

class JokesAdapter(
    private val listJokes: MutableList<String>
) : RecyclerView.Adapter<JokesAdapter.JokesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemJokeBinding.inflate(inflater, parent, false)
        return JokesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        val item = listJokes[position]
        with(holder.binding) {
            joke.text = item
        }
    }

    override fun getItemCount(): Int = listJokes.size

    class JokesViewHolder(val binding: ItemJokeBinding) : RecyclerView.ViewHolder(binding.root)
}
