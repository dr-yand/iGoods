package client.igooods.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import client.igooods.MainActivity;
import client.igooods.R;

public class MapFragment extends Fragment implements View.OnClickListener {
    private Dialog mDeliveredDialog;
    private double[][]  coords = {
            {37.623241,55.763483},
            {37.623277,55.7621150},
            {37.620411,55.76214},
            {37.621597,55.759501},
    };

    private GoogleMap mMap;
    private Marker mStartMarker, mEndMarker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        int SomeInt = getArguments().getInt("someInt", 0);
//        String someTitle = getArguments().getString("someTitle", "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        initView(view);
        initDlg();
        initMap(view);
        return view;
    }

    private void initView(View view){
        view.findViewById(R.id.delivered).setOnClickListener(this);
    }

    private void initDlg() {
        mDeliveredDialog = new Dialog(getActivity(), R.style.DialogTheme);
        mDeliveredDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDeliveredDialog.setContentView(R.layout.dialog_delivered);
        mDeliveredDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        ((Button) mDeliveredDialog.findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeliveredDialog.dismiss();
            }
        });
        ((Button) mDeliveredDialog.findViewById(R.id.ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeliveredDialog.dismiss();
                ((MainActivity)getActivity()).showAmountFragment();
            }
        });
    }

    private void initMap(View view){
        /*mLocationManager = (LocationManager) getApplicationContext()
                .getSystemService(Context.LOCATION_SERVICE);

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                10 * 1000, 5, listener);*/

        mMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.maps)).getMap();

        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);

       /* mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                updateCurrentLocation(location.getLatitude(), location.getLongitude());
                mMap.setOnMyLocationChangeListener(null);
            }
        });*/

        try {
            PolylineOptions lineOptions =  new PolylineOptions();

            for (int i = 0; i < coords.length; i++) {

                LatLng position = new LatLng(coords[i][1], coords[i][0]);

                lineOptions.add(position);
                lineOptions.width(10);
                lineOptions.color(0xff6B9936);
            }

            mMap.addPolyline(lineOptions);

            BitmapDescriptor market = BitmapDescriptorFactory.fromResource(R.drawable.market_32);
            BitmapDescriptor home = BitmapDescriptorFactory.fromResource(R.drawable.home_32);

            MarkerOptions start = new MarkerOptions()
                    .position(new LatLng(coords[0][1], coords[0][0]))
                    .icon(market)
                    .title("Кораблестроителей, 24");
            MarkerOptions end = new MarkerOptions()
                    .position(new LatLng(coords[coords.length - 1][1], coords[coords.length - 1][0]))
                    .icon(home)
                    .title("Пушечная ул, 3");
            mStartMarker = mMap.addMarker(start);
            mEndMarker = mMap.addMarker(end);

            mStartMarker.showInfoWindow();

            LatLng latLng = new LatLng(coords[0][1], coords[0][0]);
            CameraPosition CITY =
                    new CameraPosition.Builder().target(latLng)
                            .zoom(15f)
                            .bearing(0)
                            .tilt(25)
                            .build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(CITY));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.delivered) {
            mDeliveredDialog.show();
        }
    }
}