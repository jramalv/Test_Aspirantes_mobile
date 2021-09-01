package com.example.test_aspirantes_mobile.views.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.test_aspirantes_mobile.databinding.ActivityDetailBillboardBinding
import com.example.test_aspirantes_mobile.model.Cast
import com.example.test_aspirantes_mobile.model.Movies
import com.example.test_aspirantes_mobile.model.Routes
import com.example.test_aspirantes_mobile.utils.Utils

class DetailBillBoardActivity: AppCompatActivity() {

    private lateinit var binding:ActivityDetailBillboardBinding
    private lateinit var movie: Movies
    private lateinit var routes: ArrayList<Routes>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBillboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.getSerializableExtra("movies")!=null){
            movie = intent.getSerializableExtra("movies") as Movies
        }

        if(intent.getSerializableExtra("routes")!=null){
            routes = intent.getSerializableExtra("routes") as ArrayList<Routes>
        }

        bindView()
        binding.activityDetailBillboardIvBack.setOnClickListener{ finish() }
    }

    private fun bindView(){
        Utils.getImageToDisplay(movie,routes!!, binding.actDetailBillboardIvBackgroundSypnosis,"background_synopsis")
        binding.actDetailBillboardTvName.text = movie.name
        binding.actvityDetailBillboardTvSynopsis.text =movie.synopsis
        binding.activityDetailBillboardTvDirectors.text = getDirectors(movie.cast!!)
        binding.activityDetailBillboardTvActors.text= getActors(movie.cast!!)
    }

    private fun getDirectors(cast: ArrayList<Cast>):String{
        var directors=""
        for(cast in cast){
            if(cast.label.contentEquals("Directores")){
                directors+=cast.value+","
            }
        }
        return directors
    }

    private fun getActors(cast: ArrayList<Cast>):String{
        var actors=""
        for(cast in cast){
            if(cast.label.contentEquals("Directores")){
                actors+=cast.value
            }
        }
        return actors
    }


}