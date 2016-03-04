package com.example.enzo.practicedemos.Core;

/**
 * Created by Enzo(ZENG Yuhao) on 16/3/3.
 */
public class User_Binding
{
    private  String firstName;
    private  String lastName;
    public User_Binding(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }
}
