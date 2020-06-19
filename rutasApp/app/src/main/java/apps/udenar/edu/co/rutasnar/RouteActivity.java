package apps.udenar.edu.co.rutasnar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import apps.udenar.edu.co.rutasnar.R;
import apps.udenar.edu.co.rutasnar.interfaces.RutasNarAPI;
import apps.udenar.edu.co.rutasnar.model.Postit;
import apps.udenar.edu.co.rutasnar.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RouteActivity extends AppCompatActivity {

    private ImageView img_route;
    private FloatingActionButton btn_favorite;
    private TextView lbl_title;
    private TextView lbl_descrip;
    private TextView lbl_municipio;
    private TextView lbl_tiempo;
    private WebView webView;
    private String global_id_ruta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        img_route = findViewById(R.id.img_route);
        lbl_title = findViewById(R.id.txt_title_route);
        lbl_descrip = findViewById(R.id.txt_route_desc);

        lbl_municipio = findViewById(R.id.txt_route_mun);
        lbl_tiempo = findViewById(R.id.lbl_route_time);

        webView = findViewById(R.id.webview_route);
        webView.getSettings().setDomStorageEnabled(true);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setAppCacheEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccessFromFileURLs(true);

        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setDomStorageEnabled(true);


        //--Caché
        settings.setAppCacheEnabled(true);
        //webView.setRendererPriorityPolicy(RENDERER_PRIORITY_BOUND, true);

        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        if(getIntent().getExtras() != null){
            Bundle b = getIntent().getExtras();
            String id_ruta = b.getString("id_ruta");
            global_id_ruta = id_ruta;
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

            webView.loadUrl(ApiUtils.MAP_URL_ROUTES + id_ruta);
        }

        btn_favorite = findViewById(R.id.btn_favorite_route);
        btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(RouteActivity.this);
                User user = db.getCurrentUser();
                db.close();
                saveRoute(user.getIdUsuario(),lbl_title.getText().toString(), global_id_ruta);
                Toast.makeText(RouteActivity.this, "Ruta guardada :D", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void saveRoute(String idUsuario, String nom_ruta, String id_ruta) {
        RutasNarAPI rutasNarAPI = ApiUtils.getAPIService();
        rutasNarAPI.postit(idUsuario, nom_ruta, id_ruta, "").enqueue(new Callback<List<Postit>>() {
            @Override
            public void onResponse(Call<List<Postit>> call, Response<List<Postit>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(RouteActivity.this, "Ruta guardada :D", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(RouteActivity.this, "Ya existe...", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Postit>> call, Throwable t) {}
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }

}
