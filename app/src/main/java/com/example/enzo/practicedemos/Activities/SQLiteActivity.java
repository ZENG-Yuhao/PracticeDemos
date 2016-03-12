package com.example.enzo.practicedemos.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.enzo.practicedemos.DB.UserDB.User;
import com.example.enzo.practicedemos.DB.UserDB.UserDbManager;
import com.example.enzo.practicedemos.R;

public class SQLiteActivity extends AppCompatActivity
{
    private EditText editxt_id, editxt_name, editxt_address, editxt_email, editxt_account, editxt_password;
    private Button btn_add_user, btn_get_user, btn_delete_user, btn_modify_password, btn_clear_all;
    private UserDbManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        editxt_id = (EditText) findViewById(R.id.editxt_id);
        editxt_name = (EditText) findViewById(R.id.editxt_name);
        editxt_address = (EditText) findViewById(R.id.editxt_address);
        editxt_email = (EditText) findViewById(R.id.editxt_email);
        editxt_account = (EditText) findViewById(R.id.editxt_account);
        editxt_password = (EditText) findViewById(R.id.editxt_password);

        btn_add_user = (Button) findViewById(R.id.btn_add_user);
        btn_add_user.setOnClickListener(new addUserOnClickListener());

        btn_get_user = (Button) findViewById(R.id.btn_get_user);
        btn_get_user.setOnClickListener(new getUserOnClickListener());

        btn_delete_user = (Button) findViewById(R.id.btn_delete_user);
        btn_delete_user.setOnClickListener(new deleteUserOnClickListener());

        btn_modify_password = (Button) findViewById(R.id.btn_modify_password);
        btn_modify_password.setOnClickListener(new modifyPasswordOnClickListener());

        btn_clear_all = (Button) findViewById(R.id.btn_clear_all);
        btn_clear_all.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                clearAllEditText();
            }
        });

        manager = new UserDbManager(this);
        manager.open();
    }


    public void clearAllEditText()
    {
        editxt_id.setText("");
        editxt_name.setText("");
        editxt_address.setText("");
        editxt_email.setText("");
        editxt_account.setText("");
        editxt_password.setText("");
    }

    private class addUserOnClickListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            String id = editxt_id.getText().toString();
            String name = editxt_name.getText().toString();
            String address = editxt_address.getText().toString();
            String email = editxt_email.getText().toString();
            String account = editxt_account.getText().toString();
            String password = editxt_password.getText().toString();

            User user = new User(id, name, address, email, account, password);
            long c = manager.addUser(user);
            Toast.makeText(SQLiteActivity.this, "--> " + c + "rows added.", Toast.LENGTH_SHORT).show();
        }
    }


    private class getUserOnClickListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            User user = manager.getUserByName(editxt_name.getText().toString());
            if (user == null)
            {
                Toast.makeText(SQLiteActivity.this, "User not found.", Toast.LENGTH_SHORT).show();
                return;
            }

            editxt_id.setText(user.getId());
            editxt_address.setText(user.getAddress());
            editxt_email.setText(user.getEmail());
            editxt_account.setText(user.getAccount());
            editxt_password.setText(user.getPassword());
            Toast.makeText(SQLiteActivity.this, "user got.", Toast.LENGTH_SHORT).show();
        }
    }


    private class deleteUserOnClickListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            int c = manager.deleteUserByName(editxt_name.getText().toString());
            Toast.makeText(SQLiteActivity.this, "--> " + c + "rows deleted.", Toast.LENGTH_SHORT).show();
        }
    }


    private class modifyPasswordOnClickListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            String newPassword = editxt_password.getText().toString();
            String name = editxt_name.getText().toString();
            int c = manager.modifyPassword(name, newPassword);
            Toast.makeText(SQLiteActivity.this, "--> " + c + "rows updated.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy()
    {
        manager.close();
        super.onDestroy();
    }
}
