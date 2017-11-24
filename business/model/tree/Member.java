package business.model.tree;

import java.io.Serializable;

public class Member implements Serializable
{
    /*PROPERTIES*/
    // true - male, false - female
    public static Boolean female = false, male = true;
    public Boolean gender;
    public String name;


    /*CONSTRUCTORS*/
    public Member(Boolean gender, String name)
    {
        this.gender = gender;
        this.name = name;
    }

    public Member(Boolean gender)
    {
        this(gender, "");
    }

    public Member()
    {
        this(true);
    }


    /*METHODS*/
    public Member copy()
    {
        return new Member(new Boolean(this.gender), new String(this.name));
    }

    public boolean equals(Member memberInfo)
    {
        return (this.name.equals(memberInfo.name) && this.gender.equals(memberInfo.gender));
    }

    @Override
    public String toString()
    {
        return "NAME: " + name + "\t\tGENDER: " + ((gender) ? "Male" : "Female");
    }
}
