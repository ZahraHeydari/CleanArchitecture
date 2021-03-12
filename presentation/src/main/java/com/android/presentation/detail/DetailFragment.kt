package com.android.presentation.detail

import android.os.Bundle
import android.view.View
import coil.load
import coil.request.CachePolicy
import coil.request.Disposable
import com.android.presentation.base.BaseFragment
import com.android.presentation.databinding.FragmentDetailBinding
import com.android.presentation.model.Photo
import org.koin.android.ext.android.inject

class DetailFragment : BaseFragment<DetailViewModel, FragmentDetailBinding>() {

    override val viewModel : DetailViewModel by inject()
    private var photo : Photo? = null
    private var imageLoader : Disposable ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.apply {
            if (containsKey(Photo::class.java.name))
                photo = getParcelable(Photo::class.java.name)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.showLoadingProgressBar()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding?.run{

            detailTitleTextView.text = photo?.title

            imageLoader = detailImageView.load(photo?.url) {
                diskCachePolicy(CachePolicy.ENABLED)
            }

            viewModel.hideProgressBarWithDelay()
        }

    }

    override fun onProgressBarVisibility(visibility: Boolean) {
        binding?.detailProgressBar?.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        imageLoader?.dispose() //To avoid memory leak
        super.onDestroyView()
    }

    companion object{
        val FRAGMENT_NAME : String = DetailFragment::class.java.simpleName
    }

}