package com.sripar.bankingnotifications;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private class DownloadTask extends AsyncTask<String, Long, String> {
        protected String doInBackground(String... urls) {
            try {
                HttpRequest request =  HttpRequest.get(urls[0]);
                String file = null;
                if (request.ok()) {
                    file = request.body();
                }
                return file;
            } catch (HttpRequest.HttpRequestException exception) {
                return null;
            }
        }

        protected void onProgressUpdate(Long... progress) {
        }

        protected void onPostExecute(String file) {
            if (file != null) {
                Log.d("PING", file);
                try {
                    JSONArray obj = new JSONArray(file);
                    ArrayList<BankNotification> bN = new ArrayList<BankNotification>();
                    ArrayList<String> notificationName = new ArrayList<>();
                    for (int i = 0; i < obj.length(); i++) {
                        JSONObject js = obj.getJSONObject(i);
                        BankNotification bankNot = new BankNotification(js.getInt("id"), js.getString("title"), js.getString("description"));
                        Log.d("TEMP", bankNot.toString());
                        bN.add(bankNot);
                        notificationName.add(bankNot.getTitle());
                        ListView lv = (ListView) findViewById(R.id.listNotifications);
                        lv.setAdapter(new ArrayAdapter<String>(MainActivity.this, R.layout.list_notif, R.id.notification, notificationName));
                    }


                } catch(JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.d("MyApp", "Download failed");
            }
        }
    }


    public void getNot(View view) {
        new DownloadTask().execute("http://demo-knowtifix.herokuapp.com/DatabaseEntries");
    }
}
