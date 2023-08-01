package com.alicea.katalogfilm

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.alicea.katalogfilm.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter
    private var keyword: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { _, _, _ ->
                    searchBar.text = searchView.text
                    searchView.hide()
                    Toast.makeText(this@SearchActivity, searchView.text, Toast.LENGTH_SHORT).show()
                    keyword = searchView.text.toString()
                    initRecyclerView()

                    viewModel = ViewModelProvider(this@SearchActivity)[MovieViewModel::class.java]
                    viewModel.getSearchMovies(keyword)
                    viewModel.observeMovieLiveData().observe(this@SearchActivity) { movieList ->
                        movieAdapter.setMovieList(movieList)
                    }
                    false
                }
        }

    }
    private fun initRecyclerView() {
        movieAdapter = MovieAdapter()
        binding.rvSearch.apply {
            layoutManager = GridLayoutManager(applicationContext, 1)
            adapter = movieAdapter
        }
    }
}