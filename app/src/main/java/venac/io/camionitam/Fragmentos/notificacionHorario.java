package venac.io.camionitam.Fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import venac.io.camionitam.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class notificacionHorario extends Fragment {


    public notificacionHorario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notificacion_horario, container, false);

        return v;
    }

}
