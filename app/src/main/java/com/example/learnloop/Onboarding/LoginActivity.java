package com.example.learnloop.Onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnloop.Fragments.MainFragment;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private TextView txtView_register;
    private EditText editTxt_loginEmail, editTxt_loginPassword;
    private ImageView btn_google, btn_instagram, btn_facebook;
    private Button btn_login;

    //Variable to store inputs
    private String mEmail, mPassword, mName;

    //Google sign in variables
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    //Facebook sign in variables
    CallbackManager callbackManager;
    AccessToken accessToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
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

        txtView_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mEmail = editTxt_loginEmail.getText().toString();
                mPassword = editTxt_loginPassword.getText().toString();

                validateEmail();
                validatePassword();
                validateInput();


            }
        });

        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignIn();
            }
        });

        btn_instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void googleSignIn() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    private void validateInput() {

        if (!validateEmail() | !validatePassword())
            return;
        else
        {
            //TODO: Set up the conditions and firebase to cross check if the acc is in the database
        }

    }

    private boolean validatePassword() {

        //Regex pattern to require alphanumeric and special characters
        Pattern regexPassword = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
        Matcher matcher = regexPassword.matcher(mPassword);

        if (mPassword.isEmpty())
        {
            editTxt_loginPassword.setError("Required");
            return false;
        }
        else if (!matcher.matches())
        {
            editTxt_loginPassword.setError("Invalid password");
            return false;
        }
        else
            return true;
    }

    private boolean validateEmail() {

        if (mEmail.isEmpty())
        {
            editTxt_loginEmail.setError("Required");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(mEmail).matches())
        {
            editTxt_loginEmail.setError("Invalid Email");
            return false;
        }
        else
            return true;
    }

    private void initWidget() {

        // TextView
        txtView_register = findViewById(R.id.txtView_register);

        // EditText
        editTxt_loginEmail = findViewById(R.id.editTxt_loginEmail);
        editTxt_loginPassword = findViewById(R.id.editTxt_loginPassword);

        // Clickable ImageView -> Register with Google, Instagram and/or LinkedIn
        btn_google = findViewById(R.id.btn_google);
        btn_instagram = findViewById(R.id.btn_instagram);
        btn_facebook = findViewById(R.id.btn_facebook);

        // Button
        btn_login = findViewById(R.id.btn_login);
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

                /*
                Bundle bundle = new Bundle();
                bundle.putString("Name", mName);
                bundle.putString("Email", mEmail);

                MainFragment fragment = new MainFragment();
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = getSupportFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.bottom_nav_main, fragment);
                 */

                startActivity(new Intent(getApplicationContext(), MainActivity.class));


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