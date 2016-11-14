package com.android.gallery3.utils;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.File;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/11/14 9:57
 */

public class ImageInfoUtil {

    private static double imgLatitude;
    private static double imgLongitude;
    private static String make;
    private static String model;
    private static String time;
    private static String width;
    private static String height;
    private static String zoom;
    private static String focal;
    private static String iso;
    private static String flash;
    private static String balance;


    /**
     * 获取纬度
     */
    public static double getImgLatitude(File file) throws Exception {
        printImageInfo(file);
        return imgLatitude;
    }

    /**
     * 获取经度
     */
    public static double getImgLongitude(File file) throws Exception {
        printImageInfo(file);
        return imgLongitude;
    }

    /**
     * 获取制造商
     */
    public static String getMake(File file) throws Exception{
        printImageInfo(file);
        return make;
    }

    /**
     * 获取相机型号
     */
    public static String getModel(File file) throws Exception{
        printImageInfo(file);
        return model;
    }

    /**
     * 获取照片宽
     */
    public static String getWidth(File file) throws Exception{
        printImageInfo(file);
        return width;
    }

    /**
     * 获取照片高
     */
    public static String getHeight(File file) throws Exception{
        printImageInfo(file);
        return height;
    }

    /**
     * 获取时间
     */
    public static String getTime(File file) throws Exception{
        printImageInfo(file);
        return time;
    }

    /**
     * 获取光圈
     */
    public static String getZoom(File file) throws Exception{
        printImageInfo(file);
        return zoom;
    }

    /**
     * 获取焦距
     */
    public static String getFocal(File file) throws Exception{
        printImageInfo(file);
        return focal;
    }

    /**
     * 获取ISO
     */
    public static String getIso(File file) throws Exception{
        printImageInfo(file);
        return iso;
    }

    /**
     * 获取闪光灯
     */
    public static String getFlash(File file)throws Exception{
        printImageInfo(file);
        return flash;
    }

    /**
     * 获取白平衡
     */
    public static String getBalance(File file)throws Exception{
        printImageInfo(file);
        return balance;
    }

    /**
     * 读取照片里面的信息
     */
    public static void printImageInfo(File file) throws Exception {
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                String tagName = tag.getTagName();//标签名
                String desc = tag.getDescription();//标签信息
                if (tagName.equals("GPS Latitude")) {
                    imgLatitude = StrToDou(desc);
                    System.err.print("纬度：" + imgLatitude);
                } else if (tagName.equals("GPS Longitude")) {
                    imgLongitude = StrToDou(desc);
                    System.err.print("经度：" + imgLongitude);
                } else if (tagName.equals("Make")){
                    make = desc;
                    System.err.print("制造商：" + make);
                } else if (tagName.equals("Model")){
                    model = desc;
                    System.err.print("相机型号：" + model);
                } else if (tagName.equals("Date/Time")){
                    time = desc;
                    System.err.print("时间：" + time);
                } else if (tagName.equals("Exif Image Width")){
                    width = desc;
                    System.err.print("宽：" + width);
                } else if (tagName.equals("Exif Image Height")){
                    height = desc;
                    System.err.print("高：" + height);
                } else if (tagName.equals("Digital Zoom Ratio")){
                    zoom = desc;
                    System.err.print("光圈：" + zoom);
                } else if (tagName.equals("Focal Length")){
                    focal = desc;
                    System.err.print("焦距：" + focal);
                } else if (tagName.equals("ISO Speed Ratings")) {
                    iso = desc;
                    System.err.print("iso：" + iso);
                }  else if (tagName.equals("Flash")){
                    flash = desc;
                    System.err.print("闪光灯：" + flash);
                } else if (tagName.equals("White Balance")){
                    balance = desc;
                    System.err.print("白平衡：" + balance);
                }
            }
        }
    }

    /**
     * 将经纬度度分秒格式String转换为度double格式
     */
    private static double StrToDou(String desc) {
        Double du = Double.parseDouble(desc.substring(0, desc.indexOf("°")).trim());
        Double fen = Double.parseDouble(desc.substring(desc.indexOf("°") + 1, desc.indexOf("'")).trim());
        Double miao = Double.parseDouble(desc.substring(desc.indexOf("'") + 1, desc.indexOf("\"")).trim());
        Double duDouble = du + fen / 60 + miao / 60 / 60;
        return duDouble;
    }
}
