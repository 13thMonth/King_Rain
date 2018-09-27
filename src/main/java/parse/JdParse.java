package parse;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import model.JdModel;
import model.RoomModel;
/*
 * author qianyang 1563178220@qq.com
 * 用于将上面传下来的html解析，获取我们需要的内容
 * 解析方式，采用Jsoup解析，有不明白Jsoup的可以上网搜索API文档
 * Jsoup是一款很简单的html解析器
 */
public class JdParse {
    public static List<JdModel> getData (String html) throws Exception{
        //获取的数据，存放在集合中
        List<JdModel> data = new ArrayList<JdModel>();
        //采用Jsoup解析
        Document doc = Jsoup.parse(html);
        //获取html标签中的内容
        Elements elements=doc.select("ul[class=gl-warp clearfix]").select("li[class=gl-item]");
        for (Element ele:elements) {
            String bookID=ele.attr("data-sku");
            String bookPrice=ele.select("div[class=p-price]").select("strong").select("i").text();
            String bookName=ele.select("div[class=p-name]").select("em").text();
            String bookImage=ele.select("div[class=p-img]").select("img").attr("source-data-lazy-img");
            //创建一个对象，这里可以看出，使用Model的优势，直接进行封装
            JdModel jdModel=new JdModel();
            //对象的值
            jdModel.setBookID(bookID);
            jdModel.setBookName(bookName);
            jdModel.setBookPrice(bookPrice);
            jdModel.setBookImage(bookImage);
            //将每一个对象的值，保存到List集合中
            data.add(jdModel);
        }
        //返回数据
        return data;
    }
    
    public static List<RoomModel> getRoomData (String html) throws Exception{
        //获取的数据，存放在集合中
        List<RoomModel> data = new ArrayList<RoomModel>();
        //采用Jsoup解析
        Document doc = Jsoup.parse(html);
        //获取html标签中的内容
        Elements elements=doc.select("ul[id=houseList]").select("li[class=clearfix]");
        for (Element ele:elements) {
            String roomTitle = ele.select("div[class=img pr]").select("a").select("img").attr("src");//标题
            String roomDescribe = ele.select("div[class=txt]").select("h3").select("a").text();
            String roomAddress =  ele.select("div[class=txt]").select("div[class=detail]").select("p").select("span").text();
            String price = ele.select("div[class=priceDetail]").select("p[class=price]").select("span[class=num]").text();
            Double roomPrice = 0D;
            if(price ==null||price.equals("")){
                roomPrice = 4000D;
            }else{
                roomPrice = Double.parseDouble(price);
            }
            String roomRent = ele.select("div[class=txt]").select("h4").select("a").text();
            String subway = ele.select("div[class=txt]").select("p[class=room_tags clearfix]").select("span[class=subway]").text();
            String balcony = ele.select("div[class=txt]").select("p[class=room_tags clearfix]").select("span[class=balcony]").text();
            String roomLabel =subway+","+balcony;
            //创建一个对象，这里可以看出，使用Model的优势，直接进行封装
            RoomModel roomModel=new RoomModel();
            //对象的值
            roomModel.setRoomDescribe(roomDescribe);
            roomModel.setRoomTitle(roomTitle);
            roomModel.setRoomRent(roomRent);
            roomModel.setRoomAddress(roomAddress);
            roomModel.setRoomPrice(roomPrice);
            roomModel.setRoomLabel(roomLabel);
            //将每一个对象的值，保存到List集合中
            data.add(roomModel);
        }
        //返回数据
        return data;
    }
}

