package com.example.jzunigamusicapp

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import okhttp3.Dispatcher
import okhttp3.OkHttpClient

class MusicApp : Application(), ImageLoaderFactory {
    override fun newImageLoader(): ImageLoader {
        val dispatcher = Dispatcher().apply {
            maxRequests = 2
            maxRequestsPerHost = 1
        }

        return ImageLoader.Builder(this)
            .okHttpClient {
                OkHttpClient.Builder()
                    .dispatcher(dispatcher)
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .addHeader("User-Agent", "Mozilla/5.0 (Linux; Android 14; Pixel 9) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36")
                            .addHeader("Referer", "https://en.wikipedia.org/")
                            .addHeader("Accept", "image/webp,image/apng,image/*,*/*;q=0.8")
                            .build()
                        var response = chain.proceed(request)
                        var retries = 0
                        while (response.code == 429 && retries < 3) {
                            response.close()
                            Thread.sleep(2000L * (retries + 1))
                            response = chain.proceed(request)
                            retries++
                        }
                        response
                    }
                    .build()
            }
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.3)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(cacheDir.resolve("image_cache"))
                    .maxSizePercent(0.1)
                    .build()
            }
            .crossfade(true)
            .build()
    }
}
