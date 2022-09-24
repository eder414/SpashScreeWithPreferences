package curso.clases.spashscreewithpreferences.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import curso.clases.spashscreewithpreferences.MainActivity;
import curso.clases.spashscreewithpreferences.R;

public class LoginActivity extends AppCompatActivity {
    Button btnCerraSession;
    TextView textViewUsuario,textViewPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnCerraSession = findViewById(R.id.btnCerraSession);

        textViewUsuario = findViewById(R.id.textViewUsuario);
        textViewPassword = findViewById(R.id.textViewPassword);


        SharedPreferences preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String usuario = preferences.getString("usuario","");
        String password = preferences.getString("contra","");

        textViewPassword.setText(password);
        textViewUsuario.setText(usuario);

        btnCerraSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EliminarPreferencias();
            }
        });

    }

    private void EliminarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("Credenciales",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("usuario");
        editor.remove("contra");
        editor.commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}