package com.android.presentation.photos

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.android.presentation.R
import com.android.presentation.base.BaseFragment
import com.android.presentation.databinding.FragmentPhotosBinding
import com.android.presentation.main.OnMainActivityCallback
import com.android.presentation.model.Photo
import com.android.presentation.util.NetworkState
import com.android.presentation.util.snackBar
import org.koin.android.ext.android.inject


class PhotosFragment : BaseFragment<PhotosViewModel, FragmentPhotosBinding>(), OnPhotosListener {


    override val viewModel: PhotosViewModel by inject()
    private var adapter: PhotosAdapter? = null
    private var callback: OnMainActivityCallback? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnMainActivityCallback) callback = context
        else throw ClassCastException("$context must implement OnMainActivityCallback!")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.run {

            adapter = PhotosAdapter(this@PhotosFragment)
            if (adapter?.hasObservers() == false) adapter?.setHasStableIds(true)
            photosRecyclerView.adapter = adapter
        }

        with(viewModel) {

            photosData.observe(viewLifecycleOwner, Observer {
                adapter?.photoList = it
            })
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fetchData() //Fetch a list of photo from remote or local
    }


    private fun fetchData() {
        val isNetworkAvailable = context?.let { NetworkState.isAvailable(it) } ?: false
        viewModel.getPhotos(isNetworkAvailable)
        if (!isNetworkAvailable) {
            viewModel.hideLoadingProgressBar()
            showConnectionErrorMessage()
        }
    }

    private fun showConnectionErrorMessage() {
        binding?.root?.snackBar(
            getString(R.string.no_internet_connection),
            getString(R.string.retry),
            ::tryAgain
        )
    }

    private fun tryAgain() {
        val isNetworkAvailable = context?.let { NetworkState.isAvailable(it) }
        if (isNetworkAvailable == true) viewModel.getPhotos(true)
        else {
            viewModel.hideLoadingProgressBar()
            showConnectionErrorMessage()
        }
    }

    override fun onProgressBarVisibility(visibility: Boolean) {
        binding?.photosProgressBar?.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    override fun onItemClick(photo: Photo) {
        callback?.navigateToDetail(photo)
    }

    override fun onDestroyView() {
        adapter = null //To avoid memory leak
        super.onDestroyView()
    }

    override fun onDetach() {
        callback = null //To avoid memory leak
        super.onDetach()
    }


    companion object {
        val FRAGMENT_NAME: String = PhotosFragment::class.java.simpleName
    }

}