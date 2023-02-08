package com.example.learnloop.Looprary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnloop.Fragments.LoopraryFragment;
import com.example.learnloop.MainActivity;
import com.example.learnloop.R;
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

import java.io.ByteArrayOutputStream;

public class CourseDetailsActivity extends AppCompatActivity {

    private TextView txtView_back, txtView_courseTitle, txtView_courseStats, txtView_courseAttendee,
            txtView_attendeeDetail, txtView_lessonsProgress, txtView_courseHost, txtView_hostDesc,
            txtView_courseDesc, txtView_seeMore;

    private ImageView imgView_course, imgView_courseAttendee;

    private MaterialButton btn_addToCalendar, btn_emailOrganizer, btn_registerCourse;

    private String courseTitle, hostName, hostDesc, courseDuration, courseLesson, courseDesc, courseUniversity, universityDesc, publishedDates, courseLink;
    private int img_course, img_host;

    private String mEmail = "thetpine254@gmail.com";
    private String mPhoneNumber = "93542856";

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://learnloop-1673224439925-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference databaseReference  = database.getReference().child("users");

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    StorageReference imagesRef = storageRef.child("courses");

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_details);
        getSupportActionBar().hide();

        intent = getIntent();

        initWidget();

        getIntentData();

        pageDirectories();

    }

    private void getIntentData() {

        courseTitle = intent.getStringExtra("Course Title");
        hostName = intent.getStringExtra("Host Name");
        hostDesc = intent.getStringExtra("Host Desc");
        courseDuration = intent.getStringExtra("Course Duration");
        courseLesson = intent.getStringExtra("Course Lessons");

        courseDesc = intent.getStringExtra("Course Desc");
        courseUniversity = intent.getStringExtra("Course University");
        universityDesc = intent.getStringExtra("University Desc");
        publishedDates = intent.getStringExtra("Published Dates");
        courseLink = intent.getStringExtra("Course Link");

        img_course = intent.getIntExtra("Course Image", 0);
        img_host = intent.getIntExtra("Host Image", 0);

        initUI();
    }

    private void pageDirectories() {

        txtView_seeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CourseDetailsActivity.this);
                builder.setTitle("View Link");
                builder.setMessage("Do you wish to visit the link within or outside the app?");
                builder.setPositiveButton("View Page in App", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(getApplicationContext(), WebView_Activity.class);
                        intent.putExtra("Link", courseLink);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Share", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        String url = "https://www.webmd.com/diet/features/is-your-diet-aging-you";
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Check out this cool ass NFT");
                        intent.putExtra(Intent.EXTRA_TEXT, courseLink);
                        startActivity(Intent.createChooser(intent, "Share Via"));
                    }
                });
                builder.create().show();
            }
        });

        btn_addToCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Add the event to your local calendar
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setData(CalendarContract.Events.CONTENT_URI);
                intent.putExtra(CalendarContract.Events.TITLE, courseTitle);
                intent.putExtra(CalendarContract.Events.DESCRIPTION, hostDesc);
                intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                intent.putExtra(Intent.EXTRA_EMAIL, mEmail);

                startActivity(intent);
            }
        });

        btn_emailOrganizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipient = "thetpne254@gmail.com";
                String subject = "Enquires about event";
                String message = "Your message";

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, message);

                // Needed only if you are using email clients other than Gmail
                intent.setType("message/rfc822");

                startActivity(Intent.createChooser(intent, "Send email"));
            }
        });

        btn_registerCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });

        txtView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void addData() {

        String userCourse = "User's Courses";

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(mPhoneNumber).hasChild(userCourse))
                {
                    long numOfTransaction = snapshot.child(mPhoneNumber).child("User's Courses").getChildrenCount();
                    System.out.println(numOfTransaction);

                    for (int i = (int) numOfTransaction; i<=100; i++)
                    {
                        String eventID = "Course " + (numOfTransaction + 1);

                        databaseReference.child(mPhoneNumber).child("User's Courses").child(eventID).child("Title").setValue(courseTitle);
                        databaseReference.child(mPhoneNumber).child("User's Courses").child(eventID).child("Host").setValue(hostName);
                        databaseReference.child(mPhoneNumber).child("User's Courses").child(eventID).child("Host Desc").setValue(hostDesc);
                        databaseReference.child(mPhoneNumber).child("User's Courses").child(eventID).child("Link").setValue(courseLink);

                        Bitmap bitmap = ((BitmapDrawable) imgView_course.getDrawable()).getBitmap();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] data = baos.toByteArray();

                        StorageReference riversRef = imagesRef.child(eventID+".jpg");
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
                else
                {
                    databaseReference.child(mPhoneNumber).child("User's Courses").child("Course 1").child("Title").setValue(courseTitle);
                    databaseReference.child(mPhoneNumber).child("User's Courses").child("Course 1").child("Host").setValue(hostName);
                    databaseReference.child(mPhoneNumber).child("User's Courses").child("Course 1").child("Host Desc").setValue(hostDesc);
                    databaseReference.child(mPhoneNumber).child("User's Courses").child("Course 1").child("Link").setValue(courseLink);

                    Bitmap bitmap = ((BitmapDrawable) imgView_course.getDrawable()).getBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] data = baos.toByteArray();

                    StorageReference riversRef = imagesRef.child("Course 1.jpg");
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

                //Transition from this activity page to the fragment page

                Toast.makeText(CourseDetailsActivity.this, "Course registered successfully", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void initUI() {

        txtView_courseTitle.setText(courseTitle);
        txtView_courseAttendee.setText(hostName);
        txtView_attendeeDetail.setText(hostDesc);
        txtView_lessonsProgress.setText("Remaining: " + courseLesson);

        txtView_courseStats.setText("58,320 views - " + publishedDates);
        txtView_courseHost.setText(courseUniversity);
        txtView_hostDesc.setText(universityDesc);
        txtView_courseDesc.setText(courseDesc);

        imgView_course.setImageResource(img_course);
        imgView_courseAttendee.setImageResource(img_host);

    }

    private void initWidget() {

        //TextView
        txtView_back = findViewById(R.id.txtView_back);
        txtView_courseTitle = findViewById(R.id.txtView_courseTitle);
        txtView_courseStats = findViewById(R.id.txtView_courseStats);
        txtView_courseAttendee = findViewById(R.id.txtView_courseAttendee);
        txtView_attendeeDetail = findViewById(R.id.txtView_attendeeDetail);
        txtView_lessonsProgress = findViewById(R.id.txtView_lessonsProgress);
        txtView_courseHost = findViewById(R.id.txtView_courseHost);
        txtView_hostDesc = findViewById(R.id.txtView_hostDesc);
        txtView_courseDesc = findViewById(R.id.txtView_courseDesc);
        txtView_seeMore = findViewById(R.id.txtView_seeMore);

        //Imageview
        imgView_course = findViewById(R.id.imgView_course);
        imgView_courseAttendee = findViewById(R.id.imgView_courseAttendee);

        //Button
        btn_addToCalendar = findViewById(R.id.btn_addToCalendar);
        btn_emailOrganizer = findViewById(R.id.btn_emailOrganizer);
        btn_registerCourse = findViewById(R.id.btn_registerCourse);

    }
}