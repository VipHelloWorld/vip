package data.gather.server;

import com.google.common.collect.Sets;
import data.gather.constant.SinaConstant;
import data.gather.util.HtmlUtil;
import data.gather.util.JsonObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import com.yoyo.spider.utils.HttpTools;

/**
 * Created by dell on 2016/12/15.
 */
public class SinaService {

    private static JsonObjectMapper jsonObjectMapper = new JsonObjectMapper();
    private static final String PREPAGE = "http://weibo.com/?category=7";//按字母查找品牌 车系 数据时 拼接品牌链接前缀
//    private static final String TAILHTML = ".html";//按字母查找品牌 车系 数据时 拼接品牌链接后缀
//    private static final String TEXTFLAG = "暂无";//没有据字样


    //按照英文字母 获取某字幕开头下的品牌数据
    public static Set<String> getSinapages() {
        Set<String> set = Sets.newHashSet();
        try {
            Map<String, String> cookies = SinaConstant.getCookie();
            Document doc = Jsoup.connect(PREPAGE).cookies(cookies).get();
            System.out.println(doc);
            Elements eles = doc.getElementsByTag("script");
//            System.out.println("--"+eles.text());
            if(eles ==null || eles.size()==0){
                return null;
            }
            for (Element ele:eles) {
                if (ele.toString().contains("\"ns\":\"pl_unlogin_home_feed\"")) {
//                    String regex = "\\<div class\\=\\\\\"subinfo_box clearfix\\\\\">(.*?)<\\\\/div>";
                    String res1 = "\\<li class\\=\\\\\"pt_li pt_li_2 S_bg2\\\\\"(.*?)li>";
                    String res2 = "\\<li class\\=\\\\\"pt_li pt_li_1 S_bg2\\\\\"(.*?)li>";
                    Matcher m1 = Pattern.compile(res1).matcher(ele.toString());
//                    Matcher m = Pattern.compile("\\<li class\\=(.*?)li>").matcher(ele.toString());
                    while (m1.find()) {
//                        System.out.println(m1.group());
                        set.add(m1.group());
                    }
                    Matcher m2 = Pattern.compile(res2).matcher(ele.toString());
                    while (m2.find()) {
//                        System.out.println(m2.group());
                        set.add(m2.group());
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }

    public static void getInfo(){
        Set<String> sinaPages = getSinapages();
        for (String str:sinaPages){
            //获取链接
            String url = getPageInfo(str);
//            System.out.println(url);


        }
    }
    //获取标题
    public static String getPageInfo(String str){
        String url = "";
        String img = "";
        String chatCount ="";
        String title = "";
        Document jsoup = Jsoup.parse(str);
        //获取url
        Elements elements = jsoup.getElementsByTag("li");
        url = elements.get(0).attr("href");
        url = url.replaceAll("\\\\\"","").replace("\\/","/");
        Elements contentElement = elements.select("li[pt_li_1]");
        //如果 class 为 'pt_li_1'类型
        if(!contentElement.isEmpty()){
            //获取标题图片
            img = contentElement.select("img").attr("src");
            img = img.replaceAll("\\\\\"","").replace("\\/","/");
//            System.err.println(contentElement);
            //获取标题
            Elements titleElement = contentElement.select("div[text_cut2]");
            title = HtmlUtil.getTextFromHtml(titleElement.text());
            if(title!=""||title!=null){
//                System.out.println(title);
                title = title.substring(0,title.indexOf("\\n")+1);
            }
            if(titleElement.size()!=0){
                titleElement = contentElement.select("div[text_cut]");
                title = HtmlUtil.getTextFromHtml(titleElement.text());
                if(title!=""||title!=null){
                    System.out.println(title);
                    title = title.substring(0,title.indexOf("\\n")+1);
                }
            }
            //获取评论数量
            Element chatCountElement = contentElement.select("span[s_txt2\\\"]").get(1);
            Document parse = Jsoup.parse(chatCountElement.html());
            String count = parse.getElementsByTag("em").get(3).text();
            count = count.substring(0,count.indexOf("<"));

        }
        return url;
    }

    //获取讨论量
    //获取标题图片




    public static String match(String source, String element, String attr) {
        String result = "";
        String reg = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
        Matcher m = Pattern.compile(reg).matcher(source);
        while (m.find()) {
            String r = m.group(1);
            result = r;
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        getInfo();
    }
}
