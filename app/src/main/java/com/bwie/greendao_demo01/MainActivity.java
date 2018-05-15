package com.bwie.greendao_demo01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.greendao_demo01.dao.DaoMaster;
import com.bwie.greendao_demo01.dao.DaoSession;
import com.bwie.greendao_demo01.dao.StudentDao;
import com.bwie.greendao_demo01.entity.Student;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity=====";
    private EditText et_name;
    private EditText et_age;
    private EditText et_id;
    private EditText et_money;
    private Button bt_insert;
    private Button bt_select;
    private Button bt_delete;
    private Button bt_update;
    private RecyclerView rlv;
    private MyAdapter adapter;
    private StudentDao studentDao;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this,"pwd.db",null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        studentDao = daoSession.getStudentDao();

    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_age = (EditText) findViewById(R.id.et_age);
        et_id = (EditText) findViewById(R.id.et_id);
        et_money = (EditText) findViewById(R.id.et_momey);
        bt_insert = (Button) findViewById(R.id.bt_insert);
        bt_insert.setOnClickListener(this);
        bt_select = (Button) findViewById(R.id.bt_select);
        bt_select.setOnClickListener(this);
        bt_delete = (Button) findViewById(R.id.bt_delete);
        bt_delete.setOnClickListener(this);
        bt_update = (Button) findViewById(R.id.bt_update);
        bt_update.setOnClickListener(this);
        rlv = (RecyclerView) findViewById(R.id.rlv);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_insert:
                insert();
                break;
            case R.id.bt_select:
                select();
                break;
            case R.id.bt_delete:
                delete();
                break;
            case R.id.bt_update:
                update();
                break;


            default:
                break;
        }
    }
    private void insert() {
        name = et_name.getText().toString().trim();
        String age = et_age.getText().toString().trim();
        String money=et_money.getText().toString().trim();


        Student student = new Student(null, name, age,money);
        studentDao.insert(student);
        Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();

        select();

    }

    private void select() {
        List<Student > users = studentDao.loadAll();

        for(int i = 0;i<users.size();i++){
            Long id = users.get(i).getId();
            Log.d(TAG, "select: "+id);
        }

        adapter = new MyAdapter(users,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rlv.setLayoutManager(linearLayoutManager);
        rlv.setAdapter(adapter);
    }
    private void delete(){
        String id = et_id.getText().toString().trim();

        if(id.isEmpty()){
            Toast.makeText(this,"请输入ID",Toast.LENGTH_SHORT).show();
        }else{
            studentDao.queryBuilder().where(StudentDao.Properties.Id.eq(id)).buildDelete().executeDeleteWithoutDetachingEntities();
            Toast.makeText(this,"删除成功",Toast.LENGTH_SHORT).show();
        }
        select();
    }
    private void update(){
        String id = et_id.getText().toString().trim();

        long ids = Long.parseLong(id);

        String s = et_name.getText().toString();

        String age = et_age.getText().toString();
        String money=et_money.getText().toString();


        Student student = new Student(ids,s,age,money);
        studentDao.update(student);

        Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
        select();;
    }
}
