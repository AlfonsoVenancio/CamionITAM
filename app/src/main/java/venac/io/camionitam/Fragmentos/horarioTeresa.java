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
public class horarioTeresa extends Fragment {


    public horarioTeresa() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_horario_teresa, container, false);
    }

}
