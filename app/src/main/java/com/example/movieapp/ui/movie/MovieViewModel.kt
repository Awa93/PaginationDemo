package com.example.movieapp.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.demotask.network.RetrofitService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val apiInterface:RetrofitService):ViewModel() {

    private val query = MutableLiveData<String>("")
     val list = query.switchMap {
        Pager(PagingConfig(pageSize = 10)){
            MyPaging(it,apiInterface)
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(s:String){
        query.postValue(s)
    }

}