package com.valeriano.photospaceilustration.data;

import com.valeriano.photospaceilustration.model.ModelNasa;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by luis.valeriano on 30/07/2016.
 */
public interface ApodService {
    //la cosntrucción de la petición se pone el key que genere de la nasa.gov
    @GET("planetary/apod?api_key=DN4CQ6mdAnjesM9HvxOG4vluKWaTCkjUzMV07b0i")
    //Se agrega un end point Call<List <ApodService> >
    Call<ModelNasa>  getTodayApod();

    @GET ("planetary/apod")
    Call<ModelNasa> getTodayApodWithQuery(@Query("api_key") String apiKey);
}
