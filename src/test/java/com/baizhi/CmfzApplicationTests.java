package com.baizhi;

import com.baizhi.entity.*;
import com.baizhi.mapper.*;
import com.baizhi.service.ArticleSevice;
import com.baizhi.service.PictureService;
import io.goeasy.GoEasy;
import net.minidev.json.JSONArray;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzApplicationTests {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    PictureMapper pictureMapper;
    @Autowired
    AlbumMapper albumMapper;
    @Autowired
    ChapterMapper chapterMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    PictureService pictureService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ArticleSevice articleSevice;

    @Test
    public void contextLoads() {
        Admin admin = new Admin("wzx", "123456");
    }

    @Test
    public void select() {
        List<picture> pictures = pictureMapper.selectPicture(1, 2);
        Integer count = pictureMapper.count();
        System.out.println(count);
        for (picture picture : pictures) {
            System.out.println(picture);
        }
    }

    @Test
    public void asd() {
        List<Album> albums = albumMapper.selectAll(0, 1);
        for (Album album : albums) {
            System.out.println(album);
        }
        Integer count = albumMapper.count();
        System.out.println(count);
    }

    @Test
    public void insert() {
        Album album = new Album();
        String s = UUID.randomUUID().toString();
        album.setId(s);
        album.setAnnouncer("asdasdsa");
        album.setAuthor("asdasd");
        album.setTitle("asdasd");
        album.setScore(123.0);
        album.setCount(12);
        album.setSrc("asd.jpg");
        album.setIntroduction("asdasdsa");
        album.setState("asdasd");
        album.setReleasetime(new Date());
        album.setUploadtime(new Date());
        albumMapper.insertAlbum(album);
    }

    @Test
    public void i() {
        Chapter chapter = new Chapter();
        String s = UUID.randomUUID().toString();
        chapter.setId(s);
        chapter.setTitle("这是");
        chapter.setSize("123m");
        chapter.setTitle("13秒");
        chapter.setUploadtime(new Date());
        chapter.setName("asdsad");
        chapter.setAlbumId("8f8e9ec9-aee9-41d9-91ac-dc17e5bdfb77");
        chapterMapper.insert(chapter);
    }

    @Test
    public void d() {
        String fid = "8f8e9ec9-aee9-41d9-91ac-dc17e5bdfb77";
        Integer count = chapterMapper.count(fid);
        chapterMapper.Updatecount(fid, count);
    }

    @Test
    public void asda() {
        List<Article> articles = articleMapper.selectAll(0, 2);
        for (Article article : articles) {
            System.out.println(article);
        }
    }

    @Test
    public void asdasdsaa() {
        Article article = new Article();
        article.setId("3");
        article.setTitle("asdadsasdfazbvk,jhdsatkjsaekf");
        article.setCreatDate(new Date());
        article.setContent("asdasdsadsadfsafas");
        article.setAuthor("小阿斯顿");
        article.setStatus("可用");
        articleMapper.insert(article);
    }

    @Test
    public void user() {
        List<Integer> count = userMapper.count();
        Collections.reverse(count);
        for (Integer integer : count) {
            if (integer == null) {
            }
        }
        System.out.println(count);
    }

    @Test
    public void goeasy() throws InterruptedException {
        while (true) {
            List<Integer> list = new ArrayList<>();
            list.add(new Random().nextInt(1000));
            list.add(new Random().nextInt(1000));
            list.add(new Random().nextInt(1000));
            list.add(new Random().nextInt(1000));
            list.add(new Random().nextInt(1000));
            list.add(new Random().nextInt(1000));
            String s = JSONArray.toJSONString(list);


            GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-41105467e17d4745869fc7051df656ee");
            goEasy.publish("164channel", s);


            Thread.sleep(2000);

        }
    }
    TransportClient transportClient;
    @Before
    public void be() throws UnknownHostException {
        TransportAddress transportAddress = new TransportAddress(InetAddress.getByName("192.168.174.155"), 9300);
        transportClient = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(transportAddress);
    }

    @Test
    public void name() throws Exception{
        List<Article> selectal = articleMapper.selectal();
        for (Article article : selectal) {
            XContentBuilder xContentBuilder = XContentFactory.jsonBuilder();
            xContentBuilder.startObject()
            .field("id",article.getId())
           .field("title",article.getTitle())
            .field("content",article.getContent())
            .field("creatDate",article.getCreatDate())
            .field("author",article.getAuthor())
            .field("status",article.getStatus())
            .endObject();
            transportClient.prepareIndex("cmfz","article").setSource(xContentBuilder).execute().get();
        }
    }

    @Test
    public void newa() {
        List<Article> articles = articleSevice.queryByes("小飞");
        if(articles.size()==0) {
            System.out.println("没有找到你要的结果");
        }else {
            for (Article article : articles) {
                System.out.println(article);
            }
        }
    }


}
