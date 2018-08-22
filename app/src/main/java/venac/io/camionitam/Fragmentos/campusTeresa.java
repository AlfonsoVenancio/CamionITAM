package venac.io.camionitam.Fragmentos;


import android.graphics.Bitmap;
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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import venac.io.camionitam.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class campusTeresa extends Fragment implements OnMapReadyCallback{

    private MapView mapView;
    private GoogleMap map;
    private View v;
    private LatLng coordenadasITAM, coordenadasCampus;
    private BottomSheetBehavior bottomSheetBehavior;
    private ImageView iv_trigger;
    private CoordinatorLayout coordinatorLayout;

    public campusTeresa() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_campus_teresa, container, false);
        coordenadasITAM = new LatLng(19.3441391,-99.2007026);
        coordenadasCampus = new LatLng(19.36147807312731,-99.18954849243165);
        coordinatorLayout = (CoordinatorLayout) v.findViewById(R.id.coordinatorTeresa);
        init_persistent_bottomsheet();
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) v.findViewById(R.id.mapViewTeresa);
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
        //Bitmap marcadorMapaITAM = escalaBitmap(R.drawable.ic_map_itam,200,200);
        //Bitmap marcadorMapaMetro =  escalaBitmap(R.drawable.ic_map_metro,100,100);
        map.addMarker(new MarkerOptions().position(coordenadasITAM).title("ITAM Rio Hondo").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_map_itam)));
        map.addMarker(new MarkerOptions().position(coordenadasCampus).title("ITAM Santa Teresa").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_map_itam)));


        //------------------Termina declaración de marcadores estaticos-----------------------------


        CameraPosition camera = new CameraPosition.Builder().target(coordenadasITAM).zoom(15).build(); //Este se debe reemplazar por la posicion actual del camion
        map.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
        LatLngBounds limites = new LatLngBounds(coordenadasITAM,coordenadasCampus);
        map.setLatLngBoundsForCameraTarget(limites);

        map.setMinZoomPreference(14);
        Polyline linea = map.addPolyline(new PolylineOptions().add(coordenadasITAM,coordenadasCampus).width(5).color(Color.RED));

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
