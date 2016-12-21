package data.gather.server;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * Created by dell on 2016/12/20.
 */
public class TencentNewService {
    public static void getPage(){
        try {
            Document doc  = Jsoup.connect("http://news.qq.com/society_index.shtml").get();
            Elements newElement = doc.select("div#news");
            Elements select = newElement.select("div.first").select("div.Q-tpList");
            for (Element element:select){
//                System.out.println(element);
                if(!StringUtils.isEmpty(element.select("img").attr("src"))){
                    System.out.println(element.select("img").attr("src"));
                }else{
                    System.out.println(element);
                }


            }
//            System.out.println(select);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getPageInfo(){

    }



    public static void main(String[] args) {
        getPage();
    }
}
