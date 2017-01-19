package com.islarf6546.gmail.lock_prototype_1;

import java.io.FileOutputStream;
import java.io.IOException;

import static android.os.ParcelFileDescriptor.MODE_WORLD_READABLE;

/**
 * Created by Islarf on 23/12/2016.
 */

public final class FILE_Helper {
    private final String defDirectory = new String();

    private FILE_Helper(){}


    public boolean createFile(String directory, String filename)  throws IOException {
        final String TESTSTRING = new String("Hello Android");

        //need to openFileOutput()-method
        //we use MODE_WORLD_READABLE, nothing to hide
        FileOutputStream fOut = openFileOutput("sample_file.txt");
    }
}
