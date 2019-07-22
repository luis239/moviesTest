package com.example.luis.test.movies

import android.os.Bundle
import android.view.*
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.luis.domain.movies.model.MovieModel
import com.example.luis.test.R
import com.example.luis.test.common.Resource
import com.example.luis.test.common.ViewModelFactory
import com.example.luis.test.dagger.Injectable
import com.example.luis.test.movies.viewmodel.MovieViewModel
import com.example.luis.test.extension.hideLoadingDialog
import com.example.luis.test.extension.showLoadingDialog
import com.example.luis.test.extension.showMessage
import kotlinx.android.synthetic.main.movies_fragment.*
import javax.inject.Inject



class WatchListActivity : AppCompatActivity(), MoviesAdapter.OnSelectedCallback,MoviesAdapter.FavoritecheckCallback,Injectable {
    private var movieList: MutableList<MovieModel>? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var movieViewModel: MovieViewModel
    lateinit var mAdapter:MoviesAdapter


    override fun onItemSelected(item: MovieModel) {

    }


    override fun checkSelected(item: MovieModel, view: View) {
        val v = view as CheckBox
        val i = movieList?.indexOf(item)
        movieList!![i!!].isFavorite = v.isChecked
        movieViewModel.updateFavorite(item.id!!,v.isChecked)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movies_fragment)

        movieViewModel = ViewModelProviders.of(this,viewModelFactory)
            .get(MovieViewModel::class.java)
            movieViewModel.getWatchList()
            movieViewModel.moviesLiveData.observe(this, Observer {
                when (it.status) {
                    Resource.ResourceState.NEXT -> {
                        hideLoadingDialog()
                        if (!it.data.isNullOrEmpty()) {
                            movieList = it.data as MutableList<MovieModel>?
                            mAdapter = MoviesAdapter(movieList!!)
                            recyclerProfile.apply {
                                adapter = mAdapter
                                setHasFixedSize(true)
                            }
                            mAdapter.setCallback(this)
                            mAdapter.setCallbackCheck(this)
                        }
                        //movieViewModel.saveTimestamp()
                    }
                    Resource.ResourceState.LOADING -> {
                        showLoadingDialog(message = "Cargando")
                    }
                    Resource.ResourceState.ERROR -> {
                        showMessage(it.message)
                    }
                    Resource.ResourceState.COMPLETED -> {
                        hideLoadingDialog()
                    }
                }
            })
    }

}
