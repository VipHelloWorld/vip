package data.gather.server;

import com.google.common.collect.Sets;
import data.gather.constant.SinaConstant;
import data.gather.dao.Infohandle;
import data.gather.model.InfoEntity;
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


    public static Set<String> getSinapages() {
        Set<String> set = Sets.newHashSet();
        try {
            Map<String, String> cookies = SinaConstant.getCookie();
            Document doc = Jsoup.connect(PREPAGE).cookies(cookies).timeout(1000).get();
            Elements eles = doc.getElementsByTag("script");
            if (eles == null || eles.size() == 0) {
                return null;
            }
            for (Element ele : eles) {
                String eleString = ele.toString();
                if (eleString.contains("\"ns\":\"pl_unlogin_home_feed\"")) {
                    eleString = eleString.substring(eleString.indexOf("<div"), eleString.lastIndexOf("\"})"));
                    Document parseEle = Jsoup.parse(eleString);
//                    System.out.println(parseEle);
                    Elements ulNum = parseEle.select("ul[pagenum]");
//                    System.out.println("ul size:"+ulNum.size());
                    Elements lis = ulNum.select("li[pt_li_1]");
//                    System.out.println("pt1:"+lis.size());
                    for (Element element : lis) {
                        set.add(element.toString());
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }

    public static void getInfo() {
        Set<String> sinaPages = getSinapages();
        for (String str : sinaPages) {
            //获取链接
            InfoEntity infoEntity = getPageInfo(str);
            int insert = Infohandle.insert(infoEntity);
            System.out.println(insert);
        }
    }


    public static InfoEntity getPageInfo(String str) {
        String url = "";
        List imgList = new ArrayList();
        String img = "";
        String chatCount = "";
        String title = "";
        InfoEntity infoEntity = new InfoEntity();
        infoEntity.setSource(0);
        Document jsoup = Jsoup.parse(str);
        //获取url
        Elements elements = jsoup.getElementsByTag("li");
        url = elements.get(0).attr("href");
        url = url.replaceAll("\\\\\"", "").replace("\\/", "/");
        infoEntity.setUrl(url);
        Elements contentElement = elements.select("li[pt_li_1]");
        //如果 class 为 'pt_li_1'类型
        if (!contentElement.isEmpty()) {
            //获取标题图片
            img = contentElement.select("img").attr("src");
            img = img.replaceAll("\\\\\"", "").replace("\\/", "/");
            imgList.add(img);
            infoEntity.setImg(imgList);

            //获取标题
            Elements titleElement = contentElement.select("div[text_cut2]");
            title = HtmlUtil.getTextFromHtml(titleElement.text());
            if ((title != "" || title != null) && title.contains("\\n")) {
                title = title.substring(0, title.indexOf("\\n"));
            }
            titleElement = contentElement.select("div[text_cut]");
            if (titleElement.size() != 0) {
                title = HtmlUtil.getTextFromHtml(titleElement.text());
                if (title != "" || title != null) {
                    title = title.substring(0, title.indexOf("\\n") + 1);
                }
            }
            infoEntity.setTitle(title);
            //获取评论数量
            Element chatCountElement = contentElement.select("span[s_txt2\\\"]").get(1);
            Document parse = Jsoup.parse(chatCountElement.html());
            chatCount = parse.getElementsByTag("em").get(3).text();
            chatCount = chatCount.substring(0, chatCount.indexOf("<"));
            infoEntity.setChatCount(chatCount);
            System.out.println(infoEntity);
            infoEntity.setSource(0);
        }
        return infoEntity;
    }


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
