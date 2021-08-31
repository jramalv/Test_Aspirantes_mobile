package com.example.test_aspirantes_mobile.views.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test_aspirantes_mobile.R
import com.example.test_aspirantes_mobile.model.BillBoardResponse
import com.example.test_aspirantes_mobile.model.Media
import com.example.test_aspirantes_mobile.model.Movies
import com.example.test_aspirantes_mobile.model.Routes
import com.example.test_aspirantes_mobile.views.MyApplication
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import java.lang.Exception

class BillBoardAdapter() : RecyclerView.Adapter<BillBoardAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(MyApplication.appContext!!)
    private lateinit var billBoardResponse: BillBoardResponse
    private lateinit var activity: Activity

    internal fun setBillboard(billBoardResponse: BillBoardResponse) {
        this.billBoardResponse = billBoardResponse
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemview = inflater.inflate(R.layout.item_billboard, parent, false)
        return ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var current = billBoardResponse.movies!![position]
        obtainCurrentPosters(current, billBoardResponse.routes, holder)
        holder.mItemBillBoardTvBillBoard.text = current.name
    }

    private fun obtainCurrentPosters(
        current: Movies, routes: ArrayList<Routes>?,
        holder: ViewHolder
    ) {
        try {
            for (media in current.media!!) {
                if (!media.code.isNullOrEmpty() &&
                    media.code.lowercase().contentEquals("poster")
                ) {
                    holder.mItemBillBoardIvBillboard.clipToOutline = true
                    Picasso.get().load(getPoster(media, routes))
                        .into(holder.mItemBillBoardIvBillboard)
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun getPoster(media: Media, routes: ArrayList<Routes>?): String {
        var posterRoute = ""
        for (route in routes!!) {
            if (route.code.lowercase().contentEquals(media.code)) {
                if (!route.sizes!!.large.isNullOrEmpty() &&
                    !media.resource.isNullOrEmpty()
                ) {
                    posterRoute = route.sizes!!.large + media.resource
                }
            }
        }
        return posterRoute
    }

    override fun getItemCount(): Int = billBoardResponse.movies!!.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mItemBillBoardIvBillboard: ShapeableImageView =
            itemView.findViewById(R.id.item_billboard_iv_billboard)
        val mItemBillBoardTvBillBoard: TextView = itemView.findViewById(R.id.item_billboard_tv_billboard)
    }
}