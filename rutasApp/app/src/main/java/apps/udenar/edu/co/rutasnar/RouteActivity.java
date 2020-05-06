package apps.udenar.edu.co.rutasnar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import apps.udenar.edu.co.rutasnar.R;

public class RouteActivity extends AppCompatActivity {

    private ImageView img_route;
    private FloatingActionButton btn_favorite;
    private TextView lbl_title;
    private TextView lbl_descrip;
    private TextView lbl_municipio;
    private TextView lbl_tiempo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        img_route = findViewById(R.id.img_route);
        lbl_title = findViewById(R.id.txt_title_route);
        lbl_descrip = findViewById(R.id.txt_route_desc);

        lbl_municipio = findViewById(R.id.txt_route_mun);
        lbl_tiempo = findViewById(R.id.lbl_route_time);

        if(getIntent().getExtras() != null){
            Bundle b = getIntent().getExtras();
            String id_ruta = b.getString("id_ruta");
            String id_municipio = b.getString("id_municipio");
            String nom_ruta = b.getString("nom_ruta");
            String desc_ruta = b.getString("desc_ruta");
            String img_ruta = b.getString("img_ruta");
            String tiempo_ruta = b.getString("tiempo_ruta");

            Glide.with(getApplicationContext()).load(img_ruta).into(img_route);

            lbl_title.setText(nom_ruta);
            lbl_descrip.setText(desc_ruta);
            lbl_municipio.setText(id_municipio);
            lbl_tiempo.setText(tiempo_ruta);
        }

        btn_favorite = findViewById(R.id.btn_route_favorite);
        /*
        btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Favorita :v

            }
        });*/

    }
}
