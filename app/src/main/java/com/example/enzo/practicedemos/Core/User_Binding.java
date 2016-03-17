package com.example.enzo.practicedemos.Core;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.enzo.practicedemos.BR;

/**
 * Created by Enzo(ZENG Yuhao) on 16/3/3.
 */
public class User_Binding extends BaseObservable
{
    private  String firstName;
    private  String lastName;
    public User_Binding(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    @Bindable
    public String getFirstName() {
        return this.firstName;
    }

    @Bindable
    public String getLastName() {
        return this.lastName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }
}
