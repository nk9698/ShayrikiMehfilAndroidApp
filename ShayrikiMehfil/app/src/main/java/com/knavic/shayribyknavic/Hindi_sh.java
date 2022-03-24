package com.knavic.shayribyknavic;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Hindi_sh extends Fragment {
    private DBManager dbManager;

    private RecyclerView recyclerView;
    private ArrayList<String> joke_id, joke_title, joke_full;
    private TextView textView;
    private AdapterHindi customAdapter;

    private static View root=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        if(root==null){
            root = inflater.inflate(R.layout.hindi_sh, container, false);
            dbManager = new DBManager(getActivity());
            dbManager.open();
            //getData();
            joke_id = new ArrayList<>();
            joke_title = new ArrayList<>();
            joke_full = new ArrayList<>();
            textView =(TextView)root.findViewById(R.id.hindi_empty);
            recyclerView =(RecyclerView)root.findViewById(R.id.hindi_recylerview);
            getData1();

        }else {

        }


        return root;
    }
    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://knavictools.com/celiapps/shayriapp/fetch_hindi_shayri.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                dbManager.hindi_deleteall();
                for (int i = 0 ; i <= response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        dbManager.hindi_insert(jsonObject.getInt("id"),jsonObject.getString("title"),jsonObject.getString("jokes"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                storeDataInArrays();
                customAdapter = new AdapterHindi(getActivity(),getActivity().getApplicationContext(),joke_id,joke_title);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(customAdapter);

                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
    }

    private void storeDataInArrays(){
        Cursor cursor = dbManager.hindi_fetch();
        if(cursor.getCount() == 0){

            textView.setVisibility(View.VISIBLE);
        }else{
            cursor.moveToLast();
            do{
                joke_id.add(cursor.getString(0));
                joke_title.add(cursor.getString(1));
                joke_full.add(cursor.getString(2));

            } while (cursor.moveToPrevious());


            textView.setVisibility(View.GONE);
        }
    }
    private void getData1() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://knavictools.com/celiapps/shayriapp/fetch_id_hindi.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id12 = jsonObject.getInt("id");
                        int id13 = dbManager.hindi_getid();
                        if(id12 > id13) {
                            getData();
                        }
                        else {

                            storeDataInArrays();
                            customAdapter = new AdapterHindi(getActivity(),getActivity().getApplicationContext(),joke_id,joke_title);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setAdapter(customAdapter);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }

                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
    }
}
