package business.model;

import business.model.tree.GenealogicalTree;
import business.model.tree.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caiomoraes on 22/11/17.
 */
public class User
{
    String name;
    String password;
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
        if (!user.getName().equals(name))
        {
            return false;
        }
        if (!user.getPassword().equals(password))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "NAME: " + name + " PASSWORD: " + password;
    }
}
