package apps.udenar.edu.co.rutasnar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import apps.udenar.edu.co.rutasnar.adapters.EventAdapter;
import apps.udenar.edu.co.rutasnar.interfaces.RutasNarAPI;
import apps.udenar.edu.co.rutasnar.model.Event;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventsActivity extends AppCompatActivity {

    RecyclerView recyclerEvents;
    private List<Event> eventList;
    private EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        setTitle("Eventos");

        recyclerEvents = findViewById(R.id.recycler_events);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerEvents.setLayoutManager(linearLayoutManager);


        eventList = new ArrayList<>();

        Map<String, String> mapInfo = new HashMap<>();
        mapInfo.put("id_evento", "123456");
        mapInfo.put("id_municipio", "654321");
        mapInfo.put("nom_evento", "Blancos y Negros");
        mapInfo.put("desc_evento", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi");
        mapInfo.put("img_evento", "carnaval.png");
        mapInfo.put("fecha_evento", "2021-01-02");
        mapInfo.put("disponible", "true");
        mapInfo.put("latitud", "1.2");
        mapInfo.put("longitud", "-77.234");

        //eventList.add(new Event(mapInfo));

        getEvents();
    }

    private void getEvents(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://181.55.121.253:8880/api/")
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
