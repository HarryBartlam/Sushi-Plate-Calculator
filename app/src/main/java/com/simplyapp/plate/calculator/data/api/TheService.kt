package com.simplyapp.plate.calculator.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TheService {
    @GET("/api.php")
    fun getNothingBySearch(@Query("search") searchString: String): Single<Nothing>

    @GET("/api.php")
    fun getNothingByTitle(@Query("page") titleString: String): Single<Nothing>
}
