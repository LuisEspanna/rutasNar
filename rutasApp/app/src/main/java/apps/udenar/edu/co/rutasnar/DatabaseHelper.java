package apps.udenar.edu.co.rutasnar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import apps.udenar.edu.co.rutasnar.model.User;


public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME ="register.db";

    public static final String TABLE_NAME ="registeruser";

    public static final String COL_1 ="id_usuario";

    public static final String COL_2 ="nom_usuario";

    public static final String COL_3 ="clave_usuario";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE registeruser (id_usuario, nom_usuario varchar, clave_usuario varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long addUser(User user){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,user.getIdUsuario());
        contentValues.put(COL_2,user.getNomUsuario());
        contentValues.put(COL_3,user.getClaveUsuario());
        long res = db.insert(TABLE_NAME,null,contentValues);
        db.close();
        return  res;
    }

    public void resetDB(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = String.format("DELETE FROM %s", TABLE_NAME);
        db.execSQL(query);
    }

    public boolean checkUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = String.format("SELECT * FROM %s", TABLE_NAME);

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            String id = cursor.getString(0);

            if(id != null){
                if(id.compareTo("0")!=0){
                    return true;
                }
            }
        }
        return false;
    }
}