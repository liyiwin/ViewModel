package com.example.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this
    ).get(ViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.setValue()

        val title_Observe = Observer<Data>{

          my_title.text = it.title

          my_intro.text = it.introduce

          my_author.text = it.author

          Glide.with(this@MainActivity).load(it.imageurl).into(imageView)


        }


        viewModel.getValue().observe(this,title_Observe)





    }
}
