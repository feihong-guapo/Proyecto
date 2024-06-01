package com.example.proyecto.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface NewsApiService {
    @GET("news")
    Call<NewsResponse> getNews(
            @Query("apikey") String apiKey,
            @Query("q") String query,
            @Query("country") String country,
            @Query("language") String language
    );
}
