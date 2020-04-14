package apps.udenar.edu.co.rutasnar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    ImageView img_banner;
    Button btn_t, btn_newt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btn_t = findViewById(R.id.btn_t);
        btn_newt = findViewById(R.id.btn_newt);


        btn_newt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,UserRegisterActivity.class));
            }
        });

        btn_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,UserLoginActivity.class));
            }
        });

        img_banner = findViewById(R.id.img_banner);
        Glide.with(getApplicationContext())
                .load("https://1.bp.blogspot.com/-BHIkQhmra0c/XpUUpmVLfsI/AAAAAAAABpk/oO_O3lCxajkVeAksMURtcf5QOEiuDGPIQCLcBGAsYHQ/s1600/banner_rutas2.png")
                .into(img_banner);
    }
}
