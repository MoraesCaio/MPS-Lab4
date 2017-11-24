package business.model;

import business.model.tree.GenealogicalTree;
import business.model.tree.Member;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable
{
    private String name;
    private String password;
    GenealogicalTree genealogicalTree;

    public User(String name, String password, GenealogicalTree genealogicalTree)
    {
        this.name = name;
        this.password = password;
        this.genealogicalTree = genealogicalTree;
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
