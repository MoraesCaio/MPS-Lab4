package business.model;

import business.control.notification.Observer;
import business.control.notification.Subject;
import business.model.tree.GenealogicalTree;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable, Observer
{
    private String name;
    private String password;
    private List<Subject> subjects;
    GenealogicalTree genealogicalTree;

    public User(String name, String password, GenealogicalTree genealogicalTree)
    {
        this.name = name;
        this.password = password;
        this.genealogicalTree = genealogicalTree;
        this.subjects = new ArrayList<>();
    }

    public User(String name, String password)
    {
        this(name, password, new GenealogicalTree());
    }

    public User()
    {
        this("", "");
    }

    public String getName()
    {
        return name;
    }

    public String getPassword()
    {
        return password;
    }

    public GenealogicalTree getGenealogicalTree()
    {
        return genealogicalTree;
    }

    public void addSubject(Subject subject)
    {
        if (!subjects.contains(subject))
        {
            subjects.add(subject);
            subject.add(this);
        }
    }

    public void removeSubject(Subject subject)
    {
        if (subjects.contains(subject))
        {
            subjects.remove(subject);
            subject.remove(this);
        }
    }

    public void setSubjects(List<Subject> subjects)
    {
        for (Subject subject : this.subjects)
        {
            removeSubject(subject);
        }
        for (Subject subject : subjects)
        {
            addSubject(subject);
        }
    }

    @Override
    public void update(String updateMessage)
    {
        System.out.println("Sending notification to user (" + this + ")\nUpdate message: " + updateMessage);
    }

    public boolean equals(User user)
    {
        return user.getName().equals(name) && user.getPassword().equals(password);
    }

    @Override
    public String toString()
    {
        return "NAME: " + name + " PASSWORD: " + password;
    }
}
