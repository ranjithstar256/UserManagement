package kp.ranjith.usermanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

        private EditText edUserName;
        private EditText edAge;
        private EditText edLocation;
        private EditText edMobile;
        private EditText edPassword;

        private Button buttonRegister;

        private static final String REGISTER_URL = "http://104.236.122.77/good/ttt.php";
    String resu;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signup);

            edUserName = (EditText) findViewById(R.id.idusername);
            edAge= (EditText) findViewById(R.id.idage);
            edLocation= (EditText) findViewById(R.id.idlocation);
            edMobile= (EditText) findViewById(R.id.idmobile);
            edPassword = (EditText) findViewById(R.id.idpassword);

            buttonRegister = (Button) findViewById(R.id.email_sign_in_button);

            buttonRegister.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v == buttonRegister){
                registerUser();
            }
        }

        private void registerUser() {
            String str_username = edUserName.getText().toString().trim().toLowerCase();
            String str_age = edAge.getText().toString().trim().toLowerCase();
            String str_location = edLocation.getText().toString().trim().toLowerCase();
            String str_mobile = edMobile.getText().toString().trim().toLowerCase();
            String str_password = edPassword.getText().toString().trim().toLowerCase();

          String y =  register(str_username,str_age,str_location,str_mobile,str_password);

            if(y.equals("con oksuccessfully registered")){
                startActivity(new Intent(SignUpActivity.this,HomePage.class));
            }
        }

        private String register(String vr_username, String vr_age, String vr_locatin, String vr_mobile, String vr_password) {

            class RegisterUser extends AsyncTask<String, Void, String>{
                ProgressDialog loading;
                Operations ruc = new Operations();


                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(SignUpActivity.this, "Please Wait",null, true, true);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                }

                @Override
                protected String doInBackground(String... params) {

                    HashMap<String, String> data = new HashMap<String,String>();
                    data.put("username",params[0]);
                    data.put("age",params[1]);
                    data.put("location",params[2]);
                    data.put("mobile",params[3]);
                    data.put("password",params[4]);

                    String result = ruc.sendPostRequest(REGISTER_URL,data);

                    return  result;
                }
            }

            RegisterUser ru = new RegisterUser();
            ru.execute(vr_username,vr_locatin,vr_mobile,vr_password,vr_age);

            return vr_username;
        }
    }
  /*  // UI references.
    private EditText mName;
    private EditText mUsernae;
    private EditText mPassword;
    private EditText mEmail;
    String str_name, str_username, str_password, str_email;

    private static final String REGISTER_URL = "http://192.168.1.8/good/ttt.php";

   // private static final String REGISTER_URL = "http://simplifiedcoding.16mb.com/UserRegistration/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ActivityCompat.requestPermissions(SignUpActivity.this,
                new String[]{Manifest.permission.INTERNET},
                1);

        mName = (EditText) findViewById(idname);
        mUsernae = (EditText) findViewById(R.id.idusername);

        mPassword = (EditText) findViewById(idpassword);
        mEmail = (EditText) findViewById(R.id.idemail);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                str_name = mName.getText().toString();
                str_username = mUsernae.getText().toString();
                str_password = mPassword.getText().toString();
                str_email = mEmail.getText().toString();


                //register(str_name, str_username, str_password, str_email);


                RegisterUser ru = new RegisterUser();
                ru.execute(str_name, str_username, str_password, str_email);
            }
        });

    }
    //private void register(String name, String str_username, String str_password, String str_email) {

        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;
            Operations ruc = new Operations();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SignUpActivity.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String, String>();
                data.put("name", params[0]);
                data.put("username", params[1]);
                data.put("password", params[2]);
                data.put("email", params[3]);

                String result = ruc.sendPostRequest(REGISTER_URL, data);

                return result;
            }
        }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                    Toast.makeText(SignUpActivity.this, "Permission granted", Toast.LENGTH_SHORT).show();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(SignUpActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
//}*/

