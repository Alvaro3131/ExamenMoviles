package com.example.apirest2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.apirest2.activities.CreateActivity;
import com.example.apirest2.adapter.DistritosAdapters;
import com.example.apirest2.interfaces.CRUDInterface;
import com.example.apirest2.model.Producto;
import com.example.apirest2.utils.Constantes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    List<Producto> products;
    CRUDInterface crudInterface;
    ListView listview;
    FloatingActionButton as;
    EditText search;
    ImageButton image,image2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview=findViewById(R.id.listView);
        as=findViewById(R.id.createButton);
        search=findViewById(R.id.EditTextBusqueda);
        image=findViewById(R.id.imageButton2);
        image2=findViewById(R.id.imageButton3);
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAll();
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSearch(search.getText().toString());


            }
        });
        as.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callCreate();
            }
        });

        getAll();
    }
    private void callCreate(){
        Intent  intent= new Intent(getApplicationContext(), CreateActivity.class);
        startActivity(intent);
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
                DistritosAdapters distritoadapter=new DistritosAdapters(products,getApplicationContext());
                listview.setAdapter(distritoadapter);
                products.forEach(p -> Log.i("Estudiantes: ", p.toString()));
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }
    private void getSearch(String nombre) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        crudInterface = retrofit.create(CRUDInterface.class);
        Call<List<Producto>> call = crudInterface.getSearch(nombre);
        call.enqueue(new Callback<List<Producto>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if(!response.isSuccessful()) {
                    Log.e("Response err: ", response.message());
                    return;
                }

                products = response.body();
                DistritosAdapters distritoadapter=new DistritosAdapters(products,getApplicationContext());
                listview.setAdapter(distritoadapter);
                products.forEach(p -> Log.i("Estudiantes: ", p.toString()));
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }
}