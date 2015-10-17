package com.global.analytics.firstsampleapp;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Prakash on 17/10/15.
 */
public class MyClass implements Serializable {
    private static final long serialVersionUID = 1L;

    public Context context;



    public MyClass( Context context) {

        this.context = context;
    }

    public boolean saveObject(DataLayer obj) {
        final File suspend_f = new File(context.getFilesDir().getAbsolutePath(), "test");

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean keep = true;

        try {
            fos = new FileOutputStream(suspend_f);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
        } catch (Exception e) {
            keep = false;
        } finally {
            try {
                if (oos != null) oos.close();
                if (fos != null) fos.close();
                if (keep == false) suspend_f.delete();
            } catch (Exception e) {
            /* do nothing */
            }
        }

        return keep;
    }

    public DataLayer getObject() {
        final File suspend_f = new File(context.getFilesDir().getAbsolutePath(), "test");

        DataLayer simpleClass = null;
        FileInputStream fis = null;
        ObjectInputStream is = null;

        try {
            fis = new FileInputStream(suspend_f);
            is = new ObjectInputStream(fis);
            simpleClass = (DataLayer) is.readObject();
        } catch (Exception e) {
            String val = e.getMessage();
        } finally {
            try {
                if (fis != null) fis.close();
                if (is != null) is.close();
            } catch (Exception e) {
            }
        }

        return simpleClass;
    }
}