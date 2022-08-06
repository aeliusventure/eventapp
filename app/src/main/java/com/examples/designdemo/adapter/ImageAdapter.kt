package com.examples.designdemo.adapter

import android.content.res.ColorStateList
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.examples.designdemo.R
import com.examples.designdemo.databinding.RvImageLayoutBinding
import com.examples.designdemo.listener.OnClickListener

class ImageAdapter(
    val list: ArrayList<String>,
    private val onClickListener: OnClickListener<String>
) : RecyclerView.Adapter<ImageAdapter.Viewholder>() {


    inner class Viewholder(val binding: RvImageLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClickListener.onClick(list[adapterPosition])
            }
        }

        fun bindData(s: String) {

            binding.apply {
                if (s.isEmpty()) {

                    imageAdd.visibility = VISIBLE
                    image.visibility = GONE
                    tvProLabel.visibility = VISIBLE

                } else {
                    imageAdd.visibility = GONE
                    image.visibility = VISIBLE
                    tvProLabel.visibility = GONE
                    Glide.with(itemView.context)
                        .load(s)
                        .into(binding.image)
                }
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding = RvImageLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int = list.size
}