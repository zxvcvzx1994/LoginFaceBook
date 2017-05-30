package com.example.kh.myfacebook.Controler;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by kh on 5/30/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        processHASHMAP();
    }

    public void processHASHMAP(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.kh.myfacebook",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }

        } catch (NoSuchAlgorithmException e) {

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

}
