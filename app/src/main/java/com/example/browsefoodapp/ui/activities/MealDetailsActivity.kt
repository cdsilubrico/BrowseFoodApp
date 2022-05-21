package com.example.browsefoodapp.ui.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.browsefoodapp.databinding.ActivityMealDetailsBinding

class MealDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealDetailsBinding
    private var name: String? = null
    private var area: String? = null
    private var thumb: String? = null
    private var category: String? = null
    private var instruction: String? = null
    private var link: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentData()
        onYoutubeClick()
        updateMealImage()
        loadTextViewValues()

    }

    private fun updateMealImage()
    {
        Glide.with(this)
            .load(thumb)
            .into(binding.ivMeal)
    }

    private fun loadTextViewValues()
    {
        binding.tvMealName.text = name
        binding.tvCategory.text = category
        binding.tvArea.text = area
        binding.tvInstructions.text = instruction
    }

    private fun getIntentData()
    {
        name = intent.getStringExtra("MEAL_NAME").toString()
        area = intent.getStringExtra("MEAL_AREA").toString()
        thumb = intent.getStringExtra("MEAL_THUMB").toString()
        category = intent.getStringExtra("MEAL_CATEGORY").toString()
        instruction = intent.getStringExtra("MEAL_INSTRUCTIONS").toString()
        link = intent.getStringExtra("YOUTUBE_LINK").toString()
    }

    private fun onYoutubeClick()
    {
        binding.ivYtLink.setOnClickListener{
            val yt = Intent(Intent.ACTION_VIEW,  Uri.parse(link))
            startActivity(yt)
        }
    }

}