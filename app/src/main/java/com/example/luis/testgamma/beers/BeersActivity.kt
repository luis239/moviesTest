package com.example.luis.testgamma.beers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.luis.domain.beer.model.MovieModel
import com.example.luis.testgamma.R
import com.example.luis.testgamma.common.Resource
import com.example.luis.testgamma.common.ViewModelFactory
import com.example.luis.testgamma.dagger.Injectable
import com.example.luis.testgamma.beers.viewmodel.BeerViewModel
import com.example.luis.testgamma.extension.hideLoadingDialog
import com.example.luis.testgamma.extension.showLoadingDialog
import com.example.luis.testgamma.extension.showMessage
import kotlinx.android.synthetic.main.activity_beers.*
import java.util.*
import javax.inject.Inject



class BeersActivity : AppCompatActivity(), BeersAdapter.OnSelectedCallback,Injectable {

    private var beerList: MutableList<MovieModel>? = null
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var beerViewModel: BeerViewModel
    lateinit var mAdapter:BeersAdapter

    override fun onItemSelected(item: MovieModel) {
        /*val mEmail = Intent(Intent.ACTION_SEND)
        mEmail.putExtra(Intent.EXTRA_SUBJECT, item.title)
        mEmail.putExtra(Intent.EXTRA_TEXT, item.title +"\n"+item.tagline+"\n"+item.description)
        mEmail.type = "message/rfc822"
        startActivity(Intent.createChooser(mEmail, "Elije tu correo"))*/
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beers)

        beerViewModel = ViewModelProviders.of(this,viewModelFactory)
            .get(BeerViewModel::class.java)
        beerViewModel.beerListLiveData.observe(this, Observer {
            when (it.status){
                Resource.ResourceState.NEXT ->{
                    hideLoadingDialog()
                    if(!it.data.isNullOrEmpty()){
                        beerList = it.data as MutableList<MovieModel>?
                        mAdapter = BeersAdapter(beerList!!)
                        recyclerProfile.apply {
                            adapter = mAdapter
                            setHasFixedSize(true)
                        }
                        mAdapter.setCallback(this)
                    }
                    beerViewModel.saveTimestamp()
                }
                Resource.ResourceState.LOADING -> {
                    showLoadingDialog(message = "Cargando")}
                Resource.ResourceState.ERROR ->{ showMessage(it.message) }
                Resource.ResourceState.COMPLETED -> { hideLoadingDialog()}
            }
        })

        beerViewModel.date.observe(this, Observer {
            Toast.makeText(this,it,Toast.LENGTH_LONG).show()
        })

        val touchHelper =
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0) {
                override fun onMove(
                    p0: RecyclerView,
                    p1: RecyclerView.ViewHolder,
                    p2: RecyclerView.ViewHolder
                ): Boolean {
                    val sourcePosition = p1.adapterPosition
                    val targetPosition = p2.adapterPosition
                    Collections.swap(beerList,sourcePosition,targetPosition)
                    mAdapter.notifyItemMoved(sourcePosition,targetPosition)
                    return true
                }

                override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {

                }

            })

        touchHelper.attachToRecyclerView(recyclerProfile)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_sort_action,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == R.id.action_sort){
           /* beerList?.sortBy {
                it.ph
            }*/
            mAdapter.notifyDataSetChanged()

        }
        return super.onOptionsItemSelected(item)
    }

}
