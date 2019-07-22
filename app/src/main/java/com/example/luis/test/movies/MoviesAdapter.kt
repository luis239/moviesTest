package com.example.luis.test.movies

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.luis.domain.movies.model.MovieModel
import com.example.luis.test.R
import com.example.luis.test.extension.inflate
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter (private val list: MutableList<MovieModel>): RecyclerView.Adapter<MoviesAdapter.ProfileViewHolder>(){


    private var callback: OnSelectedCallback? = null
    private var callbackCheck: FavoritecheckCallback? = null


    fun setCallback(callback: OnSelectedCallback) {
        this.callback = callback
    }

    fun setCallbackCheck(callback: FavoritecheckCallback){
        callbackCheck = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder(parent.inflate(R.layout.item_movie))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bindItem(list[position])
    }


    inner class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun  bindItem(itemProfile : MovieModel) = with(itemView){
           itemProfile.let {item ->
               titleMovie.text = item.title
               rating.text = item.rating.toString()
               favoriteCheck.isChecked = item.isFavorite
               Glide
                   .with(context)
                   .load("https://image.tmdb.org/t/p/w500"+item.imageUrl)
                   .centerCrop()
                   .placeholder(R.drawable.ic_baseline_face_24px)
                   .into(icon)
               setOnClickListener { callback?.onItemSelected(item) }
               favoriteCheck.setOnClickListener { v ->
                   callbackCheck?.checkSelected(item,v)
               }

           }
        }

    }


    interface OnSelectedCallback{
        fun onItemSelected(item: MovieModel)
    }
    interface FavoritecheckCallback{
        fun checkSelected(item: MovieModel, view: View)
    }

}







