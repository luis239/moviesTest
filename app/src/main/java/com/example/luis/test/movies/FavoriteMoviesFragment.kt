package com.example.luis.test.movies

import android.os.Bundle
import android.view.*
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
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
import java.util.*
import javax.inject.Inject



class FavoriteMoviesFragment : Fragment(), MoviesAdapter.OnSelectedCallback,MoviesAdapter.FavoritecheckCallback,Injectable {
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



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        movieViewModel = ViewModelProviders.of(activity!!,viewModelFactory)
            .get(MovieViewModel::class.java)

        movieViewModel.getFavoriteMovies()
        movieViewModel.favoriteListLiveData.observe(this, Observer {
            when (it.status) {
                Resource.ResourceState.NEXT -> {
                    activity?.hideLoadingDialog()
                    if (!it.data.isNullOrEmpty()) {
                        movieList = it.data as MutableList<MovieModel>?
                        mAdapter = MoviesAdapter(movieList!!)
/*                        mAdapter = MoviesAdapter(movieList!!.filter {
                            it.isFavorite
                        } as MutableList<MovieModel>)*/
                        recyclerProfile.apply {
                            adapter = mAdapter
                            setHasFixedSize(true)
                        }
                        mAdapter.setCallback(this)
                        mAdapter.setCallbackCheck(this)
                    }

                }
                Resource.ResourceState.LOADING -> {
                    activity?.showLoadingDialog(message = "Cargando")
                }
                Resource.ResourceState.ERROR -> {
                    activity?.showMessage(it.message)
                }
                Resource.ResourceState.COMPLETED -> {
                    activity?.hideLoadingDialog()
                }
            }
        })
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val touchHelper =
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0) {
                override fun onMove(
                    p0: RecyclerView,
                    p1: RecyclerView.ViewHolder,
                    p2: RecyclerView.ViewHolder
                ): Boolean {
                    val sourcePosition = p1.adapterPosition
                    val targetPosition = p2.adapterPosition
                    Collections.swap(movieList,sourcePosition,targetPosition)
                    mAdapter.notifyItemMoved(sourcePosition,targetPosition)
                    return true
                }

                override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {

                }

            })

        touchHelper.attachToRecyclerView(recyclerProfile)

    }

    companion object {
        const val TAG = "MoviesFragment"
        private const val EXTRA_FAVORITES = "isFavorites"
        fun newInstance(isFavorites:Boolean): Fragment {
            return FavoriteMoviesFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(EXTRA_FAVORITES, isFavorites)

                }
            }
        }
    }

}
