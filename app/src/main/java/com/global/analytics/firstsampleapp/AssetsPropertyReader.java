package com.global.analytics.firstsampleapp;

/**
 * Created by Prakash on 05/02/15.
 */

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class AssetsPropertyReader {
    private Context context;
    private Properties properties;

    public AssetsPropertyReader(Context context) {
        this.context = context;
        /**
         * Constructs a new Properties object.
         */
        properties = new Properties();
    }

    public Properties getProperties(String FileName) {

        try {
            /**
             * getAssets() Return an AssetManager instance for your
             * application's package. AssetManager Provides access to an
             * application's raw asset files;
             */
            AssetManager assetManager = context.getAssets();
            /**
             * Open an asset using ACCESS_STREAMING mode. This
             */
            InputStream inputStream = assetManager.open(FileName);
            /**
             * Loads properties from the specified InputStream,
             */
            properties.load(inputStream);

        } catch (IOException e) {
            properties = null;
        } catch (Exception e) {
            properties = null;
        }
        return properties;

    }

    public Properties getPropertiesFromDocs(String FileName) {

        try {
            /**
             * getAssets() Return an AssetManager instance for your
             * application's package. AssetManager Provides access to an
             * application's raw asset files;
             */
            //AssetManager assetManager = context.getAssets();
            /**
             * Open an asset using ACCESS_STREAMING mode. This
             */
            InputStream inputStream = context.openFileInput(FileName);
            /**
             * Loads properties from the specified InputStream,
             */
            properties.load(inputStream);
            inputStream.close();

        } catch (IOException e) {
            properties=null;
        }
        catch (Exception e) {
            properties = null;
        }
        return properties;

    }

    public void writePropertiesToDocs(String FileName,Properties properties) {
        try{
            OutputStream outputStream = context.openFileOutput(FileName,context.MODE_PRIVATE);
            properties.store(outputStream,null);
            outputStream.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}