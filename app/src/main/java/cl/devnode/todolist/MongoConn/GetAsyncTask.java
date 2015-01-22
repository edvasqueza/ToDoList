package cl.devnode.todolist.MongoConn;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.mongodb.DBObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Eduardo on 21-01-2015.
 */
public class GetAsyncTask extends AsyncTask<Void, Void, ArrayList<TaskModel>> {

    private TextView text;

    public GetAsyncTask(TextView target) {
        text = target;
    }

    @Override
    protected ArrayList<TaskModel> doInBackground(Void... params) {
        BufferedReader in = null;
        ArrayList<TaskModel> taskList = new ArrayList<>();
        try
        {
            QueryBuilder qb = new QueryBuilder();

            HttpClient httpClient = new DefaultHttpClient();
            HttpGet request = new HttpGet(qb.buildTasksSaveURL());
            HttpResponse response = httpClient.execute(request);

            in = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            String server_output = in.readLine();

            String mongoarray = "{ \"artificial_basicdb_list\": "+server_output+"}";
            Object o = com.mongodb.util.JSON.parse(mongoarray);

            DBObject dbObj = (DBObject) o;
            ArrayList<DBObject> tasks = (ArrayList<DBObject>) dbObj.get("artificial_basicdb_list");

            for (DBObject userObj : tasks) {

                TaskModel temp = new TaskModel();
                temp.setText(userObj.get("text").toString());
                temp.setDone(Boolean.valueOf(userObj.get("done").toString()));
                DBObject id = (DBObject) userObj.get("_id");
                temp.setDoc_id(id.get("$oid").toString());
                taskList.add(temp);
            }

        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());
        }

        return taskList;
    }
}
