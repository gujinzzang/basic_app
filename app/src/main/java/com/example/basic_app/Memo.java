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
            add("https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F99391E395AB083A906");
            add("https://img1.daumcdn.net/thumb/R720x0.q80/?scode=mtistory2&fname=http%3A%2F%2Fcfile24.uf.tistory.com%2Fimage%2F9984A4425E68B3C025D35E");
            add("http://theviewers.co.kr/Files/30/News/201912/1954_20191220102638897.JPG");
            add("https://file3.instiz.net/data/file3/2018/01/31/3/7/9/379f6ddda72b634b23a567a4b2217103.jpg");
        }
    };
    public static final String userimgurl = "https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F99391E395AB083A906";


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