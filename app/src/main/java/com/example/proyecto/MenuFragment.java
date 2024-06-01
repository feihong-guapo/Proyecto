package com.example.proyecto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.proyecto.model.ArticleAdapter;
import com.example.proyecto.model.NewsApiService;
import com.example.proyecto.model.NewsResponse;
import com.example.proyecto.model.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MenuFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArticleAdapter adapter;
    private List<NewsResponse.Article> articleList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_news, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fetchNews();

        return rootView;
    }
    private void fetchNews() {
        NewsApiService apiService = RetrofitClient.getClient().create(NewsApiService.class);
        Call<NewsResponse> call = apiService.getNews("pub_44787875523bf705d721a7d6e4dabc04d970e", "automocion",  "es", "es");

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    articleList = response.body().getResults();
                    adapter = new ArticleAdapter(articleList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage());
                Toast.makeText(getContext(), "Error en la conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}