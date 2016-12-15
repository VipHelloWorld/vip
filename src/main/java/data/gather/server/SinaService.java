package data.gather.server;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.google.common.collect.Sets;
import data.gather.constant.SinaConstant;
import data.gather.util.AbstractGather;
import data.gather.util.JsonObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Map;
import java.util.Set;

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
    public static Set<String> getBrandPages(){
        Set<String> set = Sets.newHashSet();
        try {
            Map<String, String> cookies = SinaConstant.getCookie();
            Document doc = Jsoup.connect(PREPAGE).cookies(cookies).get();
//            System.out.println(doc);
            Elements eles = doc.getElementsByTag("script");
//            System.out.println("--"+eles.text());
            for (Element ele : eles) {
                if(ele.toString().contains("\"ns\":\"pl_unlogin_home_feed\"")){
                    String html = ele.toString() .replaceAll("<script charset=\"utf-8\">", "").replaceAll("<script>","");
                    html = AbstractGather.getMessage(html);
                    Map map = jsonObjectMapper.readValue(html, Map.class);
                    Object htmls =  map.get("html");
                    System.out.println(htmls.toString());
                    Document docChild = Jsoup.parse(html);
                    Elements elesChild = docChild.getElementsByClass("text text_cut2 W_f14");
                    for (Element eleChild : elesChild){
                        System.out.println("-----------------------------------");
                        System.out.println(eleChild.text());
                    }
//                    AbstractGather.getHtml(html);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }

    public static void test(){
        WebClient webClient = new WebClient(BrowserVersion.getDefault());

    }

//    public void getHtml(){
//        /** HtmlUnit请求web页面 */
//        WebClient wc = new WebClient(BrowserVersion.CHROME);
//        wc.getOptions().setUseInsecureSSL(true);
//        wc.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true
//        wc.getOptions().setCssEnabled(false); // 禁用css支持
//        wc.getOptions().setThrowExceptionOnScriptError(false); // js运行错误时，是否抛出异常
//        wc.getOptions().setTimeout(100000); // 设置连接超时时间 ，这里是10S。如果为0，则无限期等待
//        wc.getOptions().setDoNotTrackEnabled(false);
//    }

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
