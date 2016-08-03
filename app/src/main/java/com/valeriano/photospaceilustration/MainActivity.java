package com.valeriano.photospaceilustration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.valeriano.photospaceilustration.data.ApodService;
import com.valeriano.photospaceilustration.data.Data;
import com.valeriano.photospaceilustration.model.ModelNasa;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView txtDate;
    private TextView txtExplication;
    private TextView txtTitle;
    private ImageView imgCosmo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("activity_main:", BuildConfig.URL);
        Log.d("APPLICATION_ID:", BuildConfig.APPLICATION_ID);

        txtDate = (TextView) findViewById(R.id.activity_main_date);
        txtExplication = (TextView) findViewById(R.id.activity_main_explication);
        txtTitle = (TextView) findViewById(R.id.activity_main_title);
        imgCosmo = (ImageView) findViewById(R.id.activity_main_image);

        // se debe de realizar llamadas asincronas en todas las aplicaciones
        ApodService apodService = Data.getRetrofitInterfase().create(ApodService.class);

        //Call<ModelNasa> callApodService = apodService.getTodayApod();
        String key_nasa = "DN4CQ6mdAnjesM9HvxOG4vluKWaTCkjUzMV07b0i";
        Call<ModelNasa> callApodService = apodService.getTodayApodWithQuery(key_nasa);
        //Ejecución de tareas asincronas enqueue
        //mensajees entre objetos Callback
        callApodService.enqueue(new Callback <ModelNasa>() {

            /**
             * Invoked for a received HTTP response.
             * <p>
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call {@link Response#isSuccessful()} to determine if the response indicates success.
             *
             * @param call
             * @param response
             */
            @Override
            public void onResponse(Call<ModelNasa> call, Response<ModelNasa> response) {
                Log.d("<-----> title:", response.body().getTitle());
                Log.d("<-----> date:", response.body().getDate());
                txtDate.setText(response.body().getDate());
                txtExplication.setText(response.body().getExplanation());
                txtTitle.setText(response.body().getTitle());
                //Picasso.with(getBaseContext()).load(response.body().getUrl()).into(imgCosmo);
            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected
             * exception occurred creating the request or processing the response.
             *
             * @param call
             * @param t
             */
            @Override
            public void onFailure(Call<ModelNasa> call, Throwable t) {

            }
        });
    }
}
