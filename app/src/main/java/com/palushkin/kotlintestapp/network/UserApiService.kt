/*
 * 20.01.2021
 * @author Maksim Palushkin
 */

package com.palushkin.kotlintestapp.network


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//private const val BASE_URL = "https://reqres.in/api/users"
private const val BASE_URL = "https://reqres.in/api/"

class UserAdapter {

    @WrappedRepoList
    @FromJson
    fun fromJson(json: UserList): List<User> {
        return json.dataU
    }

    @ToJson
    fun toJson(@WrappedRepoList value: List<User>): UserList {
        throw UnsupportedOperationException()
    }
}

private val moshi = Moshi.Builder()
        .add(UserAdapter())
        //.add(KotlinJsonAdapterFactory())
        //.addLast(KotlinJsonAdapterFactory())
        .build()


val spec: ConnectionSpec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
        .tlsVersions(TlsVersion.TLS_1_2)
        .build()


//val tlsSocketFactory: TLSSocketFactory = TLSSocketFactory()
val tlsSocketFactory: TLSSocketFactoryK = TLSSocketFactoryK()


//@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
val okHttpBuilder = OkHttpClient.Builder()
        //.connectionSpecs(mutableListOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS))
        //.connectionSpecs(Collections.singletonList(spec))
        //.sslSocketFactory(tlsSocketFactory, tlsSocketFactory.trustManager)
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        //.client(okHttpBuilder)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

interface UserApiService {

    @GET("users")
    @WrappedRepoList
    suspend fun getUsers(): List<User>
}

object UserApi {
    val retrofitService: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }
}