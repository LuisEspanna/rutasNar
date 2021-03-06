package apps.udenar.edu.co.rutasnar.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.List;

import apps.udenar.edu.co.rutasnar.ApiUtils;
import apps.udenar.edu.co.rutasnar.EventActivity;
import apps.udenar.edu.co.rutasnar.R;
import apps.udenar.edu.co.rutasnar.interfaces.RutasNarAPI;
import apps.udenar.edu.co.rutasnar.model.Event;
import apps.udenar.edu.co.rutasnar.model.Postit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private List<Event> eventList;
    private Context mContext;

    public EventAdapter(Context mContext , List<Event> eventList) {
        this.eventList = eventList;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.event_item, parent, false);
        return new EventAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event e = eventList.get(position);

        Glide.with(mContext).load(e.getImg_evento()).into(holder.img_item_event);
        holder.btn_item_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, "No disponible :(", Toast.LENGTH_LONG).show();
                saveEvent(ApiUtils.getCurrentUser(mContext).getIdUsuario(), e.getNom_evento(), e.getId_evento());
            }
        });

        holder.btn_item_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(mContext, EventActivity.class);
                it.putExtras(e.toBundle());
                mContext.startActivity(it);
            }
        });

        holder.txt_item_title.setText(e.getNom_evento());
        holder.txt_item_description.setText(e.getDescEventSumary());
        holder.txt_item_date.setText(e.getFecha_evento());
    }

    private void saveEvent(String idUsuario, String nom_evento, String id_evento) {
        RutasNarAPI rutasNarAPI = ApiUtils.getAPIService();
        rutasNarAPI.postit(idUsuario, nom_evento, "", id_evento).enqueue(new Callback<List<Postit>>() {
            @Override
            public void onResponse(Call<List<Postit>> call, Response<List<Postit>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(mContext, "Evento guardado :D", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(mContext, "Ya existe...", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Postit>> call, Throwable t) {}
        });
    }


    @Override
    public int getItemCount() {
        return eventList.size();
    }

    //---------------------------------------------------------------------------------------------- ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView img_item_event;
        public FloatingActionButton btn_item_favorite;
        public TextView txt_item_title;
        public TextView txt_item_description;
        public TextView txt_item_date;
        public Button btn_item_more;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_item_event = itemView.findViewById(R.id.img_item_event);
            btn_item_favorite = itemView.findViewById(R.id.btn_item_favorite);
            txt_item_title = itemView.findViewById(R.id.txt_item_title);
            txt_item_description = itemView.findViewById(R.id.txt_item_description);
            txt_item_date = itemView.findViewById(R.id.txt_item_time);
            btn_item_more  = itemView.findViewById(R.id.btn_item_more);
        }
    }
}
