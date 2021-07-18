package com.example.marsphoto.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marsphoto.data.MarsPhoto
import com.example.marsphoto.databinding.GridViewItemBinding

class PhotoGridAdapter : ListAdapter<MarsPhoto, PhotoGridAdapter.MarsPhotoViewHolder>(DiffCallBack) {

    class MarsPhotoViewHolder(val gridViewItemBinding: GridViewItemBinding) :
        RecyclerView.ViewHolder(gridViewItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotoViewHolder {
        val layout = GridViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarsPhotoViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MarsPhotoViewHolder, position: Int) {
        holder.gridViewItemBinding.photo = getItem(position)
        holder.gridViewItemBinding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<MarsPhoto>() {
        override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }

    }
}

