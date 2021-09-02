package com.example.test_aspirantes_mobile.views.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test_aspirantes_mobile.R
import com.example.test_aspirantes_mobile.model.models.BillBoardResponse
import com.example.test_aspirantes_mobile.model.models.Movies
import com.example.test_aspirantes_mobile.model.models.Routes
import com.example.test_aspirantes_mobile.utils.Utils
import com.example.test_aspirantes_mobile.views.MyApplication
import com.example.test_aspirantes_mobile.views.activity.DetailBillBoardActivity
import com.google.android.material.imageview.ShapeableImageView

class BillBoardAdapter() : RecyclerView.Adapter<BillBoardAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(MyApplication.appContext!!)
    private lateinit var billBoardResponse: BillBoardResponse
    private lateinit var activity: Activity
    private lateinit var context: Context

    internal fun setBillboard(context: Context,activity: Activity,billBoardResponse: BillBoardResponse) {
        this.context =context
        this.activity = activity
        this.billBoardResponse = billBoardResponse
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemview = inflater.inflate(R.layout.item_billboard, parent, false)
        return ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var current = billBoardResponse.movies!![position]
        Utils.getImageToDisplay(current, billBoardResponse.routes,
            holder.mItemBillBoardIvBillboard,"poster",context)
        holder.mItemBillBoardTvClasification.text = current.rating
        holder.mItemBillBoardTvBillBoard.text = current.name
        holder.mItemBillBoardIvBillboard.setOnClickListener{
            goToBillBoardDetail(current,billBoardResponse.routes!!)
        }
    }


    private fun goToBillBoardDetail(current: Movies, routes: ArrayList<Routes>){
        var intent = Intent(activity,DetailBillBoardActivity::class.java)
        intent.putExtra("movies",current)
        intent.putExtra("routes",routes)
        activity.startActivity(intent)
    }




    override fun getItemCount(): Int = billBoardResponse.movies!!.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mItemBillBoardIvBillboard: ShapeableImageView =
            itemView.findViewById(R.id.item_billboard_iv_billboard)
        val mItemBillBoardTvClasification: TextView =
            itemView.findViewById(R.id.item_billboard_tv_clasification)
        val mItemBillBoardTvBillBoard: TextView =
            itemView.findViewById(R.id.item_billboard_tv_billboard)
    }
}