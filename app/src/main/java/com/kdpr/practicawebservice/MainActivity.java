package com.kdpr.practicawebservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kdpr.practicawebservice.WebServices.Asynchtask;
import com.kdpr.practicawebservice.WebServices.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View view){
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws = new WebService ("https://gorest.co.in/public/v1/users", datos,
                MainActivity. this, MainActivity. this) ;
        ws.execute ("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtListausuario= findViewById(R.id.txtListaWeb);
        String list = "";
        JSONObject objecjson = new JSONObject(result);
        JSONArray JSONLista = objecjson.getJSONArray("data");
        for(int i =0; i < JSONLista.length();i++){
            JSONObject mostrarusuario = JSONLista.getJSONObject(i);
            list = list +
                    "user: " + mostrarusuario.getString("name").toString() +
                    " " + mostrarusuario.getString("email").toString() + "\n";
        }
        txtListausuario.setText(list);
    }
}