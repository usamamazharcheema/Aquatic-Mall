package com.example.androiddeveloper.aquatic_mall.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 10/10/2017.
 */

public class DtabaeHelper extends SQLiteOpenHelper {
    public static final String database_name="bizventureaquatic.db";
    public static final String aquatic_login_table="aquatic_login_table";
    public static final String aquatic_email="EMAIL";
    public static final String aquatic_name="NAME";
    public static final String aquatic_code="CODE";
    public static final String aquatic_type="TYPE";
    public static final String aquatic_phone="PHONE";
    public static final String aquatic_cnic="CNIC";

    public static final String usertable="usertable";
    public static final String message="MESSAGE";
    public static final String admintable="admintable";
    public static final String adminmessage="ADMINMESSAGE";
    public static final String adminemail="ADMINEMAIL";



    public static final String SONOF="SONOF ";
    public static final String ADDRESS="ADDRESS";
    public static final String OFFICE="OFFICENO";
    public static final String PROFESION="PROFESSION";
    public static final String IMG="IMG";
    public static final String DATE="DATE";
















    public static final String table_name="login";
    public static final String loginvalue="EMAIL";
    public static final String type="UTYPE";
    public static final String table_nametype="utypev";

    public DtabaeHelper(Context context) {
        super(context, database_name, null, 2);

    }

    @Override
    public String getDatabaseName() {
        return super.getDatabaseName();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL("create table "+ table_name +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,EMAIL TEXT) ");
        sqLiteDatabase.execSQL("create table "+ table_nametype +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,UTYPE TEXT)");
       sqLiteDatabase.execSQL("create table "+ aquatic_login_table +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,EMAIL TEXT,NAME TEXT,CODE TEXT,TYPE TEXT,PHONE TEXT,CNIC TEXT,SONOF TEXT,ADDRESS TEXT,PROFESSION TEXT,OFFICENO TEXT,IMG TEXT,DATE TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

/// If you need to add a column
        if (i1 > i) {
            sqLiteDatabase.execSQL("ALTER TABLE aquatic_login_table ADD COLUMN SONOF TEXT");
            sqLiteDatabase.execSQL("ALTER TABLE aquatic_login_table ADD COLUMN ADDRESS TEXT");
            sqLiteDatabase.execSQL("ALTER TABLE aquatic_login_table ADD COLUMN PROFESSION TEXT");
            sqLiteDatabase.execSQL("ALTER TABLE aquatic_login_table ADD COLUMN OFFICENO TEXT");
            sqLiteDatabase.execSQL("ALTER TABLE aquatic_login_table ADD COLUMN IMG TEXT");
            sqLiteDatabase.execSQL("ALTER TABLE aquatic_login_table ADD COLUMN DATE TEXT");


        }


    }



    public boolean insertitem(String email)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues newcontent=new ContentValues();

        newcontent.put(loginvalue,email);

        long result=db.insert(table_name,null,newcontent);

        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }
    public Cursor checkitems()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from " +table_name,null);
        return  res;
    }
    public boolean updateitems(String id, String update1)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues newcontent=new ContentValues();
        newcontent.put(loginvalue,update1);

        db.update(table_name,newcontent, "ID = ? ",new String[] {id});
        return true;



    }







    public boolean inserttype(String email)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues newcontent=new ContentValues();

        newcontent.put(type,email);

        long result=db.insert(table_nametype,null,newcontent);

        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }
    public Cursor checktype()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from " +table_nametype,null);
        return  res;
    }
    public boolean updatetype(String id, String update1)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues newcontent=new ContentValues();
        newcontent.put(type,update1);

        db.update(table_nametype,newcontent, "ID = ? ",new String[] {id});
        return true;



    }







    public boolean insertauatic(String email,String name,String code,String type,String phone,String cnic,String sonof,String date,String img,String profession,String officeno,String address)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues newcontent=new ContentValues();

        newcontent.put(aquatic_email,email);
        newcontent.put(aquatic_name,name);
        newcontent.put(aquatic_code,code);
        newcontent.put(aquatic_type,type);
        newcontent.put(aquatic_phone,phone);
        newcontent.put(aquatic_cnic,cnic);

        newcontent.put(SONOF,sonof);
        newcontent.put(DATE,date);
        newcontent.put(IMG,img);
        newcontent.put(PROFESION,profession);
        newcontent.put(OFFICE,officeno);
        newcontent.put(ADDRESS,address);




        long result=db.insert(aquatic_login_table,null,newcontent);

        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }
    public Cursor checkaquatic()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from " +aquatic_login_table,null);
        return  res;
    }
    public boolean updateaquatic(String id, String email,String name,String code,String type,String phone,String cnic,String sonof,String date,String img,String profession,String officeno,String address)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues newcontent=new ContentValues();
        newcontent.put(aquatic_email,email);
        newcontent.put(aquatic_name,name);
        newcontent.put(aquatic_code,code);
        newcontent.put(aquatic_type,type);
        newcontent.put(aquatic_phone,phone);
        newcontent.put(aquatic_cnic,cnic);

        newcontent.put(SONOF,sonof);
        newcontent.put(DATE,date);
        newcontent.put(IMG,img);
        newcontent.put(PROFESION,profession);
        newcontent.put(OFFICE,officeno);
        newcontent.put(ADDRESS,address);

        db.update(aquatic_login_table,newcontent, "ID = ? ",new String[] {id});
        return true;



    }
























}
