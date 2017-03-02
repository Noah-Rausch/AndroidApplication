package com.example.noahr.photoapp.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.noahr.photoapp.Domain.User;
import com.example.noahr.photoapp.R;
import com.example.noahr.photoapp.Services.LoginNetworkLayer;

// Activity that deals with logging the user in and registering them.  This involves sending their
// input to the server and accepting a response.
public class LoginAcc extends AppCompatActivity {
    // UI elements; Buttons and EditText fields.
    static private Button registerBT;
    private Button loginBT;
    static private EditText usernameET;
    static private EditText emailET;
    static private EditText passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acc);
        registerBT = (Button) findViewById(R.id.registerBTXml);
        loginBT = (Button) findViewById(R.id.loginBTXml);
        usernameET = (EditText) findViewById(R.id.usernameETXml);
        emailET = (EditText) findViewById(R.id.emailETXml);
        passwordET = (EditText) findViewById(R.id.passwordETXml);

        // When the user clicks "register", take the input from the UI and start a "loginTask" that
        // will connect to the server to verify what the user inputted.
        registerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameET.getText().toString();
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                // Test to see if all fields have info entered into them.
                if(username.equalsIgnoreCase("")|| email.equalsIgnoreCase("") || password.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "No blank fields", Toast.LENGTH_LONG).show();
                }
                else {
                    // Create a user with the given info, and connect to the server with the
                    // LoginNetwork object.
                    User user = new User(username, email, password);
                    LoginNetworkLayer loginNetworkLayer = new LoginNetworkLayer(user, getApplicationContext());
                    loginNetworkLayer.createUser();
                }
            }
        });


        // When the user clicks "login", it is assumed they have an account, and we just want to verify it
        // in the database, and that the password matches.
        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameET.getText().toString();
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                // Test to see if all fields have info entered into them.
                if(username.equalsIgnoreCase("")|| email.equalsIgnoreCase("") || password.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(), "No blank fields", Toast.LENGTH_LONG).show();
                }
                else {
                    // Log the user in with the given info, and connect to the server with the
                    // LoginNetwork object..
                    User user = new User(username, email, password);
                    LoginNetworkLayer loginNetworkLayer = new LoginNetworkLayer(user, getApplicationContext());
                    loginNetworkLayer.loginUser();
                }
            }
        });
    }
}
