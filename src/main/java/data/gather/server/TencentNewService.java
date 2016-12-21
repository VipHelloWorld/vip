package data.gather.server;

import com.google.common.collect.Sets;
import data.gather.dao.Infohandle;
import data.gather.model.InfoEntity;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dell on 2016/12/20.
 */
public class TencentNewService {
    public static void getPage() {
        try {
            Document doc = Jsoup.connect("http://news.qq.com/society_index.shtml").get();
            Elements newElement = doc.select("div#news");
            Set<String> set = Sets.newHashSet();
            Elements select = newElement.select("div.first").select("div.Q-tpList");
            getPageInfo(select);
            select = newElement.select("div.second").select("div.Q-tpList");
            getPageInfo(select);
            select = newElement.select("div.third").select("div.Q-tpList");
            getPageInfo(select);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void getPageInfo(Elements elements) {
        for (Element element : elements) {
            List list = new ArrayList();
            InfoEntity infoEntity = new InfoEntity();
            infoEntity.setSource(1);
            //图片
            String img = element.select("img.picto").attr("src");
            if (StringUtil.isBlank(img)) {
                img = element.select("img.picto").attr("_src");
            }
            list.add(img == null ? "" : img);
            infoEntity.setImg(list);

            //链接
            String url = element.select("a.pic").attr("href");
            infoEntity.setUrl(url);

            //标题
            String title = element.select("a.linkto").text();
            infoEntity.setTitle(title);

            //讨论数量
            String chatCount = element.select("a.discuzBtn").text();
            infoEntity.setChatCount(chatCount);

            int insert = Infohandle.insert(infoEntity);
            System.out.println(insert);
        }
    }

    public static void main(String[] args) {
        getPage();
    }
}
