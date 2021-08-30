package com.example.movieapp.ui.movie

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentMovie : Fragment() {
    lateinit var binding :FragmentMovieBinding
    val viewModel : MovieViewModel by viewModels<MovieViewModel>()
    val movieAdapter= MovieAdapter()
    var mProgressDialog : ProgressDialog? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentMovieBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mProgressDialog= ProgressDialog(context)
        mProgressDialog?.setCancelable(false)
        mProgressDialog?.setMessage("Processsing...")
        mProgressDialog?.show()
        viewModel.setQuery("it")

        initRecyclerView()
        /*binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {


                }
                return true
            }
        })*/
        viewModel.list.observe(viewLifecycleOwner){
            movieAdapter.submitData(lifecycle,it)
            mProgressDialog?.dismiss()
        }

    }

    private fun initRecyclerView() {
        binding.movieList.apply {
            adapter=movieAdapter
            layoutManager=GridLayoutManager(requireContext(),2)
        }
    }

}