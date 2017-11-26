package infra;

import business.model.User;
import business.model.tree.Member;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class UserDAOFile implements UserDAO
{

    private String registerFile;
    private static final String defaultRegisterFile = "Register.bin";

    private UserDAOFile(String registerFile)
    {
        this.registerFile = registerFile;
    }

    public UserDAOFile()
    {
        this(defaultRegisterFile);
    }

    public String getRegisterFile()
    {
        return registerFile;
    }

    public void saveUser(User user) throws Exception
    {
        ArrayList<User> users = loadUsers();
        users.add(user);

        saveUsers(users);
    }

    @Override
    public User getUser(String login) throws Exception
    {
        ArrayList<User> users = loadUsers();
        User foundUser = null;
        for (User user : users)
        {
            if (user.getName().equals(login))
            {
                foundUser = user;
                break;
            }
        }
        return foundUser;
    }

    @Override
    public void addMember(User user, Member member) throws Exception
    {
        ArrayList<User> users = loadUsers();
        User oldUser = null;

        for(User u : users)
        {
            if(u.getName().equals(user.getName())) oldUser = u;
        }

        if(oldUser == null) return;

        users.set(users.indexOf(oldUser), user);
        saveUsers(users);
    }

    @Override
    public void updateMember(User user, String gender, String name, String newGender, String newName) throws Exception
    {
        boolean g = false;
        if(newGender.equals("M")) g = true;
        addMember(user, new Member(g, name));
    }

    private void saveUsers(ArrayList<User> users) throws Exception
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(registerFile);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(users);

        }
        catch (IOException e)
        {
            throw new Exception("Erro ao salvar lista de usuários.");
        }
    }

    @SuppressWarnings("unchecked")
    private ArrayList<User> loadUsers() throws Exception
    {
        ArrayList<User> users = new ArrayList<User>();
        try
        {
            Path registerFilePath = Paths.get(registerFile);
            if (!Files.exists(registerFilePath))
            {
                Files.createFile(registerFilePath);
            }
            FileInputStream fis = new FileInputStream(registerFile);
            ObjectInputStream in = new ObjectInputStream(fis);


            users = (ArrayList<User>) in.readObject();

            return users;
        }
        //Empty file
        catch (EOFException eofEx)
        {
            return new ArrayList<User>();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
            throw new Exception("Erro ao carregar lista de usuários.");
        }
    }
}
