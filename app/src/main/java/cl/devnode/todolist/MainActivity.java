package cl.devnode.todolist;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import cl.devnode.todolist.MongoConn.GetAsyncTask;
import cl.devnode.todolist.MongoConn.PostAsyncTask;
import cl.devnode.todolist.MongoConn.TaskModel;


public class MainActivity extends ActionBarActivity {
    public TaskAdapter adapter = null;
    ArrayList<TaskModel> taskList = new ArrayList<>();
    TextView taskText;
    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info = (TextView) findViewById(R.id.info);
        taskText = (TextView) findViewById(R.id.taskText);
        ListView listView = (ListView) findViewById(R.id.list);

        //Getting Tasks from MongoLab
        GetAsyncTask tsk = new GetAsyncTask();
        try {
            taskList = tsk.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        adapter = new TaskAdapter(this, taskList);
        listView.setAdapter(adapter);
    }

    public void createNewTask(View v) {
        TaskModel task = new TaskModel(taskText.getText().toString());
        PostAsyncTask tsk = new PostAsyncTask(getApplicationContext());
        try {
            Boolean isSaved = tsk.execute(task).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        taskList.add(task);
        taskText.setText("");

        runOnUiThread(new Runnable() {
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
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
