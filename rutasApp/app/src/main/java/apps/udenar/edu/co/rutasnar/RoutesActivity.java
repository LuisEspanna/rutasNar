package apps.udenar.edu.co.rutasnar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import apps.udenar.edu.co.rutasnar.adapters.EventAdapter;
import apps.udenar.edu.co.rutasnar.adapters.RouteAdapter;
import apps.udenar.edu.co.rutasnar.interfaces.RutasNarAPI;
import apps.udenar.edu.co.rutasnar.model.Event;
import apps.udenar.edu.co.rutasnar.model.Route;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoutesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Route> mRoutes;
    private RouteAdapter routeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);

        setTitle("Rutas");

        recyclerView = findViewById(R.id.recycler_routes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        mRoutes = new ArrayList<>();

        getRoutes();
    }

    private void getRoutes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://181.55.121.253:8880/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RutasNarAPI rutasNarAPI = retrofit.create(RutasNarAPI.class);
        Call<List<Route>> call = rutasNarAPI.getRoutes();

        call.enqueue(new Callback<List<Route>>() {
            @Override
            public void onResponse(Call<List<Route>> call, Response<List<Route>> response) {
                if(!response.isSuccessful()){
                    Log.d("NOTICIAS", "onResponse: " + response);
                    Toast.makeText(RoutesActivity.this, "Error", Toast.LENGTH_LONG).show();
                    return;
                }

                mRoutes.clear();
                mRoutes.addAll(response.body());
                routeAdapter = new RouteAdapter(RoutesActivity.this, mRoutes);
                recyclerView.setAdapter(routeAdapter);
            }

            @Override
            public void onFailure(Call<List<Route>> call, Throwable t) {}
        });
    }
}
