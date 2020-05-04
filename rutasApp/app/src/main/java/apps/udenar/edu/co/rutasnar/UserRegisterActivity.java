package apps.udenar.edu.co.rutasnar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import apps.udenar.edu.co.rutasnar.interfaces.RutasNarAPI;
import apps.udenar.edu.co.rutasnar.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRegisterActivity extends AppCompatActivity {

    EditText txtUserName, txtPassword, txtPasswordR;
    Button mButtonRegister;
    DatabaseHelper db;
    RutasNarAPI rutasNarAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        setTitle("Registro del viajero");

        rutasNarAPI = ApiUtils.getAPIService();

        db = new DatabaseHelper(this);
        txtUserName = findViewById(R.id.edittext_username);
        txtPassword = findViewById(R.id.edittext_passwd);
        txtPasswordR = findViewById(R.id.edittext_cnf_passwd);
        mButtonRegister = findViewById(R.id.btn_register);
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = txtUserName.getText().toString();
                String pass = txtPassword.getText().toString();
                String passR = txtPasswordR.getText().toString();

                if (pass.equals(passR))createUser(username, pass);
                else Toast.makeText(UserRegisterActivity.this,"Las contraseñas no coinciden...", Toast.LENGTH_SHORT).show();

                /*
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnf_pwd = mTextCnfPassword.getText().toString().trim();
                if(pwd.equals(cnf_pwd)){
                    long val = db.addUser(user,pwd);
                    if(val > 0){
                        Toast.makeText(UserRegisterActivity.this,"Te has registrado", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(UserRegisterActivity.this,UserLoginActivity.class);
                        startActivity(moveToLogin);
                    }
                    else{
                        Toast.makeText(UserRegisterActivity.this,"Ha ocurrido un error en el proceso de registro",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(UserRegisterActivity.this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
                }
                 */
            }
        });

    }

    private void createUser(String user, String pwd){
        rutasNarAPI.newUser("0", user, pwd).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    Log.d("NOTICIAS", "onResponse: " + response.body().toString());
                    User u = response.body();

                    DatabaseHelper db = new DatabaseHelper(UserRegisterActivity.this);
                    db.addUser(u);
                    db.close();

                    Intent MainActivity = new Intent(UserRegisterActivity.this,MainActivity.class);
                    startActivity(MainActivity);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {}
        });
    }
}
