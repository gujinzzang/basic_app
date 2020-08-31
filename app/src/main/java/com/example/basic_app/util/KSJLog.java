package com.example.basic_app.util;

import android.util.Log;

/**
 * 로그 관리
 */
public class KSJLog {

    private static final String TAG = "KSJ";

    private static boolean DEBUG = true; // 로그 표시 여부
    private static int LEVEL = Log.VERBOSE;

    public static void v(String message) {
        v(TAG, message);
    }

    public static void v(String tag, String message) {
        if (DEBUG && LEVEL <= Log.VERBOSE)
            Log.v(TAG, buildMessage(tag, message));
    }

    public static void d(String message) {
        d(TAG, message);
    }

    public static void d(String tag, String message) {
        if (DEBUG && LEVEL <= Log.DEBUG)
            Log.d(TAG, buildMessage(tag, message));
    }

    public static void i(String message) {
        i(TAG, message);
    }

    public static void i(String tag, String message) {
        if (DEBUG && LEVEL <= Log.INFO)
            Log.i(TAG, buildMessage(tag, message));
    }

    public static void w(String message) {
        w(TAG, message);
    }

    public static void w(String tag, String message) {
        if (DEBUG && LEVEL <= Log.WARN)
            Log.w(TAG, buildMessage(tag, message));
    }

    public static void e(String message) {
        e(TAG, message);
    }

    public static void e(String tag, String message) {
        if (DEBUG && LEVEL <= Log.ERROR)
            Log.e(TAG, buildMessage(tag, message));
    }

    public static void a(String message) {
        a(TAG, message);
    }

    public static void a(String tag, String message) {
        if (DEBUG && LEVEL <= Log.ASSERT)
            Log.e(TAG, buildMessage(tag, message));
    }

    private static String buildMessage(String tag, String message) {
        StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
        String caller = "<unknown>";
        for (int i = 2; i < trace.length; i++) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(KSJLog.class)) {
                String callingClass = trace[i].getClassName();
                callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1);
                callingClass = callingClass.substring(0, callingClass.lastIndexOf('$') + 1);
                caller = callingClass + trace[i].getMethodName() + ":" + trace[i].getLineNumber();
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(caller); // 클래스명.함수명()
        if (message != null) {
            sb.append("] >>> ");
            sb.append(tag);
            sb.append(":: ");
            sb.append(message);
        } else {
            sb.append("] <<<");
        }

        return sb.toString();
    }
}
