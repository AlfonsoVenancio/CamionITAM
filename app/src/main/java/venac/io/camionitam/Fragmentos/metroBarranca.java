package venac.io.camionitam.Fragmentos;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import venac.io.camionitam.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class metroBarranca extends Fragment implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap map;
    private View v;
    private LatLng coordenadasITAM, coordenadasMetro;

    public metroBarranca() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_metro_barranca, container, false);
        coordenadasITAM = new LatLng(19.3441391,-99.2007026);
        coordenadasMetro = new LatLng(19.36147807312731,-99.18954849243165);
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) v.findViewById(R.id.mapViewBarranca);
        if(mapView!= null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        Bitmap marcadorMapaITAM = escalaBitmap(R.drawable.ic_map_itam,200,200);
        Bitmap marcadorMapaMetro =  escalaBitmap(R.drawable.ic_map_metro,100,100);
        map.addMarker(new MarkerOptions().position(coordenadasITAM).title("ITAM").icon(BitmapDescriptorFactory.fromBitmap(marcadorMapaITAM)));
        map.addMarker(new MarkerOptions().position(coordenadasMetro).title("Metro Barranca del Muerto").icon(BitmapDescriptorFactory.fromBitmap(marcadorMapaMetro)));
        CameraPosition camera = new CameraPosition.Builder().target(coordenadasITAM).zoom(15).build(); //Este se debe reemplazar por la posicion actual del camion
        map.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
        LatLngBounds limites = new LatLngBounds(coordenadasITAM,coordenadasMetro);
        map.setLatLngBoundsForCameraTarget(limites);

        map.setMinZoomPreference(14);
    }

    /*
    Escala el bitmap para mostrar un marcador mas grande
     */
    protected Bitmap escalaBitmap(int dirMemoria, int altura, int anchura){
        int height = altura;
        int width = anchura;
        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(dirMemoria);
        Bitmap b=bitmapdraw.getBitmap();
        return Bitmap.createScaledBitmap(b, width, height, false);
    }
}
