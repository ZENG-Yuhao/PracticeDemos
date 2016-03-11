package com.example.enzo.practicedemos.DB.UserDB;

/**
 * Created by enzoz on 2016/3/11.
 */
public class User
{
    private int id;
    private String name, address, email, account, password;

    public User(int id, String name, String address, String email, String account, String password)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.account = account;
        this.password = password;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getAccount()
    {
        return account;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }

    public boolean isPasswordRight(String pw)
    {
        return pw.equals(password);
    }

}
