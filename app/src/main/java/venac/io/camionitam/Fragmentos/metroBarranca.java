package venac.io.camionitam.Fragmentos;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import venac.io.camionitam.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class metroBarranca extends Fragment implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap map;
    private View v;
    private LatLng coordenadasITAM, coordenadasMetro;
    private BottomSheetBehavior bottomSheetBehavior;
    private ImageView iv_trigger;
    private CoordinatorLayout coordinatorLayout;

    public metroBarranca() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_metro_barranca, container, false);
        coordenadasITAM = new LatLng(19.3441391,-99.2007026);
        coordenadasMetro = new LatLng(19.36147807312731,-99.18954849243165);
        coordinatorLayout = (CoordinatorLayout) v.findViewById(R.id.coordinatorBarranca);
        init_persistent_bottomsheet();
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

        //------------------Inicia declaración de marcadores estaticos------------------------------
        map.addMarker(new MarkerOptions().position(coordenadasITAM).title("ITAM").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_map_itam)));
        map.addMarker(new MarkerOptions().position(coordenadasMetro).title("Metro Barranca del Muerto").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_map_metro)));


        //------------------Termina declaración de marcadores estaticos-----------------------------


        CameraPosition camera = new CameraPosition.Builder().target(coordenadasITAM).zoom(15).build(); //Este se debe reemplazar por la posicion actual del camion
        map.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
        LatLngBounds limites = new LatLngBounds(coordenadasITAM,coordenadasMetro);
        map.setLatLngBoundsForCameraTarget(limites);

        map.setMinZoomPreference(14);
        //Polyline linea = map.addPolyline(new PolylineOptions().add(coordenadasITAM,coordenadasMetro).width(5).color(Color.RED));

    }

    @Override
    public void onPause(){
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
        mapView.onResume();
    }

    public void onStop(){
        super.onStop();
        mapView.onStop();
    }

    public void init_persistent_bottomsheet() {
        final View persistentbottomSheet = coordinatorLayout.findViewById(R.id.bottomsheet);
        iv_trigger = persistentbottomSheet.findViewById(R.id.iv_fab);
        final BottomSheetBehavior behavior = BottomSheetBehavior.from(persistentbottomSheet);
        persistentbottomSheet.bringToFront();

        iv_trigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        if (behavior != null)
            behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    //showing the different states
                    switch (newState) {
                        case BottomSheetBehavior.STATE_HIDDEN:
                            break;
                        case BottomSheetBehavior.STATE_EXPANDED:
                            iv_trigger.setImageResource(R.drawable.ic_bottom_abajo);
                            break;
                        case BottomSheetBehavior.STATE_COLLAPSED:
                            iv_trigger.setImageResource(R.drawable.ic_bottom_arriba);
                            break;
                        case BottomSheetBehavior.STATE_DRAGGING:

                            break;
                        case BottomSheetBehavior.STATE_SETTLING:
                            break;
                    }
                }
                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                    // React to dragging events

                }
            });

    }
}
