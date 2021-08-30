package com.example.movieapp.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.BR
import com.example.movieapp.data.Movie
import com.example.movieapp.databinding.LayoutRowBinding

class MovieAdapter() : PagingDataAdapter<Movie,MovieAdapter.MyViewHolder>(DIFF_UTIL){

    companion object{
        val DIFF_UTIL =object :DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.imdbID==newItem.imdbID
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem==newItem
            }

        }
    }

    inner class MyViewHolder(val viewBinding:LayoutRowBinding):RecyclerView.ViewHolder(viewBinding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewBinding.setVariable(BR.movie,getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding= LayoutRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }
}