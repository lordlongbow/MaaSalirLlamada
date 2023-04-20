package usuario.example.tpanexoa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import usuario.example.tpanexoa.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private EditText etUsuario, etContrasenia;

    private Button btIngresar;
    private LogginActivityViewModel viewModel;

    private ActivityMainBinding binding;

    private LogginActivityViewModel mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LogginActivityViewModel.class);

        binding.btIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.validar(binding.etUsuario.getText().toString(), binding.etContrasenia.getText().toString());
            }
        });

        mv.getResultado().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                //segunda actividad
                Intent intent = new Intent(MainActivity.this, MiMenu.class);
                startActivity(intent);
            }
        });
        mv.getResultadoNegativo().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.tvREsp.setText("Acceso Denegado");
            }
        });
    }
}