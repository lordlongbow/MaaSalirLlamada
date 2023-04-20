package usuario.example.tpanexoa.ui.llamar;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LlamarViewModel extends AndroidViewModel {

    private MutableLiveData<String> numeroTelefonico = new MutableLiveData<>();
    private MutableLiveData<Boolean> llamadaRealizada = new MutableLiveData<>();
    private MutableLiveData<String> mensajeError = new MutableLiveData<>();

    public LlamarViewModel(@NonNull Application application) {
        super(application);
    }

    public void hacerLlamada(String numero, Activity activity) {
        if (numero == null || numero.trim().isEmpty()) {
            mensajeError.setValue("Por favor ingrese un número telefónico válido");
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + numero));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Agregar esta línea
            llamadaRealizada.setValue(true);
            try {
                if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, LlamarFragment.REQUEST_CALL_PHONE_PERMISSION);
                } else {
                    activity.startActivity(intent);
                }
            } catch (SecurityException e) {
                mensajeError.setValue("Permiso denegado para hacer una llamada telefónica");
            }
        }
    }

    public LiveData<Boolean> getLlamadaRealizada() {
        return llamadaRealizada;
    }

    public LiveData<String> getMensajeError() {
        return mensajeError;
    }

    public void setNumeroTelefonico(String numero) {
        numeroTelefonico.setValue(numero);
    }

    public LiveData<String> getNumeroTelefonico() {
        return numeroTelefonico;
    }
}
