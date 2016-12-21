package data.gather.model;

import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2016/12/19.
 */
public class InfoEntity {
    private String url;
    private List img;
    private String chatCount;
    private String title;
    private Date time = new Date();
    private Long t = System.currentTimeMillis();
    private Integer source;//来源  0新浪 1腾讯

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List getImg() {
        return img;
    }

    public void setImg(List img) {
        this.img = img;
    }

    public String getChatCount() {
        return chatCount;
    }

    public void setChatCount(String chatCount) {
        this.chatCount = chatCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getT() {
        return t;
    }

    public void setT(Long t) {
        this.t = t;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "InfoEntity{" +
                "url='" + url + '\'' +
                ", img=" + img +
                ", chatCount='" + chatCount + '\'' +
                ", title='" + title + '\'' +
                ", time=" + time +
                ", t=" + t +
                ", source=" + source +
                '}';
    }
}
