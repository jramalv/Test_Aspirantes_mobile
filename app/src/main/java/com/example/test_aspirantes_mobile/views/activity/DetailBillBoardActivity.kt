package com.example.test_aspirantes_mobile.views.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.test_aspirantes_mobile.databinding.ActivityDetailBillboardBinding
import com.example.test_aspirantes_mobile.model.models.Cast
import com.example.test_aspirantes_mobile.model.models.Media
import com.example.test_aspirantes_mobile.model.models.Movies
import com.example.test_aspirantes_mobile.model.models.Routes
import com.example.test_aspirantes_mobile.utils.Utils
import com.example.test_aspirantes_mobile.views.MyApplication

class DetailBillBoardActivity: AppCompatActivity() {

    private lateinit var binding:ActivityDetailBillboardBinding
    private lateinit var movie: Movies
    private lateinit var routes: ArrayList<Routes>
    private var stream=""

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
        Utils.getImageToDisplay(movie,routes!!, binding.actDetailBillboardIvBackgroundSypnosis,"background_synopsis",
            MyApplication.appContext!!)
        binding.actDetailBillboardTvName.text = movie.name
        binding.actvityDetailBillboardTvSynopsis.text =movie.synopsis
        binding.activityDetailBillboardTvDirectors.text = getDirectors(movie.cast!!)
        binding.activityDetailBillboardTvActors.text= getActors(movie.cast!!)
        binding.actDetailBillboardIvPlay.setOnClickListener{
            stream=getTrailer(movie.media!!,routes)
            if(stream.isNotEmpty()){
                goToPlayer()
            }
        }
    }

    private fun goToPlayer(){
        var intent = Intent(MyApplication.appContext!!,PlayerActivity::class.java)
        intent.putExtra("stream",stream)
        startActivity(intent)
    }

    private fun getTrailer(medias:ArrayList<Media>,routes: ArrayList<Routes>):String{
        var stream=""
        for(media in medias){
           if(media.code.contentEquals("trailer_mp4")
               && media.type.contentEquals("video")){
               for(route in routes){
                   if(route.code.contentEquals(media.code)){
                       stream= route.sizes!!.medium+""+media.resource
                   }
               }
           }
        }
        return stream
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