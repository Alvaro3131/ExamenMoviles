package com.example.apirest2.interfaces;

import com.example.apirest2.model.Facultad;
import com.example.apirest2.model.Producto;

import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CRUDInterface {
    @GET("producto")
    Call<List<Producto>> getAll();

    @GET("producto/search/{nombre}")
    Call<List<Producto>> getSearch(@Path("nombre") String nombre);


    @GET("categorias")
    Call<List<Facultad>> getAllSpinner();


    @GET("producto/{id}")
    Call<List<Producto>> getOne(@Path("id") int id);


    @POST("producto")
    Call<Void> create(@Body HashMap<String,String> map);

    @DELETE("producto/{id}")
    Call<Void> delete(@Path("id") int id);

    @PUT("producto/{id}")
    Call<Void> edit( @Path("id") int id,@Body HashMap<String,String> map);
}
