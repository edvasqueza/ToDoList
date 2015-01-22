package cl.devnode.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import cl.devnode.todolist.MongoConn.TaskModel;

/**
 * Created by Eduardo on 22-01-2015.
 */
public class TaskAdapter extends ArrayAdapter<TaskModel> {

    public TaskAdapter(Context context, ArrayList<TaskModel> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        TaskModel task = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tasklist, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.taskText);
        CheckBox tvHome = (CheckBox) convertView.findViewById(R.id.taskStatus);
        // Populate the data into the template view using the data object
        tvName.setText(task.text);
        tvHome.setChecked(task.done);
        // Return the completed view to render on screen
        return convertView;
    }
}
