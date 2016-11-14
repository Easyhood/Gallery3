package com.android.gallery3.utils;

import android.os.Environment;

import com.android.gallery3.global.GlobalConstants;

import java.io.File;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/11/14 11:04
 */

public class FileUtil {

    public static File[] getFileList(){
        File file = new File(Environment.getExternalStorageDirectory().getPath(), GlobalConstants.PHOTO_URL);
        File[] tempList = file.listFiles();
        return tempList;
    }
}
