package com.ranjie.http.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ranjie.http.RxRetrofitApp;
import com.ranjie.http.download.DaoMaster;
import com.ranjie.http.download.DaoSession;
import com.ranjie.http.http.cookie.CookieResult;
import com.ranjie.http.http.cookie.CookieResultDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * 数据缓存
 * 数据库工具类-geendao运用
 * Created by WZG on 2016/10/25.
 */
public class CookieDbUtil {
    private static CookieDbUtil db;
    private final static String dbName = "pose_db";
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    public CookieDbUtil() {
        context = RxRetrofitApp.getApplication();
        openHelper = new DaoMaster.DevOpenHelper(context, dbName);
    }

    /**
     * 获取单例
     */
    public static CookieDbUtil getInstance() {
        if (db == null) {
            synchronized (CookieDbUtil.class) {
                if (db == null) {
                    db = new CookieDbUtil();
                }
            }
        }
        return db;
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

    public void saveCookie(CookieResult info) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        CookieResultDao downInfoDao = daoSession.getCookieResultDao();
        downInfoDao.insert(info);
    }

    public void updateCookie(CookieResult info) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        CookieResultDao downInfoDao = daoSession.getCookieResultDao();
        downInfoDao.update(info);
    }

    public void deleteCookie(CookieResult info) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        CookieResultDao downInfoDao = daoSession.getCookieResultDao();
        downInfoDao.delete(info);
    }

    public CookieResult queryCookieBy(String url) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        CookieResultDao downInfoDao = daoSession.getCookieResultDao();
        QueryBuilder<CookieResult> qb = downInfoDao.queryBuilder();
        qb.where(CookieResultDao.Properties.Url.eq(url));
        List<CookieResult> list = qb.list();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public List<CookieResult> queryCookieAll() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        CookieResultDao downInfoDao = daoSession.getCookieResultDao();
        QueryBuilder<CookieResult> qb = downInfoDao.queryBuilder();
        return qb.list();
    }
}
