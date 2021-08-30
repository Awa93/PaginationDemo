package com.example.movieapp.ui.movie

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.demotask.network.RetrofitService
import com.example.movieapp.data.Movie
import com.google.gson.Gson

class MyPaging(val s: String, val retrofitService: RetrofitService):PagingSource<Int,Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
       return state.anchorPosition?.let {
            val anchrPage = state.closestPageToPosition(it)
            anchrPage?.prevKey?.plus(1)?:anchrPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page =params.key?:1
        return try {

            val data =  retrofitService.getMovieList("1d094e25","fast","movie",page)

            Log.d("TAG", "load: "+Gson().toJson(data.body()?.Movie))

            LoadResult.Page(data = data.body()?.Movie!!,
            prevKey = if (page==1)null else page-1,
                nextKey = if (data.body()?.Movie?.isEmpty()!!)null else page+1)


        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}