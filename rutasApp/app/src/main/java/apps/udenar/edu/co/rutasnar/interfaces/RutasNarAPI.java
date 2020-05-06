package apps.udenar.edu.co.rutasnar.interfaces;

import java.util.List;

import apps.udenar.edu.co.rutasnar.model.Event;
import apps.udenar.edu.co.rutasnar.model.Municipality;
import apps.udenar.edu.co.rutasnar.model.Postit;
import apps.udenar.edu.co.rutasnar.model.Route;
import apps.udenar.edu.co.rutasnar.model.User;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;

public interface RutasNarAPI {

    @GET("events")
    Call<List<Event>> getEvents();

    @GET("routes")
    Call<List<Route>> getRoutes();

    @GET("municipality")
    Call<List<Municipality>> getMunicipality();

    @POST("users")
    @FormUrlEncoded
    Call<User> newUser(@Field("id_usuario") String userId,
                       @Field("nom_usuario") String userName,
                       @Field("clave_usuario") String userPass);

    @POST("loggin")
    @FormUrlEncoded
    Call<User> loggin(@Field("id_usuario") String userId,
                       @Field("nom_usuario") String userName,
                       @Field("clave_usuario") String userPass);


    @POST("activity")
    @FormUrlEncoded
    Call<List<Postit>> postit(@Field("id_usuario") String idUser,
                                 @Field("nom_actividad") String nomActivity,
                                 @Field("id_ruta") String idRoute,
                                 @Field("id_evento") String idEvent);


    @FormUrlEncoded
    @HTTP(method = "DELETE" , path = "activity",  hasBody = true)
    Call<List<Postit>> deletePostit(@Field("id_actividad") String idActivity);


}
