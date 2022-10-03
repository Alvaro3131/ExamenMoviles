package com.example.apirest2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.apirest2.interfaces.CRUDInterface;
import com.example.apirest2.model.Producto;
import com.example.apirest2.utils.Constantes;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    List<Producto> products;
    CRUDInterface crudInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAll();
    }
    private void getAll() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        crudInterface = retrofit.create(CRUDInterface.class);
        Call<List<Producto>> call = crudInterface.getAll();
        call.enqueue(new Callback<List<Producto>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if(!response.isSuccessful()) {
                    Log.e("Response err: ", response.message());
                    return;
                }
                products = response.body();
                products.forEach(p -> Log.i("Prods: ", p.toString()));
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }
}