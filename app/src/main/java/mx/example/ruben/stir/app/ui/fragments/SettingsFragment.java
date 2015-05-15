package mx.example.ruben.stir.app.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;

import mx.example.ruben.stir.R;

public class SettingsFragment extends Fragment {


    Context CONTEXT;
    CallbackManager callbackManager;
    LoginButton loginButton;

//    Desde el otro git

    public SettingsFragment() {}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        CONTEXT = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(CONTEXT.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        LoginButton btnClose = (LoginButton) rootView.findViewById(R.id.btn_close);
        btnClose.setFragment(this);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                SharedPreferences sharedPreferences = CONTEXT.getSharedPreferences("fb_user_prefs", CONTEXT.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("first_name", "");
                editor.putString("last_name", "");
                editor.putString("fb_id", "");
                editor.putString("img_profile", "");
                editor.putBoolean("is_login", false);
                editor.apply();

                Intent i = new Intent("mx.example.ruben.stir.FACEACTIVITY");
                startActivity(i);
            }
        });

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
