package apps.udenar.edu.co.rutasnar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import apps.udenar.edu.co.rutasnar.adapters.EventAdapter;
import apps.udenar.edu.co.rutasnar.model.Event;

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

        eventList.add(new Event(mapInfo));

        eventAdapter = new EventAdapter(EventsActivity.this,eventList);
        recyclerEvents.setAdapter(eventAdapter);
    }
}
