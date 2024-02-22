package com.bfc.storyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bfc.storyapp.databinding.ActivityMainBinding
import com.bfc.storyapp.databinding.ContentMainBinding
import com.google.android.material.navigation.NavigationView
import kotlin.random.Random

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding : ActivityMainBinding

    var storyTitles = arrayOf<String>()
    var storyContents = arrayOf<String>()
    var storyImages = arrayOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        setSupportActionBar(binding.contentMain.toolbar)
        val toogle = ActionBarDrawerToggle(this,binding.drawerLayout,binding.contentMain.toolbar,R.string.open,R.string.close)
        toogle.isDrawerIndicatorEnabled = true
        binding.drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        binding.navigationView.setNavigationItemSelectedListener(this)

        storyTitles = resources.getStringArray(R.array.storyTitles)
        storyContents = resources.getStringArray(R.array.storyContents)
        storyImages = resources.getStringArray(R.array.storyImages)
        val adapter = RecyclerViewAdapter(storyTitles,storyContents,storyImages)

        binding.contentMain.storyList.layoutManager = LinearLayoutManager(this)
        binding.contentMain.storyList.adapter = adapter

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        if (item.itemId == R.id.random) {
            val randomPosition = Random.nextInt(0,storyTitles.size)
            val intent = Intent(applicationContext,DetailsActivity::class.java)
            intent.putExtra("storyTitle",storyTitles[randomPosition])
            intent.putExtra("storyContent",storyContents[randomPosition])
            intent.putExtra("storyImage",storyImages[randomPosition])
            startActivity(intent)
        }
        return true
    }
}