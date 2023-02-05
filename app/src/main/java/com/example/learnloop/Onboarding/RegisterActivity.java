package com.example.learnloop.Onboarding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnloop.MainActivity;
import com.example.learnloop.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private TextView txtView_logIn;
    private EditText editTxt_name, editTxt_email, editTxt_password, editTxt_passwordConfirmation, editTxt_phoneNumber;
    private ImageView btn_google, btn_instagram, btn_facebook, imgView_learnLoop;
    private Button btn_getStarted;

    //String to store input data
    private String mName, mEmail, mPassword, mConfirmPassword, mPhoneNumber;

    //Google sign in variables
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    //Facebook sign in variables
    CallbackManager callbackManager;
    AccessToken accessToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        getSupportActionBar().hide();

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(getApplicationContext(), gso);

        callbackManager = CallbackManager.Factory.create();

        accessToken = AccessToken.getCurrentAccessToken();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Intent intent = new Intent(getApplicationContext(), AskInformationActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });


        initWidget();

        pageDirectories();

    }

    private void pageDirectories() {

        btn_getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mName = editTxt_name.getText().toString();
                mPhoneNumber = editTxt_phoneNumber.getText().toString();
                mEmail = editTxt_email.getText().toString();
                mPassword = editTxt_password.getText().toString();
                mConfirmPassword = editTxt_passwordConfirmation.getText().toString();

                validateName();
                validatePhoneNumber();
                validateEmail();
                validatePassword();
                validateConfirmPassword();
                validateInputs();
            }
        });

        txtView_logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signIn();

            }
        });

        btn_instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, AskInformationActivity.class);
                startActivity(intent);
            }
        });

        btn_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginManager.getInstance().logInWithReadPermissions(RegisterActivity.this, Arrays.asList("email"));


            }
        });

        imgView_learnLoop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    private boolean validatePhoneNumber() {

        if (mPhoneNumber.isEmpty())
        {
            editTxt_phoneNumber.setError("Required");
            return false;
        }
        else
            return true;
    }

    private void signIn() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    private void validateInputs() {

        if (!validateName() | !validatePhoneNumber() | !validateEmail() | !validatePassword() | !validateConfirmPassword())
        {
            return;
        }
        else
        {
            // Transfer data to the next page
            Intent intent = new Intent(getApplicationContext(), AskInformationActivity.class);
            intent.putExtra("Name", mName);
            intent.putExtra("Email", mEmail);
            intent.putExtra("Phone Number", mPhoneNumber);
            intent.putExtra("Password", mPassword);
            startActivity(intent);

        }
    }

    private boolean validateConfirmPassword() {

        if (mConfirmPassword.isEmpty())
        {
            editTxt_passwordConfirmation.setError("Required");
            return false;
        }
        else if (!mConfirmPassword.equals(mPassword))
        {
            editTxt_passwordConfirmation.setError("Your passwords do not match");
            return false;
        }
        else
            return true;
    }

    private boolean validatePassword() {

        //Regex pattern to require alphanumeric and special characters
        Pattern regexPassword = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
        Matcher matcher = regexPassword.matcher(mPassword);

        if (mPassword.isEmpty())
        {
            editTxt_password.setError("Required");
            return false;
        }
        else if (!matcher.matches())
        {
            editTxt_password.setError("Your password's not strong enough");
            return false;
        }
        else
            return true;
    }

    private boolean validateEmail() {

        if (mEmail.isEmpty())
        {
            editTxt_email.setError("Required");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(mEmail).matches())
        {
            editTxt_email.setError("Invalid Email Address");
            return false;
        }
        else
            return true;
    }

    private boolean validateName() {

        //Regex pattern to allow only alphabets
        Pattern regexName = Pattern.compile("^[a-zA-Z]+$");
        Matcher matcher = regexName.matcher(mName);

        if (mName.isEmpty())
        {
            editTxt_name.setError("Required");
            return false;
        }
        else if (!matcher.matches())
        {
            editTxt_name.setError("Invalid input");
            return false;
        }
        else
            return true;
    }

    private void initWidget() {

        // TextView
        txtView_logIn = findViewById(R.id.txtView_logIn);

        // EditText
        editTxt_name = findViewById(R.id.editTxt_name);
        editTxt_email = findViewById(R.id.editTxt_email);
        editTxt_password = findViewById(R.id.editTxt_password);
        editTxt_passwordConfirmation = findViewById(R.id.editTxt_passwordConfirmation);
        editTxt_phoneNumber = findViewById(R.id.editTxt_phoneNumber);

        // ImageView
        btn_google = findViewById(R.id.btn_google);
        btn_instagram = findViewById(R.id.btn_instagram);
        btn_facebook = findViewById(R.id.btn_facebook);
        imgView_learnLoop = findViewById(R.id.imgView_learnLoop);

        // Button
        btn_getStarted = findViewById(R.id.btn_getStarted);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);


                //Get visual value on the user
                GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
                mName = acct.getDisplayName();
                mEmail = acct.getEmail();

                Intent intent = new Intent(getApplicationContext(), AskInformationActivity.class);
                intent.putExtra("Name", mName);
                intent.putExtra("Email", mEmail);
                startActivity(intent);

                System.out.println(mName);
                System.out.println(mEmail);

            }catch (ApiException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            GraphRequest request = GraphRequest.newMeRequest(
                    accessToken,
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            // Application code

                            try {
                                mName = object.getString("name");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,link");
            request.setParameters(parameters);
            request.executeAsync();
        }
    }
}