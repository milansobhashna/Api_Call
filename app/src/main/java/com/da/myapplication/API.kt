package com.da.myapplication

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

interface API {
    @FormUrlEncoded
    @POST("api/getcategory.php")
    fun categoryApi(
        @Field("limit") limit: Int?,
        @Field("offset") currentpage: Int?
    ): Observable<CategoryList>
}