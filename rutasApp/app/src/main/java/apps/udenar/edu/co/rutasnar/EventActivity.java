package apps.udenar.edu.co.rutasnar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EventActivity extends AppCompatActivity {

    private ImageView img_event;
    private FloatingActionButton btn_favorite;
    private WebView webView;
    private TextView lbl_title;
    private TextView lbl_desc;
    private TextView lbl_date;
    private TextView lbl_place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        img_event = findViewById(R.id.img_event);
        lbl_title = findViewById(R.id.lbl_event_title);
        lbl_desc = findViewById(R.id.lbl_event_desc);
        lbl_date = findViewById(R.id.lbl_event_date);
        lbl_place = findViewById(R.id.lbl_event_place);

        Bundle b = getIntent().getExtras();

        if (b != null){
            String id_evento = b.getString("id_evento");
            String id_municipio =b.getString("id_municipio");
            String nom_evento = b.getString("nom_evento");
            String desc_evento = b.getString("desc_evento");
            String img_evento = b.getString("img_evento");
            String fecha_evento = b.getString("fecha_evento");
            String disponible = b.getString("disponible");

            Glide.with(getApplicationContext()).load(img_evento).into(img_event);

            lbl_title.setText(nom_evento);
            lbl_date.setText(fecha_evento);
            lbl_desc.setText(desc_evento);
            lbl_place.setText(id_municipio);
        }
    }
}
