package com.example.bantumei.Model;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bantumei.Pojo.Sensor;
import com.example.bantumei.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SensorTanahModel {

    public void getDataSensor(final SensorTanahListener listener, Context context){
        String url = "http://sensor.bbppketindan.info/webservice2.php";
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ArrayList<Sensor> dataSenseor = new ArrayList<Sensor>();
                    JSONArray array = new JSONArray(response);
                    for(int i=0;i<array.length();i++){
                        String tanah = array.getJSONObject(i).getString("SoilMoisture");
                        String suhu= array.getJSONObject(i).getString("Temperature");
                        String kecAngin = array.getJSONObject(i).getString("Humidity");
                        String rawDate = array.getJSONObject(i).getString("Timestamp");
//                        2019-07-24 11:04:25
                        Date tanggal = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss").parse(rawDate);
                        Sensor dataBaru = new Sensor(tanah,suhu,kecAngin,tanggal);
                        dataSenseor.add(dataBaru);
                    }

                    listener.onSukses(dataSenseor.toArray(new Sensor[dataSenseor.size()]));
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onEror(error);
            }
        });
        queue.add(request);
    }


    public interface SensorTanahListener {
        void onSukses(Sensor[] result);
        void onEror(VolleyError error);
    }
}
