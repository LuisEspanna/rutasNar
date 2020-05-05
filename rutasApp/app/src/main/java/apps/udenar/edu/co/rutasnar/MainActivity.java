package apps.udenar.edu.co.rutasnar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import apps.udenar.edu.co.rutasnar.adapters.PostitAdapter;
import apps.udenar.edu.co.rutasnar.adapters.RouteAdapter;
import apps.udenar.edu.co.rutasnar.interfaces.RutasNarAPI;
import apps.udenar.edu.co.rutasnar.model.Postit;
import apps.udenar.edu.co.rutasnar.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton btn_rutas;
    private FloatingActionButton btn_eventos;
    private RecyclerView recyclerPostits;
    private RutasNarAPI rutasNarAPI;
    private DatabaseHelper dbHelper;
    private List<Postit> mPostits;
    private PostitAdapter positAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(MainActivity.this);
        rutasNarAPI = ApiUtils.getAPIService();

        btn_rutas = findViewById(R.id.float_btn_rutas);
        btn_rutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RoutesActivity.class));
            }
        });

        btn_eventos = findViewById(R.id.float_btn_eventos);
        btn_eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EventsActivity.class));
            }
        });

        recyclerPostits = findViewById(R.id.recycler_postits);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerPostits.setLayoutManager(linearLayoutManager);
        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(recyclerPostits);

        mPostits = new ArrayList<>();
        getPostits();
    }

    private void getPostits() {

        User user = dbHelper.getCurrentUser();

        if(user == null)return;

        rutasNarAPI.postit(user.getIdUsuario(), "", "", "").enqueue(new Callback<List<Postit>>() {
            @Override
            public void onResponse(Call<List<Postit>> call, Response<List<Postit>> response) {
                if (response.isSuccessful()){
                    Log.d("NOTICIAS", "getPostits: " + response.body());
                    mPostits.clear();
                    mPostits.addAll(response.body());
                    positAdapter = new PostitAdapter(MainActivity.this, mPostits);
                    recyclerPostits.setAdapter(positAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Postit>> call, Throwable t) {}
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPostits();
    }

    ItemTouchHelper.SimpleCallback itemTouchHelper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Postit p = mPostits.get(viewHolder.getAdapterPosition());
            //Agregar codigo para eliminar un postit
            //
            //
            //...
            mPostits.remove(p);
            positAdapter.notifyDataSetChanged();
        }
    };

}
