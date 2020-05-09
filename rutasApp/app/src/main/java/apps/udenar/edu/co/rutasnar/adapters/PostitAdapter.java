package apps.udenar.edu.co.rutasnar.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import apps.udenar.edu.co.rutasnar.ApiUtils;
import apps.udenar.edu.co.rutasnar.EventActivity;
import apps.udenar.edu.co.rutasnar.MainActivity;
import apps.udenar.edu.co.rutasnar.R;
import apps.udenar.edu.co.rutasnar.RouteActivity;
import apps.udenar.edu.co.rutasnar.interfaces.RutasNarAPI;
import apps.udenar.edu.co.rutasnar.model.Event;
import apps.udenar.edu.co.rutasnar.model.Postit;
import apps.udenar.edu.co.rutasnar.model.Route;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostitAdapter extends RecyclerView.Adapter<PostitAdapter.ViewHolder>{
    private Context mContext;
    private List<Postit> postits;

    public PostitAdapter(Context context, List<Postit> postits) {
        this.mContext = context;
        this.postits = postits;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.posit_item, parent, false);
        return new PostitAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        Postit p = postits.get(position);

        h.txt_post_titulo.setText(p.getNom_actividad());
        h.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, "CLick", Toast.LENGTH_SHORT).show();
                if(p.getId_evento().length()>0){
                    getFavoriteEvent(p.getId_evento());
                }else{
                    getFavoriteRoute(p.getId_ruta());
                }
            }
        });
    }

    private void getFavoriteEvent(String idEvent) {
        RutasNarAPI rutasNarAPI = ApiUtils.getAPIService();
        rutasNarAPI.getEvent(idEvent).enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if (response.isSuccessful()){
                    Log.d("NOTICIAS", "getEvent: " + response.body());
                    List<Event> res = response.body();

                    if(res.size() > 0){
                        Event e = res.get(0);
                        Intent it = new Intent(mContext, EventActivity.class);
                        it.putExtras(e.toBundle());
                        mContext.startActivity(it);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {}
        });
    }

    private void getFavoriteRoute(String idRoute) {
        RutasNarAPI rutasNarAPI = ApiUtils.getAPIService();
        rutasNarAPI.getRoute(idRoute).enqueue(new Callback<List<Route>>() {
            @Override
            public void onResponse(Call<List<Route>> call, Response<List<Route>> response) {
                if (response.isSuccessful()){
                    Log.d("NOTICIAS", "getRoute: " + response.body().toString());
                    List<Route> res = response.body();

                    if(res.size() > 0){
                        Route r = res.get(0);
                        Intent it = new Intent(mContext, RouteActivity.class);
                        it.putExtras(r.toBundle());
                        mContext.startActivity(it);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Route>> call, Throwable t) {}
        });
    }

    @Override
    public int getItemCount() {
        return postits.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txt_post_titulo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_post_titulo = itemView.findViewById(R.id.txt_post_titulo);
        }
    }
}
