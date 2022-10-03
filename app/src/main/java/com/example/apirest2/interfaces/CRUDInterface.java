package com.example.apirest2.interfaces;

import com.example.apirest2.model.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CRUDInterface {
    @GET("product")
    Call<List<Producto>> getAll();
}
