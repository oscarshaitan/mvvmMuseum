package com.allegorit.mvvmpoc.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.allegorit.mvvmpoc.data.model.Museum
import com.allegorit.mvvmpoc.databinding.ActivityMainBinding
import com.allegorit.mvvmpoc.viewmodel.MuseumViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MuseumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var museumAdapter: MuseumAdapter
    private val viewModel: MuseumViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupViewModel()

    }

    private fun setupViewModel() {
        viewModel.run {
            museum.observe(this@MuseumActivity, renderMuseumObserver)
            isViewLoading.observe(this@MuseumActivity, isViewLoadingObserver)
            onMessageError.observe(this@MuseumActivity, onMessageErrorObserver)
            isEmptyList.observe(this@MuseumActivity, emptyListObserver)
        }
    }

    private fun setupUI() {
        museumAdapter = MuseumAdapter(viewModel.museum.value ?: emptyList())
        binding.recyclerView.run {
            layoutManager = LinearLayoutManager(this@MuseumActivity)
            adapter = museumAdapter
        }
    }

    private val renderMuseumObserver = Observer<List<Museum>> {
        museumAdapter.update(it)
        binding.run {
            layoutEmpty.root.visibility = View.GONE
            layoutError.root.visibility = View.GONE
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
    }

    private val onMessageErrorObserver = Observer<Any> { it ->
        binding.run {
            layoutError.root.visibility = View.VISIBLE
            layoutEmpty.root.visibility = View.GONE
            layoutError.textViewError.text = "Error $it"
        }
    }

    private val emptyListObserver = Observer<Boolean> {
        binding.run {
            layoutError.root.visibility = View.GONE
            layoutEmpty.root.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadMuseum()
    }
}

