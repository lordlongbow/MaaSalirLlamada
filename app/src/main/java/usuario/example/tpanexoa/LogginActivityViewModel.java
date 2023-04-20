package usuario.example.tpanexoa;

import android.app.Application;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class LogginActivityViewModel extends AndroidViewModel {
    private List<Pair<String, String>> users;

    private MutableLiveData<Boolean> resultado=null;
    private MutableLiveData<Boolean> resultaoNegativo = null;

    private String usuario;
    private String contrasenia;

    public LogginActivityViewModel(@NonNull Application application) {
        super(application);
        users = new ArrayList<>();
        // Agregar algunos usuarios
        users.add(new Pair<>("A", "A"));
        users.add(new Pair<>("B", "B"));
        users.add(new Pair<>("C", "C"));
        users.add(new Pair<>("D", "D"));
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public LiveData<Boolean> getResultado(){
        if(resultado==null){
            this.resultado=new MutableLiveData<>();
        }
        return resultado;
    }
    public LiveData<Boolean> getResultadoNegativo(){
        if(resultaoNegativo==null){
            this.resultaoNegativo=new MutableLiveData<>();
        }
        return resultaoNegativo;
    }
  /*  public void Validado() {
        for (Pair<String, String> user : users) {
            if (usuario.equals(user.first) && contrasenia.equals(user.second)) {
                resultado.setValue(true);
            }
            else
            {
                resultaoNegativo.setValue(true);
            }
        }
    }*/
   public void validar(String userName, String password){

        if((userName.equals("A") && password.equals("A"))){
            resultado.setValue(true);
        }else{
            resultaoNegativo.setValue(true);
        }

    }
}


