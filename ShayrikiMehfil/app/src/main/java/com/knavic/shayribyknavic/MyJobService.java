package com.knavic.shayribyknavic;

import android.app.ProgressDialog;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {
    private DBManager dbManager;
    public MyJobService() {
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        getData1();
        Toast.makeText(this,"Executed",Toast.LENGTH_LONG).show();
        Log.d("background","executed");
        /*
         * True - if your service needs to process
         * the work (on a separate thread).
         * False - if there's no more work to be done for this job.
         */
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {

        return false;
    }
    private void getData1() {


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://knavictools.com/celiapps/shayriapp/fetch_id_guj.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id12 = jsonObject.getInt("id");
                        int id13 = dbManager.getid();
                        if(id12 > id13) {
                            getData();
                        }
                        else {


                        }
                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);
    }
    private void getData() {


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://knavictools.com/celiapps/shayriapp/fetch_guj_shayri.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                dbManager.deleteall();
                for (int i = 0 ; i <= response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        dbManager.insert(jsonObject.getInt("id"),jsonObject.getString("title"),jsonObject.getString("jokes"));

                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);
    }
}