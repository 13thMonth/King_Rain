package db;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
/*   
 *  合肥工业大学 管理学院 qianyang 1563178220@qq.com
 */
public class MyDataSource {
    public static DataSource getDataSource(String connectURI){
        BasicDataSource ds = new BasicDataSource();
         //MySQL的jdbc驱动
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername("root");              //所要连接的数据库名
        ds.setPassword("123456");                //MySQL的登陆密码
//        ds.setUsername("JRTMS");
//        ds.setPassword("X9n866");
        ds.setUrl(connectURI);
        return ds;
    }
}