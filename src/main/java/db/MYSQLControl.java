package db;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;

import model.JdModel;
import model.RoomModel;
/*
 * author qianyang 1563178220@qq.com
 * Mysql操作的QueryRunner方法
 * 一个数据库操作类，别的程序直接调用即可
 */
public class MYSQLControl {

    //根据自己的数据库地址修改
    static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://127.0.0.1:3306/wrj?characterEncoding=utf-8");
   // static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://101.201.57.127:3306/test");
    static QueryRunner qr = new QueryRunner(ds);
    //第一类方法
    public static void executeUpdate(String sql){
        try {
            qr.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //第二类数据库操作方法
    public static void executeInsert(List<JdModel> jingdongdata) throws SQLException {
        /*
         * 定义一个Object数组，行列
         * 3表示列数，根据自己的数据定义这里面的数字
         * params[i][0]等是对数组赋值，这里用到集合的get方法
         * 
         */
        Object[][] params = new Object[jingdongdata.size()][4];
        for ( int i=0; i<params.length; i++ ){
            params[i][0] = jingdongdata.get(i).getBookID();
            params[i][1] = jingdongdata.get(i).getBookName();
            params[i][2] = jingdongdata.get(i).getBookPrice();
            params[i][3] = jingdongdata.get(i).getBookImage();
        }
        qr.batch("insert into jingdongbook (BOOK_ID, BOOK_NAME, BOOK_PRICE,BOOK_IMAGE)"
                + "values (?,?,?,?)", params);
        System.out.println("执行数据库完毕！"+"成功插入数据："+jingdongdata.size()+"条");

    }
    
    //第二类数据库操作方法
    public static void executeRoomInsert(List<RoomModel> roomdata) throws SQLException {
        /*
         * 定义一个Object数组，行列
         * 3表示列数，根据自己的数据定义这里面的数字
         * params[i][0]等是对数组赋值，这里用到集合的get方法
         * 
         */
        Object[][] params = new Object[roomdata.size()][6];
        for ( int i=0; i<params.length; i++ ){
            params[i][0] = roomdata.get(i).getRoomTitle();
            params[i][1] = roomdata.get(i).getRoomDescribe();
            params[i][2] = roomdata.get(i).getRoomAddress();
            params[i][3] = roomdata.get(i).getRoomPrice();
            params[i][4] = roomdata.get(i).getRoomRent();
            params[i][5] = roomdata.get(i).getRoomLabel();
        }
        qr.batch("insert into room (ROOM_TITLE, ROOM_DESCRIBE, ROOM_ADDRESS,ROOM_PRICE,ROOM_RENT,ROOM_LABEL)"
                + "values (?,?,?,?,?,?)", params);
        System.out.println("执行数据库完毕！"+"成功插入数据："+roomdata.size()+"条");

    }
}
