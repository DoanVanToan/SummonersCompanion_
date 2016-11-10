package com.toandoan.lol.utility;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.toandoan.lol.model.champion.ChampionEnity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by ToanDoan on 10/15/2016.
 */

public class FileOperations {
    private Context mContext;

    public final static String CHAMPION = "champion";
    public final static String VIDEO = "video";

    public FileOperations(Context mContext) {
        this.mContext = mContext;
    }

    public String getChampionPath() {
        return Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/Android/data/" + mContext.getPackageName() + "/" + CHAMPION + "/";
    }

    public String getVideoPath() {
        return Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/Android/data/" + mContext.getPackageName() + "/" + VIDEO + "/";
    }

    public void makeDir(String dirName) {
        File file = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/Android/data");
        if (!file.exists()) {
            file.mkdir();
        }

        File direct = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/Android/data/" + mContext.getPackageName());
        if (!direct.exists()) {
            direct.mkdirs();
        }

        File data = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/Android/data/" + mContext.getPackageName() + "/" + dirName);
        if (!data.exists()) {
            data.mkdir();
        }

    }

    public Boolean writeData(String fname, String fcontent) {
        try {
            File file;
            makeDir(CHAMPION);

            String fpath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/Android/data/" + mContext.getPackageName() + "/" + CHAMPION + "/" + fname + ".txt";

            file = new File(fpath);

            // If file does not exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(fcontent);
            bw.close();

            Log.d("WriteFile", "Sucess");
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public String readData(String fname) {

        BufferedReader br = null;
        String response = null;

        try {
            StringBuffer output = new StringBuffer();
            String fpath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/Android/data/" + mContext.getPackageName() + "/" + CHAMPION + "/" + fname + ".txt";

            br = new BufferedReader(new FileReader(fpath));
            String line = "";
            while ((line = br.readLine()) != null) {
                output.append(line);
            }
            response = output.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }

        return response;

    }

    public String getVideoFile(String videoName) {
        String path = getVideoPath() + videoName + ".mp4";
        File file = new File(path);
        if (file.exists()) {
            return path;
        }
        return null;
    }


}