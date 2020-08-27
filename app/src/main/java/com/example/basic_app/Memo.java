package com.example.basic_app;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Memo implements Serializable {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.KOREA);
    private static final ArrayList<String> IMAGE_URLS = new ArrayList<String>() {
        {
            add("https://ww.namu.la/s/aeca2e14dbb78281beabffe6d5a8b1a84233da4aa2d7b857f11baa8530908b4faf2dae5ce55389c73821b2235cfa3c4d9744bb30d3edc6efda4a446164481c78fa96810116fac66e3f48349bd6ae2041434324fd2d713a12be8a89ff3c2029e590df43e574571827b946a7db8fb0ed06");
            add("https://img4.yna.co.kr/photo/cms/2019/05/02/02/PCM20190502000402370_P2.jpg");
            add("https://ppss.kr/wp-content/uploads/2015/07/16.jpg");
            add("https://www.nemopan.com/files/attach/images/6294/936/797/010/90bc7ddfa68f41c5da0ff4016af5fff9.jpg");
        }
    };

    public static String generateImageUrl() {
        int index = new Random().nextInt(IMAGE_URLS.size() - 1);
        return IMAGE_URLS.get(index);
    }

    public long id;
    public String title;
    public String contents;
    public String name;
    public String imageUrl;
    public long time;

    public String getTime() {
        return format.format(new Date(time));
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("id: %s, title: %s, contents: %s, name: %s, imageUrl: %s",
                id, title, contents, name, imageUrl);
    }
}