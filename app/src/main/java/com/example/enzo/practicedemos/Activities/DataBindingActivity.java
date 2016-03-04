package com.example.enzo.practicedemos.Activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.enzo.practicedemos.Core.User_Binding;
import com.example.enzo.practicedemos.R;
import com.example.enzo.practicedemos.databinding.DataBindingActivityBinding;

public class DataBindingActivity extends AppCompatActivity
{

    private Button btn_change_name;
    private User_Binding user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        DataBindingActivityBinding binding = DataBindingUtil.setContentView(this, R.layout
                .data_binding_activity);

        // If you are using data binding items inside a ListView or RecyclerView adapter, you may
        // prefer to use:
//        ListItemBinding binding = ListItemBinding.inflate(layoutInflater, viewGroup, false);
////or
//        ListItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item,
// viewGroup, false);


        user = new User_Binding("Yuhao", "ZENG");
        binding.setUser(user);

        btn_change_name = (Button) findViewById(R.id.btn_change_name);
        btn_change_name.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                user.setFirstName("Enzo");
                user.setLastName("Ikeda");
                Toast.makeText(DataBindingActivity.this, user.getFirstName() + " . " + user
                        .getLastName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}