package usuario.example.tpanexoa.ui.llamar;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import usuario.example.tpanexoa.databinding.FragmentLlamarBinding;

public class LlamarFragment extends Fragment {

    public static final int REQUEST_CALL_PHONE_PERMISSION = 1;

    private FragmentLlamarBinding binding;
    private LlamarViewModel viewModel;

    public static LlamarFragment newInstance() {
        return new LlamarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLlamarBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LlamarViewModel.class);
        Button enviarButton = binding.btnEnviar;
        enviarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numeroTelefonico = binding.etNumeroTelefonico.getText().toString();
                viewModel.hacerLlamada(numeroTelefonico, requireActivity());
            }
        });

        viewModel.getMensajeError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String error) {
                if (error != null) {
                    Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL_PHONE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                String numeroTelefonico = binding.etNumeroTelefonico.getText().toString();
                viewModel.setNumeroTelefonico(numeroTelefonico);
            } else {
                Toast.makeText(getContext(), "Permiso denegado para hacer una llamada telefónica", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


