package com.android.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.presentation.R
import com.android.presentation.util.newFragmentInstance
import com.android.presentation.databinding.ActivityMainBinding
import com.android.presentation.detail.DetailFragment
import com.android.presentation.model.Photo
import com.android.presentation.photos.PhotosFragment

class MainActivity : AppCompatActivity(), OnMainActivityCallback {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigateToPhotosPage()
    }


    private fun navigateToPhotosPage() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.main_container,
                newFragmentInstance<PhotosFragment>(),
                PhotosFragment.FRAGMENT_NAME
            ).commitAllowingStateLoss()
    }

    override fun navigateToDetail(photo: Photo) {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.main_container,
                newFragmentInstance<DetailFragment>(Pair(Photo::class.java.name, photo)),
                DetailFragment.FRAGMENT_NAME
            )
            .addToBackStack(DetailFragment.FRAGMENT_NAME)
            .commitAllowingStateLoss()
    }
}