package apps.udenar.edu.co.rutasnar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import apps.udenar.edu.co.rutasnar.R;
import apps.udenar.edu.co.rutasnar.model.Postit;

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
                Toast.makeText(mContext, "CLick", Toast.LENGTH_SHORT).show();
            }
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
