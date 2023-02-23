package com.example.learnloop.Onboarding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
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
import android.widget.Toast;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Executor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private TextView txtView_register;
    private EditText editTxt_loginEmail, editTxt_loginPassword, editTxt_phoneNumber;
    private ImageView btn_google, btn_instagram, btn_facebook;
    private Button btn_login, btn_biometricLogin;

    //Variable to store inputs
    private String mEmail, mPassword, mPhoneNumber, mName;

    //Google sign in variables
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    //Facebook sign in variables
    CallbackManager callbackManager;
    AccessToken accessToken;

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://learnloop-1673224439925-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference databaseReference  = database.getReference().child("users");

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

        initUI();

        pageDirectories();
    }

    private void initUI() {
        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(LoginActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                //f there's any error that comes up while auth
                Toast.makeText(LoginActivity.this, "Error while using biometric login", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                //Authentication successful
                Toast.makeText(LoginActivity.this, "Authentication successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                //fail to authenticate
                Toast.makeText(LoginActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
            }
        });

        //Setup title, description on auth dialog
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Authentiction")
                .setSubtitle("Login using fingerprint or face")
                .setNegativeButtonText("Cancel")
                .build();
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

                mPhoneNumber = editTxt_phoneNumber.getText().toString();
                mEmail = editTxt_loginEmail.getText().toString();
                mPassword = editTxt_loginPassword.getText().toString();

                validatePhoneNumber();
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

        btn_biometricLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show auth dialog
                biometricPrompt.authenticate(promptInfo);
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

    private void googleSignIn() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    private void validateInput() {

        if (!validatePhoneNumber() | !validateEmail() | !validatePassword())
            return;
        else
        {
            //Authenticating with real time firebase database
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(mPhoneNumber))
                    {
                        // Name exist in firebase database
                        // now getting password of user from firebase data and match if with user entered password and username

                        final String getName = snapshot.child(mPhoneNumber).child("User's Information").child("Full Name").getValue(String.class);
                        final String getPhoneNumber = snapshot.child(mPhoneNumber).child("User's Information").child("Phone Number").getValue(String.class);
                        final String getEmail = snapshot.child(mPhoneNumber).child("User's Information").child("Email").getValue(String.class);
                        final String getPassword = snapshot.child(mPhoneNumber).child("User's Information").child("Password").getValue(String.class);

                        if ((getPassword.equals(mPassword)) && getPhoneNumber.equals(mPhoneNumber) && getEmail.equals(mEmail))
                        {
                            // Lead user to the Main Menu Page activity
                            Toast.makeText(LoginActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                            intent.putExtra("Name", getName);
                            intent.putExtra("Phone Number", mPhoneNumber);
                            intent.putExtra("Email", mEmail);
                            intent.putExtra("Password", mPassword);

                            startActivity(intent);

                            finish();
                        }
                        else
                            Toast.makeText(LoginActivity.this, "Log In unsuccessful, please check your password or username", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Log In unsuccessful, please check your mobile number", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
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
        editTxt_phoneNumber = findViewById(R.id.editTxt_phoneNumber);

        // Clickable ImageView -> Register with Google, Instagram and/or LinkedIn
        btn_google = findViewById(R.id.btn_google);
        btn_instagram = findViewById(R.id.btn_instagram);
        btn_facebook = findViewById(R.id.btn_facebook);

        // Button
        btn_login = findViewById(R.id.btn_login);
        btn_biometricLogin = findViewById(R.id.btn_biometricLogin);
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