package cl.devnode.todolist.MongoConn;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import cl.devnode.todolist.MainActivity;

/**
 * Created by Eduardo on 22-01-2015.
 */
public class PostAsyncTask extends AsyncTask<TaskModel, Void, Boolean> {

    private Context context;
    public PostAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(TaskModel... arg0) {
        try {
            TaskModel task = arg0[0];

            QueryBuilder qb = new QueryBuilder();

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost request = new HttpPost(qb.buildTasksSaveURL());
            StringEntity reqParams = new StringEntity(qb.createTask(task));
            request.addHeader("content-type", "application/json");
            request.setEntity(reqParams);
            HttpResponse response = httpClient.execute(request);

            if(response.getStatusLine().getStatusCode()<205)
            {
                return true;
            }
            else
            {
                return false;
            }

        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        String infoText = "Task Guardada!";
        if(!aBoolean) {
            infoText = "Hubo un problema guardando la task";
        }
        Toast.makeText(context, infoText, Toast.LENGTH_SHORT).show();
    }
}
