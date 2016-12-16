package data.gather.constant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dell on 2016/12/16.
 */
public class TestLogin {

    public static void main(String[] args) throws IOException {
        String strURL = "http://login.sina.com.cn/sso/login.PHP?client=ssologin.js(v1.4.18)";

// strURL="http://weibo.com/u/1596867051/home?wvr=5&lf=reg";
        strURL = "http://d.weibo.com/100803?refer=index_hot_new";
        URL url = new URL(strURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

// httpConn.setRequestProperty("Host", "www.jimubox.com");
// httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
// httpConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
// httpConn.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
// httpConn.setRequestProperty("Accept-Encoding", "gzip, deflate");
//
        String cookie = "你的cookie";
        httpConn.setRequestProperty("Cookie", cookie);
// httpConn.setRequestProperty("Connection", "keep-alive");

        httpConn.setRequestProperty("charset", "utf-8");

        InputStreamReader input = new InputStreamReader(httpConn.getInputStream(), "utf-8");
        BufferedReader bufReader = new BufferedReader(input);
        String line = "";
        StringBuilder contentBuf = new StringBuilder();
        while ((line = bufReader.readLine()) != null) {
            contentBuf.append(line);
        }
        String buf = contentBuf.toString();


//字符串匹配/正则表达式，得到所有<script></script>标签段的index


// System.out.println(buf);

// String testStr = "12315<text>show me</text> <text>show me</text>";
// Pattern p = Pattern.compile("<text>(.*)</text>");
// Matcher m = p.matcher(testStr);
// while(m.find()){
// System.out.println(m.group(1));
// }

        String test = "<html><script>FM.view({})</script><script>FM.view({})</script><script>FM.view({})</script>";
        Pattern p = Pattern.compile("\\<script>FM.view(.*?)\\</script>");
// Pattern p = Pattern.compile("\\<script>(.*?)\\</script>");
        Matcher m = p.matcher(buf);
        List<String> rsList = new ArrayList<String>();
        List<String> liList = new ArrayList<String>();
        while (m.find()) {
            String t_rs = m.group(1);
            if (t_rs.contains("html") && t_rs.contains("pt_li S_line2")) {
                rsList.add(t_rs);
            }
        }

        if (rsList.isEmpty()) {
            System.out.println("抓取异常!!!!");
        }
        String topics = rsList.get(0);
// System.out.println(topics);
        p = Pattern.compile("\\<li class\\=(.*?)li>");
        m = p.matcher(topics);
        while (m.find()) {
            if (m.group(1).startsWith("\\\"pt_li S_line2\\\"")) {
                String li = m.group(1);

                String regex = "http.*?faxian_huati";
                Pattern p1 = Pattern.compile(regex);
                Matcher m1 = p1.matcher(new String(li));
// if(m1.find()){
// System.out.println(m1.group(0));
// }
//
// regex = "http:\\\\/\\\\/ww3.sinaimg.cn.*?\\.jpg";
// p1=Pattern.compile(regex);
// m1=p1.matcher(new String(li));
// if(m1.find()){
// System.out.println(m1.group(0));
// }

//top排名
// regex = "\\<span class\\=\\\\\"DSC_topicon\\\\\">(.*?)<\\\\/span>";
// p1=Pattern.compile(regex);
// m1=p1.matcher(new String(li));
// if(m1.find()){
// System.out.println(m1.group(1));
// }

//话题名称
// regex = "\\#(.*?)#";
// p1=Pattern.compile(regex);
// m1=p1.matcher(new String(li));
// if(m1.find()){
// System.out.println(m1.group(0));
// }

//分类标签
                regex = "\\<\\\\/span>(.*?)<\\\\/a>";
                p1 = Pattern.compile(regex);
                m1 = p1.matcher(new String(li));
                if (m1.find()) {
                    System.out.println(m1.group(1));
                }

            }
        }

// for(String li:liList){
// //图片url
// String regex = "http.*?faxian_huati";
// Pattern p1=Pattern.compile(regex);
// Matcher m1=p1.matcher(new String(li));
// if(m1.find()){
// System.out.println(m1.group(0));
// }
// }
//
// for(String li:liList){
// //图片url
// String regex = "http:\\\\/\\\\/ww3.sinaimg.cn.*?\\.jpg";
// Pattern p1=Pattern.compile(regex);
// Matcher m1=p1.matcher(new String(li));
// if(m1.find()){
// System.out.println(m1.group(0));
// }
// }
    }

}