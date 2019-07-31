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
import com.example.bantumei.Model.SensorTanahModel;
import com.example.bantumei.Pojo.Sensor;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class DataTanah extends Fragment implements SensorTanahModel.SensorTanahListener {


    private View view;
    private ListView dataTanah;
    ArrayList<String> dataSenseorString = new ArrayList<String>();
    SensorTanahModel model = new SensorTanahModel();
    String key = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        key = bundle.getString("id","tanah");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_data_tanah, container, false);
        dataTanah = view.findViewById(R.id.lv_data_tanah);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        model.getDataSensor(this,getActivity());
    }



    @Override
    public void onSukses(Sensor[] result) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

        switch (key){
            case "temperatur":
                for (int i = 0; i < result.length; i++) {
                    dataSenseorString.add("Suhu"+ formatter.format(result[i].timeStamp)+" : "+result[i].Temperature.toString());
                }
                break;
            case "tanah":
                for (int i = 0; i < result.length; i++) {
                    dataSenseorString.add("Kelembapan Tanah "+ formatter.format(result[i].timeStamp)+"  : "+result[i].SoilMoisture.toString());
                }
                break;
            case "angin":
                for (int i = 0; i < result.length; i++) {
                    dataSenseorString.add("Kelembapan Angin "+ formatter.format(result[i].timeStamp)+"  : "+result[i].Humidity.toString());
                }
                break;

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,dataSenseorString);
        dataTanah.setAdapter(adapter);

    }

    @Override
    public void onEror(VolleyError error) {

    }
}
