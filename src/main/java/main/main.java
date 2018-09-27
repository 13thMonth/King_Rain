package main;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import db.MYSQLControl;
import model.JdModel;
import model.RoomModel;
import util.URLFecter;

public class main {
    //log4j的是使用，不会的请看之前写的文章
    static final Log logger = LogFactory.getLog(main.class);
    public static void main(String[] args) throws Exception{
        //getBook();
        getRoom();
    }
    public static void getBook() throws Exception {
        //初始化一个httpclient
        HttpClient client = new DefaultHttpClient();
        //我们要爬取的一个地址，这里可以从数据库中抽取数据，然后利用循环，可以爬取一个URL队列
        String url="http://search.jd.com/Search?keyword=Python&enc=utf-8&book=y&wq=Python&pvid=33xo9lni.p4a1qb";
        //抓取的数据
        List<JdModel> bookdatas=URLFecter.URLParser(client, url);
        //循环输出抓取的数据
        for (JdModel jd:bookdatas) {
            logger.info("bookID:"+jd.getBookID()+"\t"+"bookPrice:"+jd.getBookPrice()+"\t"+"bookName:"+jd.getBookName());
        }
        //将抓取的数据插入数据库
        MYSQLControl.executeInsert(bookdatas);
    }
    
    public static void getRoom() throws Exception {
        //初始化一个httpclient
        HttpClient client = new DefaultHttpClient();
        //我们要爬取的一个地址，这里可以从数据库中抽取数据，然后利用循环，可以爬取一个URL队列
        String url="http://www.ziroom.com/z/nl/z2.html?utm_source=baidu&utm_medium=cpc&utm_term=%E7%BD%91%E7%A7%9F%E6%88%BF&utm_content=ZFDYBJ14632&utm_campaign=PC-ZFJHBJ30";
        //抓取的数据
        List<RoomModel> roomdatas=URLFecter.URLParserForRoom(client, url);
        //循环输出抓取的数据
        for (RoomModel jd:roomdatas) {
            logger.info("roomID:"+jd.getRoomId());
        }
        //将抓取的数据插入数据库
        MYSQLControl.executeRoomInsert(roomdatas);
    }
}
