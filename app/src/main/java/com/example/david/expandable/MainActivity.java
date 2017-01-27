package com.example.david.expandable;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String IP_Server = "iesayala.ddns.net/deividjg";
    private String url_consulta = "http://"+IP_Server+"/php.php";
    private JSONArray jSONArray;
    private JSONObject jsonObject;
    private DevuelveJSON devuelveJSON;
    private ArrayList<Album> arrayAlbumes;

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    ArrayList<String> cabeceras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        devuelveJSON = new DevuelveJSON();
        new ListaAlbum().execute();

    }

    class ListaAlbum extends AsyncTask<String, String, JSONArray> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Cargando...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONArray doInBackground(String... args) {
            try {
                HashMap<String, String> parametrosPost = new HashMap<>();
                parametrosPost.put("ins_sql","Select * from ARTISTAS, ALBUMES WHERE ARTISTAS.ID_ARTISTA = ALBUMES.ID_ARTISTA_FK");
                jSONArray = devuelveJSON.sendRequest(url_consulta, parametrosPost);
                if (jSONArray != null) {
                    return jSONArray;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(JSONArray json) {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
            if (json != null) {
                Toast.makeText(MainActivity.this, json.toString(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "JSON Array nulo", Toast.LENGTH_LONG).show();
            }
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
            if (json != null) {
                arrayAlbumes = new ArrayList<Album>();
                for (int i = 0; i < json.length(); i++) {
                    try {
                        jsonObject = json.getJSONObject(i);
                        Album album = new Album();
                        album.setArtista(jsonObject.getString("NOMBRE"));
                        album.setNombre(jsonObject.getString("ALBUM"));
                        album.setAño(jsonObject.getInt("YEAR"));
                        album.setPortada(jsonObject.getString("PORTADA"));
                        arrayAlbumes.add(album);
                        System.out.println(album.getArtista());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                cabeceras = new ArrayList<String>();
                expandableListDetail = new HashMap<String, List<String>>();
                for(int i=0; i<arrayAlbumes.size(); i++){
                    if(expandableListDetail.containsKey(arrayAlbumes.get(i).getArtista())){
                        expandableListDetail.get(arrayAlbumes.get(i).getArtista()).add(arrayAlbumes.get(i).getNombre());
                    }else{
                        cabeceras.add(arrayAlbumes.get(i).getArtista());
                        expandableListDetail.put(arrayAlbumes.get(i).getArtista(), new ArrayList<String>());
                        expandableListDetail.get(arrayAlbumes.get(i).getArtista()).add(arrayAlbumes.get(i).getNombre());
                    }

                }
                rellenaExpandable();

            } else {
                Toast.makeText(MainActivity.this, "JSON Array nulo", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void rellenaExpandable(){
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListAdapter = new Adaptador(this, cabeceras, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);

    }
}

