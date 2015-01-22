package cl.devnode.todolist.MongoConn;

/**
 * Created by Eduardo on 21-01-2015.
 */
public class TaskModel {

    public String doc_id;
    public String text;
    public Boolean done;

    public TaskModel() {
    }

    public TaskModel(String text) {
        this.text = text;
        this.done = false;
        this.doc_id = null;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "TaskModel [text=" + text + ", done=" + done.toString()+ "]";
    }
}
