package usuario.example.tpanexoa.ui.miUbicacion;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class UbicacionViewModel extends AndroidViewModel {

    private FusedLocationProviderClient fusedLocationClient;
    private MutableLiveData<Location> ubicacionActual = new MutableLiveData<>();
    private MutableLiveData<List<MarkerOptions>> marcadores = new MutableLiveData<>();

    public UbicacionViewModel(@NonNull Application application) {
        super(application);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(application.getApplicationContext());
    }

    public LiveData<Location> getUbicacionActual() {
        return ubicacionActual;
    }

    public LiveData<List<MarkerOptions>> getMarcadores() {
        return marcadores;
    }

    public void actualizarUbicacion() {
        if (ContextCompat.checkSelfPermission(getApplication().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
                if (location != null) {
                    ubicacionActual.setValue(location);
                    actualizarMarcadores(location);
                }
            });
        }
    }

    private void actualizarMarcadores(Location location) {
        List<MarkerOptions> listaMarcadores = new ArrayList<>();

        LatLng mercado1 = new LatLng(-33.1862969, -66.3230453); // Coordenadas del mercado 1
        listaMarcadores.add(new MarkerOptions().position(mercado1).title("Mercado 1"));

        LatLng mercado2 = new LatLng(-33.1854686, -66.3224754); // Coordenadas del mercado 2
        listaMarcadores.add(new MarkerOptions().position(mercado2).title("Mercado 2"));

        // Agregar más marcadores según la ubicación actual
        if (location != null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            listaMarcadores.add(new MarkerOptions().position(latLng).title("Mi ubicación"));
        }

        marcadores.setValue(listaMarcadores);
    }
}

