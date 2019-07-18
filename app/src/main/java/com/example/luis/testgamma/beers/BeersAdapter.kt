package com.example.luis.testgamma.beers

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.luis.domain.beer.model.MovieModel
import com.example.luis.testgamma.R
import com.example.luis.testgamma.extension.inflate
import kotlinx.android.synthetic.main.item_beer.view.*

class BeersAdapter (private val list: MutableList<MovieModel>): RecyclerView.Adapter<BeersAdapter.ProfileViewHolder>(){


    private var callback: OnSelectedCallback? = null


    fun setCallback(callback: OnSelectedCallback) {
        this.callback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder(parent.inflate(R.layout.item_beer))
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
               beerName.text = item.title
               taglineBeer.text = item.rating.toString()
               //descriptionBeer.text = item.description
                   Glide
                       .with(context)
                       .load("https://image.tmdb.org/t/p/w500"+item.imageUrl)
                       .centerCrop()
                       .placeholder(R.drawable.ic_baseline_face_24px)
                       .into(icon)
                   setOnClickListener { callback?.onItemSelected(item) }
           }
        }

    }


    interface OnSelectedCallback{
        fun onItemSelected(item: MovieModel)
    }

}







