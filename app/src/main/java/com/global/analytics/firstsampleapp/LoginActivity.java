package com.global.analytics.firstsampleapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Properties;

public class LoginActivity extends Activity implements onTaskCompleted,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, ResultCallback,
        View.OnClickListener {

    /* Request code used to invoke sign in user interactions. */
    private static final int RC_SIGN_IN = 0;
    private static final int RC_FB_SIGNIN = 64206;
    private boolean done = false;

    /* Client used to interact with Google APIs. */
    private GoogleApiClient mGoogleApiClient;

    // ...

    @Override
    public void onClick(View v) {
        // ...
        if (v.getId() == R.id.sign_in_button) {
            onSignInClicked();
        }
    }

    private EditText txf1,txf2;
    private SharedDataManager sharedDataManager;
    private loading_indicator lindicator;
    private AlertDialog alertDialog;
    public AssetsPropertyReader assetsPropertyReader;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private ProfileTracker profileTracker;
    /* Is there a ConnectionResult resolution in progress? */
    private boolean mIsResolving = false;

    /* Should we automatically resolve ConnectionResults when possible? */
    private boolean mShouldResolve = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Build GoogleApiClient with access to basic profile
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_PROFILE)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();
        PendingResult result = Plus.PeopleApi.loadVisible(mGoogleApiClient, null);
        result.setResultCallback(this);
        //facebook
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        Log.v("Key: #####  ", FacebookSdk.getApplicationSignature(getApplicationContext()));
        setContentView(R.layout.activity_login);
        assetsPropertyReader = new AssetsPropertyReader(this);
        sharedDataManager = SharedDataManager.getInstance(getApplicationContext());
        txf1 = (EditText)findViewById(R.id.email);
        txf2 = (EditText)findViewById(R.id.password);
        //google sign in
        findViewById(R.id.sign_in_button).setOnClickListener(this);

        int randomNum = 0 + (int)(Math.random()*100);
        if(randomNum<=35)
            findViewById(R.id.main_bckg).setBackgroundResource(R.drawable.bckg1);

        TextView tv=(TextView)findViewById(R.id.signUpTextView);
        tv.setText(Html.fromHtml(getString(R.string.sign_up_text)));

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends");
        // If using in a fragment
        //loginButton.setFragment(this);
        // Other app specific specialization

        // Callback registration
        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Profile profile = Profile.getCurrentProfile();
                        Log.v("FB Profile: ", profile.getName());
                        DataLayer data = new DataLayer();
                        data.pullSuccess = true;
                        data.first_name = profile.getFirstName();
                        data.last_name = profile.getLastName();
                        data.profilePic = profile.getProfilePictureUri(100, 100).toString();
                        data.email_id = "";
                        data.state = "0";
                        data.ReqLoanAmt = "100";
                        data.salutation = "Ms";
                        if (data!=null) {
                            sharedDataManager.applicationData = data;
                            MyClass obj = new MyClass(getApplicationContext());
                            obj.saveObject(sharedDataManager.applicationData);
                        }
                        Intent intent = new Intent(getApplicationContext(), dashboard.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Log.v("FB exp:", "Cancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Log.v("FB exp:", exception.toString());
                    }
                });

    }

    @Override
    public void onStart(){
        super.onStart();
        //mGoogleApiClient.connect();
        if(sharedDataManager.checkIfFirstTime()){
            Intent intent = new Intent(this,IntroActivity.class);
            startActivity(intent);
        }
        else if (AlreadyLoggedIn() && !done){
            Intent intent = new Intent(this, dashboard.class);
            startActivity(intent);
            done = true;
        }
    }
    private boolean AlreadyLoggedIn(){
        MyClass obj = new MyClass(getApplicationContext());
        DataLayer data = obj.getObject();
        if (data!=null) {
            sharedDataManager.applicationData = data;
            return true;
        }
        return false;
    }
    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this, "914957015252031");
    }
    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this, "914957015252031");
    }
    @Override
     protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }
    public void onClickButton (View view){
        try {
            String userName = txf1.getText().toString().trim();
            String passWord = txf2.getText().toString();
            sharedDataManager.Username = userName;
//            Intent intent = new Intent(this,ApplicationActivity.class);
//            startActivity(intent);
            if (userName.length() > 0 && passWord.length() > 0) {
                lindicator = new loading_indicator();
                lindicator.showIndicator(LoginActivity.this);
                String url = sharedDataManager.RemoteUrl;
                String postData = "";
                try {
                    postData = "<auth><username>"+userName+"</username><password>"+passWord+"</password></auth>";
                    //postData = "username=" + URLEncoder.encode(userName, "UTF-8") + "&password=" + URLEncoder.encode(passWord, "UTF-8");
                } catch (Exception e) {
                    e.printStackTrace();
                    postData = "username=" + userName + "&password=" + passWord;
                }
                Log.v("Post Data", postData);
                String notificationName = "LoginCall";
                new RequestParser(this.getApplicationContext(), this).execute(url, postData, notificationName, true);
            } else {
                lindicator.hideIndicator();
                showAlertMessage("Email and password cannot be empty.", "Enter Credentials");
            }
        }catch (Exception e){
            e.printStackTrace();
            if (lindicator != null) lindicator.hideIndicator();
            showAlertMessage("Unable to process login, Please try again later.", "Error");
        }
    }
    private void loginCheck(JSONObject jsonObject) {
        try {
            sharedDataManager.applicationData = new DataLayer(jsonObject);
            if (sharedDataManager.applicationData.pullSuccess) {
                sharedDataManager.applicationData.state = "0";
                MyClass obj = new MyClass(this);
                DataLayer data = obj.getObject();
                if (data!=null) sharedDataManager.applicationData = data;
                Intent intent = new Intent(this, dashboard.class);
                startActivity(intent);
            }
            else {
                sharedDataManager.applicationData.state = "0";
                showAlertMessage("Login Failed", "User name and Password combination did not match. " +
                        "Please try again.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void signup(View view){
        sharedDataManager.applicationData = new DataLayer();
        sharedDataManager.applicationData.pullSuccess = false;
        sharedDataManager.applicationData.state = "0";
        Intent intent = new Intent(this, dashboard.class);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            txf2.setText("Settings clicked");
//            return true;
//        }
//        else if (id == R.id.action_settings1) {
//            Intent intent = new Intent(this,MainActivity.class);
//            startActivity(intent);
//
//        }

        return super.onOptionsItemSelected(item);
    }
    private void showAlertMessage(String msg, String title) {
        try {
            alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
            alertDialog.setTitle(title);
            alertDialog.setMessage(msg);
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setCancelable(false);
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new alertDialogOnClickListener());
            //alertDialog.setIcon(R.drawable.icon);
            alertDialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        // onConnected indicates that an account was selected on the device, that the selected
        // account has granted any requested permissions to our app and that we were able to
        // establish a service connection to Google Play services.
        Log.d("FACEBOOK+", "onConnected:" + bundle);
        mShouldResolve = false;
        if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
            Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
            DataLayer data = new DataLayer();
            data.pullSuccess = true;
            data.first_name = currentPerson.getDisplayName();
            data.last_name = currentPerson.getNickname();
            data.profilePic = currentPerson.getImage().getUrl();
            data.email_id = "";
            data.state = "0";
            data.ReqLoanAmt = "100";
            data.salutation = "Mr";
            if (data!=null) sharedDataManager.applicationData = data;
            Intent intent = new Intent(getApplicationContext(), dashboard.class);
            startActivity(intent);
        }

        // Show the signed-in UI
        //showSignedInUI();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
    private void onSignInClicked() {
        // User clicked the sign-in button, so begin the sign-in process and automatically
        // attempt to resolve any errors that occur.
        mShouldResolve = true;
        mGoogleApiClient.connect();
        loginButton.callOnClick();
        // Show a message to the user that we are signing in.
        //mStatus.setText(R.string.signing_in);
    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
// Could not connect to Google Play Services.  The user needs to select an account,
        // grant permissions or resolve an error in order to sign in. Refer to the javadoc for
        // ConnectionResult to see possible error codes.
        Log.d("GOOGLE+", "onConnectionFailed:" + connectionResult);

        if (!mIsResolving && mShouldResolve) {
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(this, RC_SIGN_IN);
                    mIsResolving = true;
                } catch (IntentSender.SendIntentException e) {
                    Log.e("GOOGLE+", "Could not resolve ConnectionResult.", e);
                    mIsResolving = false;
                    mGoogleApiClient.connect();
                }
            } else {
                // Could not resolve the connection result, show the user an
                // error dialog.
                //showErrorDialog(connectionResult);
            }
        } else {
            // Show the signed-out UI
            //showSignedOutUI();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("GOOGLE+", "onActivityResult:" + requestCode + ":" + resultCode + ":" + data);
        if (requestCode == RC_FB_SIGNIN && resultCode == -1){
            String dataContent = data.getExtras().toString();
            Log.v("facebook: ", data.getExtras().toString());
            callbackManager.onActivityResult(requestCode,resultCode,data);
        }
        if (requestCode == RC_SIGN_IN) {
            // If the error resolution was not successful we should not resolve further.
            if (resultCode != RESULT_OK) {
                mShouldResolve = false;
            }

            mIsResolving = false;
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onResult(Result result) {
        Log.v("Reslt google",result.toString());
    }

    class alertDialogOnClickListener implements AlertDialog.OnClickListener {
    public void onClick(DialogInterface dialog, int which) {
        Log.v("First", String.valueOf(which));
        switch(which){
            case DialogInterface.BUTTON_NEGATIVE:
                dialog.dismiss();
                break;
            case DialogInterface.BUTTON_POSITIVE:
                dialog.dismiss();
                break;
            case DialogInterface.BUTTON_NEUTRAL:
                dialog.dismiss();
                break;
        }
    }
}
    @Override
    public void onTaskCompleted(JSONObject jsonObject, String notification) {
        lindicator.hideIndicator();
        switch (notification)
        {
            case "LoginCall":
            {
                loginCheck(jsonObject);
                break;
            }
            case "NOINTERNET":
            {
                //Toast.makeText(context, "No network connection", Toast.LENGTH_SHORT).show();
                showAlertMessage("No Internet Connection.","Please Check");
                break;
            }
            case "INVALID_SESSION":
            {
                showAlertMessage("Unable to connect to server. Please try after sometimes",
                        "Login Failed");
                break;
            }
            default:
            {
                showAlertMessage("Unable to connect to server. Please try after sometimes",
                        "Login Failed");
                break;
            }

        }
    }

    @Override
    public void onTaskCompleted(Drawable response, String notification) {
        return;
    }
}
