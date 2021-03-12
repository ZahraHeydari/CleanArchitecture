package com.android.presentation.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.android.presentation.databinding.HolderPhotoItemBinding
import com.android.presentation.model.Photo
import kotlin.properties.Delegates


class PhotosAdapter(val onPhotosListener: OnPhotosListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var photoList: List<Photo> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderPhotoBinding = HolderPhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(holderPhotoBinding)
    }

    override fun getItemCount(): Int = photoList.size

    private fun getItem(position: Int) = photoList[position]

    override fun getItemId(position: Int): Long = position.toLong()


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PhotoViewHolder).onBind(getItem(position))
    }

    private inner class PhotoViewHolder(private val holderPhotoItemBinding: HolderPhotoItemBinding) :
        RecyclerView.ViewHolder(holderPhotoItemBinding.root) {

        fun onBind(photo: Photo?) {

            with(holderPhotoItemBinding) {
                photoTitleTextView.text = photo?.title

                photoImageView.load(photo?.thumbnailUrl) {
                    diskCachePolicy(CachePolicy.ENABLED)
                }
            }

            itemView.setOnClickListener {
                photo?.let { it1 -> onPhotosListener.onItemClick(it1) }
            }
        }
    }
}