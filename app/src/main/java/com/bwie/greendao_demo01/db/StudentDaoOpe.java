package com.bwie.greendao_demo01.db;

import android.content.Context;


import com.bwie.greendao_demo01.dao.StudentDao;
import com.bwie.greendao_demo01.entity.Student;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;


public class StudentDaoOpe {

    /**
     * 添加数据至数据库
     *
     * @param context
     * @param StudentBean
     */
    public static void insertData(Context context, Student StudentBean) {
        DbManager.getDaoSession(context).getStudentDao().insert(StudentBean);
    }


    /**
     * 将数据实体通过事务添加至数据库
     *
     * @param context
     * @param list
     */
    public static void insertData(Context context, List<Student> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        DbManager.getDaoSession(context).getStudentDao().insertInTx(list);
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     *
     * @param context
     * @param StudentBean
     */
    public static void saveData(Context context, Student StudentBean) {
        DbManager.getDaoSession(context).getStudentDao().save(StudentBean);
    }

    /**
     * 删除数据至数据库
     *
     * @param context
     * @param StudentBean 删除具体内容
     */
    public static void deleteData(Context context, Student StudentBean) {
        DbManager.getDaoSession(context).getStudentDao().delete(StudentBean);
    }

    /**
     * 根据id删除数据至数据库
     *
     * @param context
     * @param id      删除具体内容
     */
    public static void deleteByKeyData(Context context, long id) {
        DbManager.getDaoSession(context).getStudentDao().deleteByKey(id);
    }

    /**
     * 删除全部数据
     *
     * @param context
     */
    public static void deleteAllData(Context context) {
        DbManager.getDaoSession(context).getStudentDao().deleteAll();
    }

    /**
     * 更新数据库
     *
     * @param context
     * @param StudentBean
     */
    public static void updateData(Context context, Student StudentBean) {
        DbManager.getDaoSession(context).getStudentDao().update(StudentBean);
    }


    /**
     * 查询所有数据
     *
     * @param context
     * @return
     */
    public static List<Student> queryAll(Context context) {
        QueryBuilder<Student> builder = DbManager.getDaoSession(context).getStudentDao().queryBuilder();

        return builder.build().list();
    }



    /**
     *  分页加载
     * @param context
     * @param pageSize 当前第几页(程序中动态修改pageSize的值即可)
     * @param pageNum  每页显示多少个
     * @return
     */
    public static List<Student> queryPaging( int pageSize, int pageNum,Context context){
        StudentDao StudentBeanDao = DbManager.getDaoSession(context).getStudentDao();
        List<Student> listMsg = StudentBeanDao.queryBuilder()
                .offset(pageSize * pageNum).limit(pageNum).list();
        return listMsg;
    }
}
