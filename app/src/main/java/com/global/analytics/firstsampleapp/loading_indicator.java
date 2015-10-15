package com.global.analytics.firstsampleapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;

/**
 * Created by Prakash on 02/02/15.
 */
public class loading_indicator {
    public Dialog dialog;
    public ProgressDialog progressDialog;
    AnimationDrawable loadingAnimation;

    public void showIndicator(Context context)
    {

        /*dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.loading_indicator);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        image.setBackgroundResource(R.drawable.loading_indicator);
        loadingAnimation = (AnimationDrawable) image.getBackground();
        loadingAnimation.start();
        dialog.show();
        */
        try {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("Loading Please Wait...");

                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setCancelable(false);
            }
            progressDialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void hideIndicator()
    {
        /*if (dialog.isShowing())
        {
            dialog.hide();
            dialog.dismiss();
        }*/
        try {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
