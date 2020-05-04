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

        recyclerEvents = findViewById(R.id.recycler_routes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerEvents.setLayoutManager(linearLayoutManager);


        eventList = new ArrayList<>();

        getEvents();
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
