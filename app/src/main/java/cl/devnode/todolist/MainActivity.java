package cl.devnode.todolist;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import cl.devnode.todolist.MongoConn.GetAsyncTask;
import cl.devnode.todolist.MongoConn.TaskModel;


public class MainActivity extends ActionBarActivity {
    ArrayList<TaskModel> taskList = new ArrayList<TaskModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView info = (TextView) findViewById(R.id.info);
        ListView listView = (ListView) findViewById(R.id.list);
        GetAsyncTask tsk = new GetAsyncTask(info);
        try {
            taskList = tsk.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        TaskAdapter adapter = new TaskAdapter(this, taskList);
        listView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
