package com.example.itsadmin.provaupload2;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.jibble.simpleftp.SimpleFTP;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout photos_layout;
    private static Bitmap scaledphoto=null;
    private String filePath=null;
    private Uri u=null;
    private Boolean picTaken=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        photos_layout=(RelativeLayout)findViewById(R.id.photos);
        //button with onClickListener to turn on camera for taking picture
        ((Button)photos_layout.findViewById(R.id.capture_photo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                String pictureName="Test";//here you can get picture name from user. I supposed Test name
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                File photo = new File(Environment.getExternalStorageDirectory(),  pictureName+".jpg");//save picture (.jpg) on SD Card
                u=Uri.fromFile(photo);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,u);
                filePath = photo.getAbsolutePath();
                startActivityForResult(intent, RESULT_FIRST_USER);
            }
        });

        ((Button)photos_layout.findViewById(R.id.upload_picture)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                upLoadPicture();
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        try {

            getContentResolver().notifyChange(u, null);
            ContentResolver cr = getContentResolver();
            Bitmap bm = android.provider.MediaStore.Images.Media.getBitmap(cr, u);
//ImageView to set the picture taken from camera.
            ImageView photo_captured=(ImageView)photos_layout.findViewById(R.id.pic_captured);
            photo_captured.setImageBitmap(bm);
            picTaken=true; //to ensure picture is taken

        }catch(Exception e) {

            e.printStackTrace();

        }
    }

    //Method for uploading picture on FtpServer
    public void upLoadPicture() {

        if(picTaken){

            final ProgressDialog pd = ProgressDialog.show(this, "Please wait", "Uploading Picture ...", true);
            new Thread(){

                @Override
                public void run() {

                    File file = new File(filePath);

                    try {

                        FTPClient client = new FTPClient();
                        client.connect("ftp.********.net");
                        client.login(ftp_server_username, ftp_server_password); //this is the login credentials of your ftpserver. Ensure to use valid username and password otherwise it throws exception

                        try {

                            client.changeDirectory("MyPictures"); //I want to upload picture in MyPictures directory/folder. you can use your own.
                        } catch (Exception e) {

                            client.createDirectory("MyPictures");
                            client.changeDirectory("MyPictures");
                        }
                        client.upload(file); //this is actual file uploading on FtpServer in specified directory/folder
                        client.disconnect(true);   //after file upload, don't forget to disconnect from FtpServer.
                        file.delete();
                    }
                    catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Exception: "+e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                        pd.dismiss();
            }.start();

        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please Take Picture First than Upload.", Toast.LENGTH_LONG).show();

        }
    }

}
