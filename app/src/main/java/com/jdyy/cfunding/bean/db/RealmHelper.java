package com.jdyy.cfunding.bean.db;

import com.jdyy.cfunding.bean.Information;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Administrator on 2016/12/1 0001.
 */

public class RealmHelper {

    public static final String DB_NAME = "CFunding.realm";

    private Realm mRealm;
    private static RealmHelper instance;

    private RealmHelper() {
    }

    public static RealmHelper getInstance() {
        if (instance == null) {
            synchronized (RealmHelper.class) {
                if (instance == null)
                    instance = new RealmHelper();
            }
        }
        return instance;
    }


    protected Realm getRealm() {
        if (mRealm == null || mRealm.isClosed())
            mRealm = Realm.getDefaultInstance();
        return mRealm;
    }

    /**
     * 添加收藏记录
     * @param bean
     */
    public void insertInformation(Information bean) {
        getRealm().beginTransaction();
        getRealm().copyToRealm(bean);
        getRealm().commitTransaction();
    }

    /**
     * 清空收藏
     */
    public void deleteAllInformation() {
        getRealm().beginTransaction();
        getRealm().delete(Information.class);
        getRealm().commitTransaction();
    }

    /**
     * 查询 收藏记录
     * @param id
     * @return
     */
    public boolean queryInformationId(String id) {
        RealmResults<Information> results = getRealm().where(Information.class).findAll();
        for (Information item : results) {
            if (item.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 收藏列表
     * @return
     */
    public List<Information> getInformationList() {
        //使用findAllSort ,先findAll再result.sort排序
        RealmResults<Information> results = getRealm().where(Information.class).findAllSorted("time", Sort.DESCENDING);
        return getRealm().copyFromRealm(results);
    }

    public void deleteInformation(String id) {
        Information information = getRealm().where(Information.class).equalTo("id", id).findFirst();
        getRealm().beginTransaction();
        information.deleteFromRealm();
        getRealm().commitTransaction();
    }

}
