package business.control.notification;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caiomoraes on 26/11/17.
 */
public abstract class Subject
{
    protected List<Observer> observers = new ArrayList<>();
    protected String updateMessage;

    public void add(Observer observer)
    {
        observers.add(observer);
    }

    public void remove(Observer observer)
    {
        observers.remove(observer);
    }

    public void updateObservers(String updateMessage)
    {
        for (Observer observer : observers)
        {
            observer.update(updateMessage);
        }
    }
}
