package apps.udenar.edu.co.rutasnar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserLoginActivity extends AppCompatActivity {

    EditText mTextUsername, mTextPassword;
    Button mButtonLogin;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        setTitle("Inicio de sesión");


        db = new DatabaseHelper(this);
        mTextUsername = findViewById(R.id.edittext_username);
        mTextPassword = findViewById(R.id.edittext_passwd);
        mButtonLogin = findViewById(R.id.btn_login);

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                boolean res = db.checkUser(user, pwd);
                if(res)
                {
                    Intent MainActivity = new Intent(UserLoginActivity.this,MainActivity.class);
                    startActivity(MainActivity);
                }
                else
                {
                    Toast.makeText(UserLoginActivity.this,"Error de inicio de sesión",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
