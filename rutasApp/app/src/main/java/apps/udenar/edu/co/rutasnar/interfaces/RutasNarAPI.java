package apps.udenar.edu.co.rutasnar.interfaces;

import java.util.List;

import apps.udenar.edu.co.rutasnar.model.Event;
import apps.udenar.edu.co.rutasnar.model.Route;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RutasNarAPI {

    @GET("events")
    Call<List<Event>> getEvents();

    @GET("routes")
    Call<List<Route>> getRoutes();
}
