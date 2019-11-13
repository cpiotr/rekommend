package pl.ciruk.rekommend

import kotlinx.coroutines.*

import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit
import kotlin.coroutines.suspendCoroutine

val client = OkHttpClient.Builder()
    .readTimeout(1, TimeUnit.SECONDS)
    .connectTimeout(1, TimeUnit.SECONDS)
    .build()

fun main() = runBlocking {
    val digg = async {
        get("http://digg.com")
    }
    val wp = async {
        get("http://wp.pl")
    }
    println(digg.await() + wp.await())
    client.dispatcher.executorService.shutdown();
    client.connectionPool.evictAll();
}

suspend fun get(url: String): String {
    return suspendCoroutine {
        val request = Request.Builder()
            .url(url)
            .build()
        val response = client.newCall(request).execute()
        it.resumeWith(Result.success(response.body!!.string()))
    }
}
