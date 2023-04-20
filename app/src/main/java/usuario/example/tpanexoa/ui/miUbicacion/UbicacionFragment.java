package usuario.example.tpanexoa.ui.miUbicacion;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import usuario.example.tpanexoa.R;
import usuario.example.tpanexoa.databinding.FragmentUbicacionBinding;

public class UbicacionFragment extends Fragment {

    private UbicacionViewModel mViewModel;
    private FragmentUbicacionBinding binding;
    public static UbicacionFragment newInstance() {
        return new UbicacionFragment();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_ubicacion, container, false);

        binding = FragmentUbicacionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_mi_menu).navigate(R.id.nav_miUbicacionFragment);
            }
        });

        return root;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UbicacionViewModel.class);
        // TODO: Use the ViewModel
    }

}