package apps.udenar.edu.co.rutasnar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import apps.udenar.edu.co.rutasnar.adapters.EventAdapter;
import apps.udenar.edu.co.rutasnar.adapters.PostitAdapter;
import apps.udenar.edu.co.rutasnar.adapters.RouteAdapter;
import apps.udenar.edu.co.rutasnar.interfaces.RutasNarAPI;
import apps.udenar.edu.co.rutasnar.model.Event;
import apps.udenar.edu.co.rutasnar.model.Municipality;
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
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);

        setTitle("Rutas");

        spinner = findViewById(R.id.spinner_routes);

        recyclerView = findViewById(R.id.recycler_routes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        mRoutes = new ArrayList<>();

        getRoutes();

        getMuniNames(RoutesActivity.this);
    }

    private void getMuniNames(Context mContext) {
        RutasNarAPI rutasNarAPI = ApiUtils.getAPIService();
        rutasNarAPI.getMunicipality().enqueue(new Callback<List<Municipality>>() {
            @Override
            public void onResponse(Call<List<Municipality>> call, Response<List<Municipality>> response) {
                if(response.isSuccessful()){
                    List<Municipality> lstMuni = response.body();
                    String [] info = new String[lstMuni.size()+1];
                    info[0] = "Todos";

                    for (int i = 0; i<lstMuni.size(); i++ ){
                        info[i+1] = lstMuni.get(i).getNom_municipio();
                    }

                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(mContext,   android.R.layout.simple_spinner_item, info);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(spinnerArrayAdapter);

                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            if (info[position].equals("Todos")){
                                routeAdapter = new RouteAdapter(mContext,mRoutes);
                                recyclerView.setAdapter(routeAdapter);
                            }else{
                                List<Route> local = new ArrayList<>();

                                for (Route e: mRoutes) {
                                    if(info[position].equals(e.getId_municipio()))local.add(e);
                                }

                                routeAdapter = new RouteAdapter(mContext,local);
                                recyclerView.setAdapter(routeAdapter);
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            return;
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Municipality>> call, Throwable t) {}
        });
    }

    private void getRoutes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUtils.BASE_URL)
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
