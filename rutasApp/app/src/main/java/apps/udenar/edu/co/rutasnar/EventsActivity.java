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
import apps.udenar.edu.co.rutasnar.interfaces.RutasNarAPI;
import apps.udenar.edu.co.rutasnar.model.Event;
import apps.udenar.edu.co.rutasnar.model.Municipality;
import apps.udenar.edu.co.rutasnar.model.Postit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventsActivity extends AppCompatActivity {

    RecyclerView recyclerEvents;
    private List<Event> eventList;
    private EventAdapter eventAdapter;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        setTitle("Eventos");

        spinner = findViewById(R.id.spinner);


        recyclerEvents = findViewById(R.id.recycler_routes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerEvents.setLayoutManager(linearLayoutManager);


        eventList = new ArrayList<>();

        getEvents();

        getMuniNames(EventsActivity.this);
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
                                eventAdapter = new EventAdapter(EventsActivity.this,eventList);
                                recyclerEvents.setAdapter(eventAdapter);
                            }else{
                                List<Event> local = new ArrayList<>();

                                for (Event e: eventList) {
                                    if(info[position].equals(e.getId_municipio()))local.add(e);
                                }

                                eventAdapter = new EventAdapter(EventsActivity.this,local);
                                recyclerEvents.setAdapter(eventAdapter);
                                //Log.d("NOTICIAS", "onItemSelected: " + info[position]);
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

    private void getEvents(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RutasNarAPI rutasNarAPI = retrofit.create(RutasNarAPI.class);
        Call<List<Event>> call = rutasNarAPI.getEvents();

        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(!response.isSuccessful()){
                    Log.d("NOTICIAS", "onResponse: " + response);
                    Toast.makeText(EventsActivity.this, "Error", Toast.LENGTH_LONG).show();
                    return;
                }

                eventList.clear();
                eventList.addAll(response.body());
                eventAdapter = new EventAdapter(EventsActivity.this,eventList);
                recyclerEvents.setAdapter(eventAdapter);
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

            }
        });
    }


}
