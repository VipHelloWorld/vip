package data.gather.server;

import java.util.Set;
import java.util.regex.Pattern;

//import com.yoyo.spider.utils.HttpTools;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.google.common.collect.Sets;

/**
 * Created by dell on 2016/12/15.
 */
public class SinaService {

    private static final String PREPAGE = "http://weibo.com/?category=7";//按字母查找品牌 车系 数据时 拼接品牌链接前缀
//    private static final String TAILHTML = ".html";//按字母查找品牌 车系 数据时 拼接品牌链接后缀
//    private static final String TEXTFLAG = "暂无";//没有据字样


    //按照英文字母 获取某字幕开头下的品牌数据
    public static Set<String> getBrandPages(){
        Set<String> set = Sets.newHashSet();
        try {
            Document doc = Jsoup.connect(PREPAGE).get();
            System.out.println(doc.text());
//            Elements eles = doc.select("div.pic_txt clearfix");
//            System.out.println("--"+eles.text());
//            for (Element ele : eles) {
//                set.add(ele.text());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }


//    // 获取某个字母下的品牌车 下的 所有车系id
//    private Set<String> gatherCarsIds(String url) {
//        String result = HttpTools.sendGet(url, "", "gb2312");
//        Document doc = Jsoup.parse(result);
//        Elements eles = doc.select("dl");
//        Set sets = Sets.newHashSet();
//        for (Element ele : eles) {
//            for (Element ele1 : ele.select("dd").select("li")) {
//                if (!"dashline".equals(ele1.attr("class"))) {
//                    if (!ele1.text().contains(this.TEXTFLAG)) {
//                        String id = Pattern.compile("[^0-9]").matcher(ele1.attr("id")).replaceAll("").trim(); // 车系id
//                        sets.add(id);
//                    }
//                }
//            }
//        }
//        return sets;
//    }
//
//    public Set<String> gatherCarsIds(){
//        Set<String> urls=  getBrandPages();
//        Set<String> carsIds = Sets.newHashSet();
//        for (String url : urls) {
//            carsIds.addAll(gatherCarsIds(url));
//        }
//        return carsIds;
//    }
//
    public static void main(String[] args) {
        getBrandPages();
    }

}
