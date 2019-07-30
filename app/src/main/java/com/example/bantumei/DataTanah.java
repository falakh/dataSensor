package com.example.bantumei;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bantumei.Pojo.Sensor;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class DataTanah extends Fragment {


    private View view;
    private ListView dataTanah;


    public DataTanah() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_data_tanah, container, false);
        getDataSensor();
        dataTanah = view.findViewById(R.id.lv_data_tanah);
        getDataSensor();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDataSensor();
    }

    void getDataSensor() {
        String url = "http://sensor.bbppketindan.info/webservice2.php";
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ArrayList<Sensor> dataSenseor = new ArrayList<Sensor>();
                    ArrayList<String> dataSenseorString = new ArrayList<String>();
                    JSONArray array = new JSONArray(response);
                    for(int i=0;i<array.length();i++){
                        String tanah = array.getJSONObject(i).getString("SoilMoisture");
                        String suhu= array.getJSONObject(i).getString("Temperature");
                        String kecAngin = array.getJSONObject(i).getString("Humidity");
                        Sensor dataBaru = new Sensor(tanah,suhu,kecAngin);
                        dataSenseor.add(dataBaru);
                        dataSenseorString.add(dataBaru.toString());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,dataSenseorString);
                    dataTanah.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("EROR", error.getMessage());
            }
        });
        queue.add(request);
    }


}
