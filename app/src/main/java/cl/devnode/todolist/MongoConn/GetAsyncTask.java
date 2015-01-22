package cl.devnode.todolist.MongoConn;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Eduardo on 21-01-2015.
 */
public class GetAsyncTask extends AsyncTask<Void, Void, String> {

    private TextView text;

    public GetAsyncTask(TextView target) {
        text = target;
    }

    @Override
    protected String doInBackground(Void... params) {
        BufferedReader in = null;
        try
        {
            QueryBuilder qb = new QueryBuilder();

            HttpClient httpClient = new DefaultHttpClient();
            HttpGet request = new HttpGet(qb.collectionsURL());
            HttpResponse response = httpClient.execute(request);

            in = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            String line = in.readLine();

            Log.d("log_tag", line);

            return line;

        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection "+e.toString());
            return e.toString();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        text.setText(result);
    }
}
