package com.fandango.papayin.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_base.*
import com.fandango.papayin.R
import com.fandango.papayin.presentation.modules.list.view.MoviesFragment


class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.app_name)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, MoviesFragment.newInstance(), "movieList")
            .commit()
    }

}
