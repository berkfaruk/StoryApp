package com.bfc.storyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bfc.storyapp.databinding.ActivityDetailsBinding
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sTitle = intent.getStringExtra("storyTitle")
        val sContent = intent.getStringExtra("storyContent")
        val sImage = intent.getStringExtra("storyImage")

        supportActionBar?.title = sTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        Picasso.get().load(sImage).into(binding.storyDetailsImage)
        binding.storyDetails.text = sContent
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}