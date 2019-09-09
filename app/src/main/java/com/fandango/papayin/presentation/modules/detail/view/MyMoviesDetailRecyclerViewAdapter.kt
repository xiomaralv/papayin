package com.fandango.papayin.presentation.modules.detail.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.fandango.papayin.Constants
import com.fandango.papayin.R
import com.fandango.papayin.data.model.ProductionCompanies
import kotlinx.android.synthetic.main.item_production.view.*

class MyMoviesDetailRecyclerViewAdapter(
    private val productions: MutableList<ProductionCompanies>
) : RecyclerView.Adapter<MyMoviesDetailRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as ProductionCompanies
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_production, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = productions[position]
        holder.productionName.text = item.name
        if (item.logoPath != null) {
            Glide.with(holder.mView)
                .load(Constants.BASE_URL_IMAGE + item.logoPath)
                .into(holder.productionImg)
            with(holder.mView) {
                tag = item
                setOnClickListener(mOnClickListener)
            }
        }
        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = productions.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val productionName: TextView = mView.productionName
        val productionImg: ImageView = mView.productionImg
    }
}