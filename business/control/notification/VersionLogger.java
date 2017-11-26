package business.control.notification;

import java.io.Serializable;

/**
 * Created by caiomoraes on 26/11/17.
 */
public class VersionLogger extends Subject implements Serializable
{
    private String version;
    private static VersionLogger instance;

    private VersionLogger()
    {
        this.updateMessage = "Version has changed to ";
    }

    public static synchronized VersionLogger getInstance()
    {
        if (instance == null)
        {
            instance = new VersionLogger();
        }
        return instance;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
        updateObservers(updateMessage + version);
    }
}
