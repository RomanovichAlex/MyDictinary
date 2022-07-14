package by.romanovich.mydictinary.ui.details

import android.content.Context
import android.content.Intent
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import by.romanovich.mydictinary.R
import by.romanovich.mydictinary.databinding.ActivityDescriptionBinding
import by.romanovich.utils.network.OnlineLiveData
import coil.ImageLoader
import coil.request.LoadRequest

class DescriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDescriptionBinding


    @RequiresApi(31)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setActionbarHomeButtonAsUp()

        binding.descriptionScreenSwipeRefreshLayout.setOnRefreshListener {
            startLoadingOrShowError()
        }
        setData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setActionbarHomeButtonAsUp() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    @RequiresApi(31)
    private fun setData() {
        val bundle = intent.extras
        binding.descriptionHeader.text = bundle?.getString(WORD_EXTRA)
        binding.descriptionTextview.text = bundle?.getString(DESCRIPTION_EXTRA)
        val imageLink = bundle?.getString(URL_EXTRA)
        if (imageLink.isNullOrBlank()) {
            stopRefreshAnimationIfNeeded()
        } else {
            useCoilToLoadPhoto(binding.descriptionImageview, imageLink)
        }
    }

    @RequiresApi(31)
    private fun startLoadingOrShowError() {
        OnlineLiveData(this).observe(
            this@DescriptionActivity,
            Observer<Boolean> {
                if (it) {
                    setData()
                } else {
                    stopRefreshAnimationIfNeeded()
                }
            })
    }


    private fun stopRefreshAnimationIfNeeded() {
        if (binding.descriptionScreenSwipeRefreshLayout.isRefreshing) {
            binding.descriptionScreenSwipeRefreshLayout.isRefreshing = false
        }
    }

    @RequiresApi(31)
    private fun useCoilToLoadPhoto(imageView: ImageView, imageLink: String) {
        val request = LoadRequest.Builder(this)
            .data("https:$imageLink")
            .target(
                onStart = {},
                onSuccess = { result ->
                    imageView.setImageDrawable(result)
                    val blurEffect = RenderEffect.createBlurEffect(15f, 0f,
                        Shader.TileMode.MIRROR)
                    imageView.setRenderEffect(blurEffect)
//binding.root.setRenderEffect(blurEffect)
                },
                onError = {
                    imageView.setImageResource(R.drawable.ic_load_error_vector)
                }
            )
            .build()
        ImageLoader(this).execute(request)
    }


   /* @RequiresApi(31)
    private fun usePicassoToLoadPhoto(imageView: ImageView, imageLink: String) {
        Picasso.get().load("https:$imageLink")
            .placeholder(R.drawable.ic_no_photo_vector).fit().centerCrop()
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    stopRefreshAnimationIfNeeded()
                }

                override fun onError(e: Exception?) {
                    stopRefreshAnimationIfNeeded()
                    imageView.setImageResource(R.drawable.ic_load_error_vector)
                }
            })
    }*/


    companion object {

        private const val WORD_EXTRA = "f76a288a-5dcc-43f1-ba89-7fe1d53f63b0"
        private const val DESCRIPTION_EXTRA = "0eeb92aa-520b-4fd1-bb4b-027fbf963d9a"
        private const val URL_EXTRA = "6e4b154d-e01f-4953-a404-639fb3bf7281"

        fun getIntent(
            context: Context,
            word: String,
            description: String,
            url: String?
        ): Intent = Intent(context, DescriptionActivity::class.java).apply {
            putExtra(WORD_EXTRA, word)
            putExtra(DESCRIPTION_EXTRA, description)
            putExtra(URL_EXTRA, url)
        }
    }
}
