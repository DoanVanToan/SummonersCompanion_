package com.toandoan.lol.utility;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.toandoan.lol.R;
import com.toandoan.lol.model.champion.ChampionEnity;
import com.toandoan.lol.model.champion.ChampionSkinEnity;
import com.toandoan.lol.model.matery.MasteryEnity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ToanDoan on 9/29/2016.
 */

public class Utils {
    public static class RiotStatic {
        public static String getProfileIcon(int profileIconID) {
            return "http://ddragon.leagueoflegends.com/cdn/6.19.1/img/profileicon/" + profileIconID + ".png";
        }


        public static String getChampionIcon(String champName) {
            return "http://ddragon.leagueoflegends.com/cdn/6.19.1/img/champion/" + champName + ".png";
        }

        public static String getChampionCover(ChampionEnity championEnity) {
            int listSkinSize = 0;
            if (championEnity.getSkins() != null) {
                listSkinSize = championEnity.getSkins().size();
            }

            int skinID = new Random().nextInt(listSkinSize - 1);

            return "http://ddragon.leagueoflegends.com/cdn/img/champion/splash/" + championEnity.getKey() + "_" + skinID + ".jpg";
        }

        public static String getChampionCover(ChampionEnity championEnity, int position) {
            return "http://ddragon.leagueoflegends.com/cdn/img/champion/splash/" + championEnity.getKey() + "_" + position + ".jpg";
        }

        public static String getChampionCover(ChampionEnity championEnity, ChampionSkinEnity championSkinEnity) {
            return "http://ddragon.leagueoflegends.com/cdn/img/champion/splash/" + championEnity.getKey() + "_" + championSkinEnity.getNum() + ".jpg";
        }

        public static String getChampionPassive(String passiveFileName) {
            return "http://ddragon.leagueoflegends.com/cdn/6.20.1/img/passive/" + passiveFileName;
        }

        public static String getChampionAbiliteUrlImage(String championAbiliteFileName) {
            return "http://ddragon.leagueoflegends.com/cdn/6.20.1/img/spell/" + championAbiliteFileName;
        }

        public static String getChampionAbiliteUrlVideo(ChampionEnity championEnity, int posion) {
            String championID = championEnity.getId();
            int missingLength = 4 - championID.length();
            for (int i = 0; i < missingLength; i++) {
                championID = "0" + championID;
            }

            return "https://lolstatic-a.akamaihd.net/champion-abilities/videos/mp4/" + championID + "_0" + posion + ".mp4";
        }

        public static String getChampionAbiliteThumbUrlVideo(ChampionEnity championEnity, int posion) {
            String championID = championEnity.getId();
            int missingLength = 4 - championID.length();
            for (int i = 0; i < missingLength; i++) {
                championID = "0" + championID;
            }

            return " https://lolstatic-a.akamaihd.net/champion-abilities/images/" + championID + "_0" + posion + ".jpg";
        }

        public static String getChampionAbilitesFileName(ChampionEnity championEnity, int posion) {
            String championID = championEnity.getId();
            int missingLength = 4 - championID.length();
            for (int i = 0; i < missingLength; i++) {
                championID = "0" + championID;
            }
            return championID + "_0" + posion;
        }


        /**
         * Get Item Image
         */
        public static String getItemImage(String imageFull) {
            return "http://ddragon.leagueoflegends.com/cdn/6.21.1/img/item/" + imageFull;
        }

        /**
         * Get Rune Image
         */
        public static String getRuneImage(String runeImage) {
            return "http://ddragon.leagueoflegends.com/cdn/6.21.1/img/rune/" + runeImage;
        }
    }


    public static String loadJSONFromAsset(Context mContext, String fileName) {
        String json = null;
        try {
            InputStream is = mContext.getAssets().open("datas/" + fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static JSONObject parseChampionFromAsset(Context mContext, String fileName) {
        JSONObject jsonObject = null;
        JSONObject result = null;
        String json = loadJSONFromAsset(mContext, fileName);
        if (json != null) {
            try {
                jsonObject = new JSONObject(json);
                if (jsonObject != null) {
                    result = JsonUtil.getJsonData(jsonObject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static void loadAvatarNomal(Context context, String thumbAvatar, ImageView imageView, final View view1) {
        if (!isExceptionDestroy(context)) {
            Glide.with(context).load(thumbAvatar)
                    .skipMemoryCache(true)
                    .crossFade()
                    .into(new GlideDrawableImageViewTarget(imageView) {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                            super.onResourceReady(resource, animation);
                            if (null != view1) {
                                view1.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onLoadStarted(Drawable placeholder) {
                            super.onLoadStarted(placeholder);
                            if (null != view1) {
                                view1.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onLoadFailed(Exception e, Drawable errorDrawable) {
                            super.onLoadFailed(e, errorDrawable);
                            if (null != view1) {
                                view1.setVisibility(View.GONE);
                            }
                        }
                    });
        }


    }


    private static boolean isExceptionDestroy(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return ((Activity) context).isDestroyed();
        }
        return false;
    }

    public static List<String> getListFromString(String str) {
        List<String> result = null;
        if (!TextUtils.isEmpty(str)) {
            String[] temp = str.split(";");
            if (temp != null && temp.length > 0) {

                result = new ArrayList<>();
                for (int i = 0; i < temp.length; i++) {
                    result.add(temp[i]);
                }
            }
        }

        return result;
    }

    public static String getStringFromList(List<String> list) {
        String str = "";
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                str += list.get(i) + ";";
            }
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }


    public static void show(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


    public static void getDataBaseFromEmuler(Context context, String dbName) {
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "//data//data//" + context.getPackageName() + "//databases//" + dbName;
                String backupDBPath = dbName;
                File currentDB = new File(currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hidenKeyboard(Activity context) {
        if (null != context) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null && context.getCurrentFocus() != null && context.getCurrentFocus().getWindowToken() != null) {
                inputMethodManager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    public static void showKeyboard(Context context, EditText editText) {
        final InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        editText.requestFocus();
    }

    public static void showKeyboardToggle(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public static Bitmap getMasteryImageFromAssets(Context context, boolean isActive, String fileName) {
        Bitmap bitmap = null;
        try {
            AssetManager assetManager = context.getAssets();
            if (isActive) {
                fileName = "masteries_image/" + fileName;
            } else {
                fileName = "masteries_image/gray_" + fileName;
            }
            InputStream istr = null;

            istr = assetManager.open(fileName);

            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static void showMasteryDialog(final Activity activity, MasteryEnity masteryEnity) {
        View view = LayoutInflater.from(activity).inflate(R.layout.can_not_start_layout, null);
        ImageView imgTitle = (ImageView) view.findViewById(R.id.imageview_icon);
        TextView txtTitle = (TextView) view.findViewById(R.id.textview_title);
        TextView textViewMessage = (TextView) view.findViewById(R.id.textview_messsage);
        imgTitle.setImageBitmap(Utils.getMasteryImageFromAssets(activity, true, masteryEnity.getImage().getFull()));
        txtTitle.setText(masteryEnity.getName());
        textViewMessage.setText(Html.fromHtml(masteryEnity.getDescription().get(masteryEnity.getRanks() - 1)));

        final DialogPlus dialog = DialogPlus.newDialog(activity)
                .setContentHolder(new ViewHolder(view))
                .setGravity(Gravity.BOTTOM)
                .setExpanded(false)
                .setCancelable(true)
                .create();

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                });
            }
        }, 5000);

        dialog.show();

    }



}
