package business.login;
import business.model.User;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RegisterManager
{
    private String registerFile;
    private static final String defaultRegisterFile = "Register.bin";

    public RegisterManager(String registerFile)
    {
        this.registerFile = registerFile;
    }

    public RegisterManager()
    {
        this(defaultRegisterFile);
    }

    public User signUp(String login, String password) throws Exception
    {
        User foundUser = null;
        List<User> users = load();
        if (login.length() >= 6)
        {
            if (password.length() >= 4)
            {
                foundUser = getUser(login, password);
                if (foundUser == null)
                {
                    users.add(new User(login, password));
                    save(users);
                }
            }
            else
            {
                System.out.println("Senha deve possuir pelo menos 4 caracteres.");
            }
        }
        else
        {
            System.out.println("Nome deve possuir pelo menos 6 caracteres.");
        }
        return foundUser;
    }

    public User login(String login, String password) throws Exception
    {
        List<User> users = load();

        for (User user : users)
        {
            if (user.getName().equals(login))
            {
                System.out.println("Usuário encontrado.");
                if (user.getPassword().equals(password))
                {
                    return user;
                }
                else
                {
                    System.out.println("Senha inválida. Tente novamente.");
                    return null;
                }
            }
        }

        System.out.println("Usuário não encontrado.");
        return null;
    }

    private void save(List<User> users) throws Exception
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
    private List<User> load() throws Exception
    {
        List<User> users = new ArrayList<>();
        try
        {
            FileInputStream fis = new FileInputStream(registerFile);
            ObjectInputStream in = new ObjectInputStream(fis);

            if (in.readObject() instanceof ArrayList)
            {
                users = (ArrayList<User>) in.readObject();
            }
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

    private User getUser(String login, String password) throws Exception
    {
        List<User> users = load();
        User currentUser = new User(login, password);
        User foundUser = null;
        for (User user : users)
        {
            if (currentUser.equals(user))
            {
                foundUser = user;
                break;
            }
        }
        return foundUser;
    }
}