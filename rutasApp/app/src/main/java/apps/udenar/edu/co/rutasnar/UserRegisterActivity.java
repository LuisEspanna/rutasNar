package apps.udenar.edu.co.rutasnar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserRegisterActivity extends AppCompatActivity {

    EditText mTextUsername, mTextPassword, mTextCnfPassword;
    Button mButtonRegister;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        setTitle("Registro del viajero");

        db = new DatabaseHelper(this);
        mTextUsername = findViewById(R.id.edittext_username);
        mTextPassword = findViewById(R.id.edittext_passwd);
        mTextCnfPassword = findViewById(R.id.edittext_cnf_passwd);
        mButtonRegister = findViewById(R.id.btn_register);
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
            }
        });

    }
}
