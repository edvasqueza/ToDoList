package cl.devnode.todolist.MongoConn;

/**
 * Created by Eduardo on 21-01-2015.
 */
public class QueryBuilder {

    /**
     * Specify your database name here
     *
     * @return
     */
    public String getDatabaseName() {
        return "android-todolist";
    }

    /**
     * Returns the docs101 collection
     *
     * @return
     */
    public String getCollectionName() {
        return "tasks";
    }

    /**
     * Specify your MongoLab API here
     *
     * @return
     */
    public String getApiKey() {
        return "XXX";
    }

    /**
     * This constructs the URL that allows you to manage your database,
     * collections and documents
     *
     * @return
     */
    public String getBaseUrl() {
        return "https://api.mongolab.com/api/1/databases/" + getDatabaseName() + "/collections/";
    }

    /**
     * Completes the formating of your URL and adds your API key at the end
     *
     * @return
     */
    public String docApiKeyUrl() {
        return "?apiKey=" + getApiKey();
    }

    /**
     * Builds a complete URL using the methods specified above
     *
     * @return
     */
    public String buildTasksSaveURL() {
        return getBaseUrl() + getCollectionName() + docApiKeyUrl();
    }

    /**
     * Formats the contact details for MongoHLab Posting
     * @param task: Details of the person
     * @return
     */
    public String createTask(TaskModel task)
    {
        return String
                .format("{\"text\": \"%s\", " + "\"done\": \"%s\"}",
                 task.text, task.done);
    }

}
