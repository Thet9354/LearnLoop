package com.example.learnloop.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnloop.AboutActivity;
import com.example.learnloop.CaptureAct;
import com.example.learnloop.MainActivity;
import com.example.learnloop.MapActiivty2;
import com.example.learnloop.Onboarding.AskInformationActivity;
import com.example.learnloop.Onboarding.LoginActivity;
import com.example.learnloop.Onboarding.RegisterActivity;
import com.example.learnloop.R;
import com.example.learnloop.SuccessAddActivty;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.io.ByteArrayOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddTransactionFragment extends Fragment {

    private RadioGroup rg_transactionFlow, rg_transactionPurpose;

    private EditText editTxt_title, editTxt_amount, editTxt_desc;

    private ImageView btn_uploadTransaction, btn_getLocation;

    private TextView txtView_location;

    private MaterialButton btn_recordDetails, btn_barCodeScanner;

    private static final int requestCamera1 = 12;

    private Context mContext;

    private String name, phoneNumber = "93542856", email, password;

    //Variable to store transaction details in
    private String mTransactionFlow, mTitle, mAmount, mPurpose, mDesc, mLocation;
    private Bitmap bitmap, defaultBitmap;
    private int mTransactionImg;

    private String  mLat, mLon;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://learnloop-1673224439925-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference databaseReference  = database.getReference().child("users");

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    StorageReference imagesRef = storageRef.child("transactions");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_transaction, container, false);

        mContext = getActivity();

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
    }

    private void findViews(View v) {

        //RadioGroup
        rg_transactionFlow = v.findViewById(R.id.rg_transactionFlow);
        rg_transactionPurpose = v.findViewById(R.id.rg_transactionPurpose);

        //EditText
        editTxt_title = v.findViewById(R.id.editTxt_title);
        editTxt_amount = v.findViewById(R.id.editTxt_amount);
        editTxt_desc = v.findViewById(R.id.editTxt_desc);

        //Imageview
        btn_uploadTransaction = v.findViewById(R.id.btn_uploadTransaction);
        btn_getLocation = v.findViewById(R.id.btn_getLocation);

        //TextView
        txtView_location = v.findViewById(R.id.txtView_location);

        //Material Button
        btn_recordDetails = v.findViewById(R.id.btn_recordDetails);
        btn_barCodeScanner = v.findViewById(R.id.btn_barCodeScanner);

        initUI();

        getIntentData();

        pageDirectories();
    }

    private void initUI() {
        defaultBitmap = ((BitmapDrawable) btn_uploadTransaction.getDrawable()).getBitmap();
    }

    private void getIntentData() {

        name = getArguments().getString("Name");
        phoneNumber = getArguments().getString("Phone Number");
        email = getArguments().getString("Email");
        password = getArguments().getString("Password");

        if (phoneNumber == null)
        {
            phoneNumber = "93542856";
        }
        else
            return;

    }

    private void pageDirectories() {

        btn_barCodeScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanBarCode();
            }
        });

        btn_uploadTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, requestCamera1);
            }
        });

        btn_getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Set up code for getting current location
            }
        });

        rg_transactionPurpose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
                mPurpose = radioButton.getText().toString();

                authenticateBarCodeScanner();
            }
        });

        rg_transactionFlow.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
                mTransactionFlow = radioButton.getText().toString();
            }
        });

        btn_recordDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mTitle = editTxt_title.getText().toString();
                mAmount = editTxt_amount.getText().toString();
                mDesc = editTxt_desc.getText().toString();
                mLocation = txtView_location.getText().toString();

                bitmap = ((BitmapDrawable) btn_uploadTransaction.getDrawable()).getBitmap();


                validateTransactionFlow();
                validateTitle();
                validateAmount();
                validatePurpose();
                validateDescription();
                validatePic();
                validateLocation();
                validateInput();

            }
        });

        btn_getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MapActiivty2.class);
                intent.putExtra("Phone Number", phoneNumber);
                startActivity(intent);
            }
        });

        Bundle bundle = getArguments();

        try {
            mLat = bundle.getString("latitude");
            mLon = bundle.getString("longitude");
            phoneNumber = bundle.getString("Phone Number");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        txtView_location.setText(mLat + " , " + mLon);
    }

    private void authenticateBarCodeScanner() {
        if (mPurpose.equals("Food and Drinks") | mPurpose.equals("Shopping") | mPurpose.equals("Groceries"))
        {
            btn_barCodeScanner.setVisibility(View.VISIBLE);
        }
        else
            btn_barCodeScanner.setVisibility(View.GONE);
    }

    private void scanBarCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to on flash");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null)
        {
            Toast.makeText(mContext, result.getContents(), Toast.LENGTH_SHORT).show();
            //TODO: Retrieve the bar code content and setText it to the Title, amount, and description



            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
        }
    });

    private void validateInput() {

        if (!validateTransactionFlow() | !validateTitle() | !validateAmount() | !validatePurpose() | !validateDescription() | !validatePic() | !validateLocation())
        {
            return;
        }
        else
        {
            String userTransaction= "User's Transaction";
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.child(phoneNumber).hasChild(userTransaction))
                    {
                        long numOfTransaction = snapshot.child(phoneNumber).child("User's Transaction").getChildrenCount();
                        System.out.println(numOfTransaction);

                        for (int i = (int) numOfTransaction; i<=100; i++)
                        {
                            String transactionID = "transaction " + (numOfTransaction + 1);

                            databaseReference.child(phoneNumber).child("User's Transaction").child(transactionID).child("Flow").setValue(mTransactionFlow);
                            databaseReference.child(phoneNumber).child("User's Transaction").child(transactionID).child("Title").setValue(mTitle);
                            databaseReference.child(phoneNumber).child("User's Transaction").child(transactionID).child("Amount").setValue(mAmount);
                            databaseReference.child(phoneNumber).child("User's Transaction").child(transactionID).child("Purpose").setValue(mPurpose);

                            databaseReference.child(phoneNumber).child("User's Transaction").child(transactionID).child("Description").setValue(mDesc);
                            databaseReference.child(phoneNumber).child("User's Transaction").child(transactionID).child("Image").setValue(mTransactionImg);
                            databaseReference.child(phoneNumber).child("User's Transaction").child(transactionID).child("Longitude").setValue(mLon);
                            databaseReference.child(phoneNumber).child("User's Transaction").child(transactionID).child("Latitude").setValue(mLat);

                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            byte[] data = baos.toByteArray();

                            StorageReference riversRef = imagesRef.child(transactionID+".jpg");
                            UploadTask uploadTask = riversRef.putBytes(data);

                            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    // Get a URL to the uploaded content
                                    Uri downloadUrl = taskSnapshot.getUploadSessionUri();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle unsuccessful uploads
                                    // ...
                                }
                            });
                        }


                        Intent intent = new Intent(mContext, SuccessAddActivty.class);
                        intent.putExtra("Transaction Title", mTitle);
                        intent.putExtra("Transaction Purpose", mPurpose);
                        intent.putExtra("Transaction Amount", mAmount);
                        getActivity().startActivity(intent);

                    }
                    else
                    {
                        databaseReference.child(phoneNumber).child("User's Transaction").child("transaction 1").child("Flow").setValue(mTransactionFlow);
                        databaseReference.child(phoneNumber).child("User's Transaction").child("transaction 1").child("Title").setValue(mTitle);
                        databaseReference.child(phoneNumber).child("User's Transaction").child("transaction 1").child("Amount").setValue(mAmount);
                        databaseReference.child(phoneNumber).child("User's Transaction").child("transaction 1").child("Purpose").setValue(mPurpose);

                        databaseReference.child(phoneNumber).child("User's Transaction").child("transaction 1").child("Description").setValue(mDesc);
                        databaseReference.child(phoneNumber).child("User's Transaction").child("transaction 1").child("Image").setValue(mTransactionImg);
                        databaseReference.child(phoneNumber).child("User's Transaction").child("transaction 1").child("Longitude").setValue(mLon);
                        databaseReference.child(phoneNumber).child("User's Transaction").child("transaction 1").child("Latitude").setValue(mLat);

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] data = baos.toByteArray();

                        StorageReference riversRef = imagesRef.child("Event 1.jpg");
                        UploadTask uploadTask = riversRef.putBytes(data);

                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // Get a URL to the uploaded content
                                Uri downloadUrl = taskSnapshot.getUploadSessionUri();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle unsuccessful uploads
                                // ...
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            }

        }

    private boolean validateLocation() {

        return true;
    }

    private boolean validatePic() {
        if (bitmap.equals(defaultBitmap))
        {
            //User did not upload any pics
            Toast.makeText(mContext, "Come on man, it wouldn't hurt to upload a picture", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }

    private boolean validateDescription() {

        if (mDesc.isEmpty())
        {
            editTxt_desc.setError("Required");
            return false;
        }
        else
            return true;
    }

    private boolean validatePurpose() {

        if (mPurpose.isEmpty())
        {
            Toast.makeText(mContext, "Please select a transaction purpose ", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }

    private boolean validateAmount() {

        if (mAmount.isEmpty())
        {
            editTxt_amount.setError("Required");
            return false;
        }
        else
            return true;
    }

    private boolean validateTitle() {

        //Regex pattern to allow only alphabets
        Pattern regexName = Pattern.compile("^[a-zA-Z]+$");
        Matcher matcher = regexName.matcher(mTitle);

        if (mTitle.isEmpty())
        {
            editTxt_title.setError("Required");
            return false;
        }
        else if (!matcher.matches())
        {
            editTxt_title.setError("Invalid title");
            return false;
        }
        else
            return true;
    }

    private boolean validateTransactionFlow() {

        if (mTransactionFlow.isEmpty())
        {
            Toast.makeText(mContext, "Please enter your transaction flow", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == requestCamera1)
        {
            Bitmap imgBitMap = (Bitmap)data.getExtras().get("data");
            int newWidth = 300;
            int newHeight = 200;
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(imgBitMap, newWidth, newHeight, false);
            btn_uploadTransaction.setImageBitmap(resizedBitmap);
        }

    }


}