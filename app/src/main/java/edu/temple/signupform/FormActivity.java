package edu.temple.signupform;

import androidx.appcompat.app.AppCompatActivity;
import java.lang.String;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Logic for Save Button
    public void saveButton(View view)
    {
        //Variables for checks
        boolean goodToSave = true;
        EditText currBox;
        String input, name = "", password="";
        TextView errorBox;

        //Check if Name is Filled Out
        currBox = (EditText)findViewById(R.id.nameInput);
        errorBox = (TextView)findViewById(R.id.nameError);
        input = currBox.getText().toString();
        if(input.equals("")) //Field is empty
        {
            errorBox.setText("Field Cannot Be Blank.");
            goodToSave = false;
        }
        else //Field is not empty
        {
            name = input;
            errorBox.setText("");
        }

        //Check if Email is Filled Out and Valid
        currBox = (EditText)findViewById(R.id.emailInput);
        errorBox = (TextView)findViewById(R.id.emailError);
        input = currBox.getText().toString();
        if(input.equals("")) //Field is empty
        {
            errorBox.setText("Field Cannot Be Blank.");
            goodToSave = false;
        }
        else //Field is not empty
        {
            //Check if Email is Valid Format
            if(android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches())
                errorBox.setText("");
            else
            {
                goodToSave = false;
                errorBox.setText("Input is not valid email format.");
            }
        }

        //Check if Password is Filled Out
        currBox = (EditText)findViewById(R.id.passwordInput);
        errorBox = (TextView)findViewById(R.id.passwordError);
        input = currBox.getText().toString();
        if(input.equals("")) //Field is empty
        {
            errorBox.setText("Field Cannot Be Blank.");
            goodToSave = false;
        }
        else //Field is not empty
        {
            errorBox.setText("");
            password = input;
        }

        //Check if Password Confirmation is Filled Out and Matches
        currBox = (EditText)findViewById(R.id.passwordConfirm);
        errorBox = (TextView)findViewById(R.id.confirmError);
        input = currBox.getText().toString();
        if(input.equals("")) //Field is empty
        {
            errorBox.setText("Field Cannot Be Blank.");
            goodToSave = false;
        }
        else //Field is not empty
        {
            //Check if passwords match
            if(input.equals(password))
                errorBox.setText("");
            else
            {
                goodToSave = false;
                errorBox.setText("Passwords do not match.");
            }
        }

        //If we pass all checks, we get a toast message
        if(goodToSave)
        {
            String output = "Welcome, ";
            output += name;
            output += ", to the SignUpForm App.";

            //This code is more or less directly taken from the Android Developer Guide
            //https://developer.android.com/guide/topics/ui/notifiers/toasts
            Context context = getApplicationContext();
            CharSequence text = output;
            int duration = Toast.LENGTH_LONG;

            //Hide the keyboard
            //This was copied from a stackOverflow answer:
            //https://stackoverflow.com/questions/1109022/how-do-you-close-hide-the-android-soft-keyboard-using-java
            //User: Reto Meier
            View v = this.getCurrentFocus();
            if (v != null)
            {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}