package com.example.kh.myfacebook.View;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kh.myfacebook.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private TextView txtdetail;
    private Profile profile;
    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {


        @Override
        public void onSuccess(LoginResult loginResult) {
            accessToken = loginResult.getAccessToken();
            profile = Profile.getCurrentProfile();
            processProfile(profile);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };
    private AccessTokenTracker accessTokenTracker;
    private AccessToken accessToken;
    private ProfileTracker profileTracker;

    public MyFragment() {
        // Required empty public constructor
    }

    public void processProfile(Profile profile){
        if(profile!=null){
            txtdetail.setText("Welcome "+profile.getFirstName());
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if(profile==null){
                    processProfile(currentProfile);
                }
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        txtdetail = (TextView) view.findViewById(R.id.txtdetail);
        loginButton.setReadPermissions("user_friends");
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager,callback);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    public void onResume() {
        super.onResume();
        profile = Profile.getCurrentProfile();
        processProfile(profile);
    }
}
