package com.example.highton_android.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.highton_android.data.model.feed.response.Content
import com.example.highton_android.data.service.FeedService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FeedPagingDataSource @Inject constructor(
    private val service: FeedService,
    private val s: String

) : PagingSource<Int, Content>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Content> {
        return try {
            val page = params.key ?: 1
            val response =
                service.getSchoolEvent(page, s).body()?.content


            val prevKey = if (page == 1) null else page - 1
            LoadResult.Page(
                data = response!!,
                prevKey = prevKey,
                nextKey = null,
            )


        } catch (e: Exception) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: IOException) {
            return LoadResult.Error(e)
        }


    }


    override fun getRefreshKey(state: PagingState<Int, Content>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    }


}