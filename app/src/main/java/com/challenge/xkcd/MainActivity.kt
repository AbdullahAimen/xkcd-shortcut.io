package com.challenge.xkcd

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.test.espresso.IdlingResource
import com.challenge.xkcd.databinding.ActivityMainBinding
import com.challenge.xkcd.domainLayer.GetLocalComicsUseCase
import com.challenge.xkcd.presentation.ComicPresenter
import com.challenge.xkcd.util.ComicCommand
import com.challenge.xkcd.util.EspressoIdlingResource
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var mComicPresenter: ComicPresenter

    @Inject
    lateinit var mGetLocalComicsUseCase: GetLocalComicsUseCase

    val countingIdlingResource: IdlingResource
        @VisibleForTesting
        get() = EspressoIdlingResource.idlingResource
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mComicPresenter.mComicCommand.observe(this, Observer {
            it.getContentIfNotHandled().let {
                when (it) {
                    is ComicCommand.disableFirst -> {
                        enableDisableBack(false)
                        binding.progressLoading.visibility = View.GONE
                    }
                    is ComicCommand.enableFirst -> {
                        enableDisableBack(true)
                    }
                    is ComicCommand.enableNext -> {
                        enableDisableNext(true)
                    }
                    is ComicCommand.disableNext -> {
                        enableDisableNext(false)
                        binding.progressLoading.visibility = View.GONE
                    }
                    is ComicCommand.hideLoading -> {
                    }
                    is ComicCommand.showLoading -> {
                        binding.progressLoading.visibility = View.VISIBLE
                    }
                    is ComicCommand.OnErrorLoading -> {
                        binding.progressLoading.visibility = View.GONE
                        Toast.makeText(this, it.error.message, Toast.LENGTH_LONG).show()
                    }
                    is ComicCommand.AssignComicResult -> {
                        if (it.mComic.num == 1) {
                            enableDisableBack(false)
                            enableDisableNext(true)
                        } else if (it.mComic.num == 2516) {
                            enableDisableBack(true)
                            enableDisableNext(false)
                        } else {
                            enableDisableBack(true)
                            enableDisableNext(true)
                        }
                        binding.titleTv.text = it.mComic.safe_title
                        binding.transcriptTv.text = it.mComic.getTransScript()
                        binding.progressLoading.visibility = View.GONE
                        Picasso.get()
                            .load(it.mComic.img)
                            .into(binding.comicImg)
                        if (it.mComic.isFavorite) {
                            binding.favoriteImg.setImageResource(R.drawable.baseline_favorite)
                        } else
                            binding.favoriteImg.setImageResource(R.drawable.baseline_favorite_border)
                    }
                    is ComicCommand.assignFavorite -> {
                        binding.favoriteImg.setImageResource(R.drawable.baseline_favorite)
                    }
                    is ComicCommand.removeFavorite -> {
                        binding.favoriteImg.setImageResource(R.drawable.baseline_favorite_border)
                    }

                }
            }
        })
        binding.loadFirstImb.setOnClickListener {
            mComicPresenter.loadFirstComic()
        }
        binding.loadPreviousImb.setOnClickListener {
            mComicPresenter.loadPreviousComic()
        }
        binding.loadLastImb.setOnClickListener {
            mComicPresenter.loadLastComic()
        }
        binding.loadNextImb.setOnClickListener {
            mComicPresenter.loadNextComic()
        }
        binding.favoriteImg.setOnClickListener {
            mComicPresenter.changeFavorite()
        }
        mComicPresenter.loadFirstComic()
    }

    fun enableDisableBack(state: Boolean) {
        binding.loadPreviousImb.isSelected = state
        binding.loadPreviousImb.isEnabled = state
        binding.loadPreviousImb.isClickable = state

        binding.loadFirstImb.isSelected = state
        binding.loadFirstImb.isEnabled = state
        binding.loadFirstImb.isClickable = state
    }

    fun enableDisableNext(state: Boolean) {
        binding.loadNextImb.isSelected = state
        binding.loadNextImb.isEnabled = state
        binding.loadNextImb.isClickable = state

        binding.loadLastImb.isSelected = state
        binding.loadLastImb.isEnabled = state
        binding.loadLastImb.isClickable = state
    }
}