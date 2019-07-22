package com.example.myprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class CompanyProvider extends ContentProvider {
SQLiteDatabase mydb;
    public static final String db_name="company";
    public static final String table_name="empdetails";
    public static final int db_version=1;

    public CompanyProvider() {
    }
        public static final String Authority="com.mounika.my.company.provider";
        public static final Uri Content_uri=Uri.parse("content://"+Authority+"/emp");
        static int emp=1;
        static int emp_id=2;
        static UriMatcher myUri = new UriMatcher(UriMatcher.NO_MATCH);

        static {
        myUri.addURI(Authority,"empdetails",emp);
        myUri.addURI(Authority,"empdetails/#",emp_id);

    }
    public class myowndb extends SQLiteOpenHelper
    {
        public myowndb(Context context) {
            super(context,db_name,null,db_version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
sqLiteDatabase.execSQL("create table "+table_name+"(id integer primary key autoincrement,emp_name text,emp_profile text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
sqLiteDatabase.execSQL("drop table if exists "+table_name);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues val) {
        // TODO: Implement this to handle requests to insert a new row.
long row = mydb.insert(table_name,null,val);
if(row>0)
{
    uri= ContentUris.withAppendedId(Content_uri,row);
    getContext().getContentResolver().notifyChange(uri,null);

}

        return  uri;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        myowndb myhelper = new myowndb(getContext());
        mydb=myhelper.getWritableDatabase();
        if(mydb!=null)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder myquery = new SQLiteQueryBuilder();
        myquery.setTables(table_name);
         Cursor cr=myquery.query(mydb,null,null,null,null,null,"");
        cr.setNotificationUri(getContext().getContentResolver(),uri);
         return cr;
        // TODO: Implement this to handle query requests from clients.
    }
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
