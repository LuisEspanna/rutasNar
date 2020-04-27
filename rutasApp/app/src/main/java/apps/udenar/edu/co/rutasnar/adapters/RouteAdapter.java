package apps.udenar.edu.co.rutasnar.adapters;

import android.content.Context;
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

import apps.udenar.edu.co.rutasnar.R;
import apps.udenar.edu.co.rutasnar.model.Route;

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.ViewHolder>{

    private List<Route> mRoutes;
    private Context mContext;

    public RouteAdapter(Context context, List<Route> mRoutes) {
        this.mRoutes = mRoutes;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.route_item, parent, false);
        return new RouteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        Route r = mRoutes.get(position);

        Glide.with(mContext).load(r.getImg_ruta()).into(h.img_item_route);
        h.btn_item_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "No disponible :(", Toast.LENGTH_LONG).show();
            }
        });

        h.txt_item_title.setText(r.getNom_ruta());
        h.txt_item_description.setText(r.getDesc_ruta());
        h.txt_item_time.setText(r.getTiempo_ruta());
        h.txt_municipio.setText(r.getId_municipio());
    }

    @Override
    public int getItemCount() {
        return mRoutes.size();
    }

    //-------------------------------------------------- ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView img_item_route;
        public FloatingActionButton btn_item_favorite;
        public TextView txt_item_title;
        public TextView txt_item_description;
        public TextView txt_item_time;
        public Button btn_item_more;
        public TextView txt_municipio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_item_route = itemView.findViewById(R.id.img_item_route);
            btn_item_favorite = itemView.findViewById(R.id.btn_route_favorite);
            txt_item_title = itemView.findViewById(R.id.txt_item_title);
            txt_item_description = itemView.findViewById(R.id.txt_item_description);
            txt_item_time = itemView.findViewById(R.id.txt_item_time);
            btn_item_more  = itemView.findViewById(R.id.btn_item_more);
            txt_municipio = itemView.findViewById(R.id.txt_item_municipio);
        }
    }
}
