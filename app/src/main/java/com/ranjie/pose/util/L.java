package com.ranjie.pose.util;

import android.util.Log;

import com.ranjie.pose.BuildConfig;
import com.ranjie.pose.app.App;

/**
 * Created by quzhiyong on 2018/12/10
 */
public class L {

    // 每行最多显示的字符数
    private static final int LINE_MAX_LENGTH = 2000;
    // package name
    private static String TAG = App.getApplication().getPackageName();
    // debug
    private static boolean DEBUG = BuildConfig.DEBUG;

    // 日志锁
    private static final Object LOCK = new Object();

    /**
     * 打印调试信息
     *
     * @param message
     */
    public static void d(String message) {
        if (DEBUG) {
            d(TAG, message);
        }
    }

    /**
     * 打印调试信息
     *
     * @param tag
     * @param message
     */
    public static void d(String tag, String message) {
        if (DEBUG) {
            d(tag, message, null);
        }
    }

    public static void debug(String tag, String format, Object... args) {
        if (DEBUG) {
            d(tag, String.format(format, args), null);
        }
    }

    /**
     * 打印调试信息
     *
     * @param tag
     * @param message
     * @param tr
     */
    public static void d(String tag, String message, Throwable tr) {
        if (DEBUG) {
            String[] strings = makeList(message);
            if (strings.length == 1) {
                Log.d(tag, strings[0], tr);
            } else {
                synchronized (LOCK) {
                    for (String string : strings) {
                        Log.d(tag, string, tr);
                    }
                }
            }
        }
    }

    /**
     * 打印Info信息
     *
     * @param message
     */
    public static void i(String message) {
        if (DEBUG) {
            i(TAG, message);
        }
    }

    /**
     * 打印Info信息
     *
     * @param tag
     * @param message
     */
    public static void i(String tag, String message) {
        if (DEBUG) {
            i(tag, message, null);
        }
    }

    public static void info(String tag, String format, Object... args) {
        if (DEBUG) {
            i(tag, String.format(format, args), null);
        }
    }

    /**
     * 打印Info信息
     *
     * @param tag
     * @param message
     * @param tr
     */
    public static void i(String tag, String message, Throwable tr) {
        if (DEBUG) {
            String[] strings = makeList(message);
            if (strings.length == 1) {
                Log.i(tag, strings[0], tr);
            } else {
                synchronized (LOCK) {
                    for (String string : strings) {
                        Log.i(tag, string, tr);
                    }
                }
            }
        }
    }

    /**
     * 打印错误信息
     *
     * @param message
     */
    public static void e(String message) {
        if (DEBUG) {
            e(TAG, message);
        }
    }

    /**
     * 打印错误信息
     *
     * @param tag
     * @param message
     */
    public static void e(String tag, String message) {
        if (DEBUG) {
            e(tag, message, null);
        }
    }

    /**
     * 打印错误信息
     *
     * @param message
     * @param tr
     */
    public static void e(String message, Throwable tr) {
        if (DEBUG) {
            e(TAG, message, tr);
        }
    }

    public static void error(String tag, String format, Object... args) {
        if (DEBUG) {
            e(tag, String.format(format, args), null);
        }
    }

    /**
     * 打印错误信息
     *
     * @param tag
     * @param message
     * @param tr
     */
    public static void e(String tag, String message, Throwable tr) {
        if (DEBUG) {
            String[] strings = makeList(message);
            if (strings.length == 1) {
                Log.e(tag, strings[0], tr);
            } else {
                synchronized (LOCK) {
                    for (String string : strings) {
                        Log.e(tag, string, tr);
                    }
                }
            }
        }
    }

    private static String[] makeList(Object object) {
        String string = String.valueOf(object);
        int start, end, index, length = string.length();
        int lines = length / LINE_MAX_LENGTH + 1;
        String[] strings = new String[lines];
        for (index = 0; index < lines; index++) {
            start = index * LINE_MAX_LENGTH;
            if (index == lines - 1) {
                end = length;
            } else {
                end = (index + 1) * LINE_MAX_LENGTH;
            }
            strings[index] = string.substring(start, end);
        }
        return strings;
    }

}
