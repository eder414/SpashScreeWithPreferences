package curso.clases.spashscreewithpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import curso.clases.spashscreewithpreferences.Activities.LoginActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnLogin, btnLimpiar;
    EditText editTextUsuario, editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLimpiar = findViewById(R.id.btnLimpiar);
        btnLogin = findViewById(R.id.btnLogin);

        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextPassword = findViewById(R.id.editTextPassword);

        btnLogin.setOnClickListener(this);
        btnLimpiar.setOnClickListener(this);


        ValidarPreferencias();
    }

    private void ValidarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("Credenciales",Context.MODE_PRIVATE);
        String usuario = preferences.getString("usuario","");
        String password = preferences.getString("contra","");

        editTextPassword.setText(password);
        editTextUsuario.setText(usuario);
        ValidarCampos();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                ValidarCampos();
                break;
            case R.id.btnLimpiar:

                break;
        }
    }

    private void ValidarCampos() {
        String usuario,password;
        usuario = editTextUsuario.getText().toString();
        password = editTextPassword.getText().toString();
        if(usuario != null && !usuario.equals("") && password != null && !password.equals("") ){
            SalvarPreferencias(usuario,password);
            CambiarActivity();
        }
        else{
            Toast.makeText(MainActivity.this,"Campos incorrecto",Toast.LENGTH_SHORT).show();
        }
    }

    private void CambiarActivity() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void SalvarPreferencias(String usuario, String password) {
        SharedPreferences preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("usuario",usuario);
        editor.putString("contra",password);
        editor.commit();
    }
}