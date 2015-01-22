package cl.devnode.todolist.MongoConn;

/**
 * Created by Eduardo on 21-01-2015.
 */
public class QueryBuilder {
    /**
     * Specify your database name here
     * @return
     */
    public String getDatabaseName() {
        return "android-todolist";
    }

    /**
     * Specify your MongoLab API here
     * @return
     */
    public String getApiKey() {
        return "XXX";
    }

    /**
     * This constructs the URL that allows you to manage your database,
     * collections and documents
     * @return
     */
    public String getBaseUrl()
    {
        return "https://api.mongolab.com/api/1/databases/"+getDatabaseName()+"/collections/";
    }

    /**
     * Completes the formating of your URL and adds your API key at the end
     * @return
     */
    public String docApiKeyUrl()
    {
        return "?apiKey="+getApiKey();
    }

    /**
     * Returns the docs101 collection
     * @return
     */
    public String documentRequest()
    {
        return "android-todolist";
    }

    /**
     * Builds a complete URL using the methods specified above
     * @return
     */
    public String buildContactsSaveURL()
    {
        return getBaseUrl()+documentRequest()+docApiKeyUrl();
    }

    /**
     * Builds a complete URL using the methods specified above
     * @return
     */
    public String collectionsURL()
    {
        return getBaseUrl()+docApiKeyUrl();
    }

}
