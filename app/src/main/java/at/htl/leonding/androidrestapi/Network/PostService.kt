package at.htl.leonding.androidrestapi.Network

import at.htl.leonding.androidrestapi.Model.Post
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://www.htl-leonding.at/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface PostService {
    @GET("wp-json/?rest_route=/wp/v2/posts")
    suspend fun getPosts(): List<Post>
}

object PostApi {
    val retrofitService: PostService by lazy { retrofit.create(PostService::class.java) }
}