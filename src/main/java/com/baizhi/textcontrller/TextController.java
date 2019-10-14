package com.baizhi.textcontrller;

import io.goeasy.GoEasy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("TextController")
public class TextController {
    @RequestMapping("upload")
    @ResponseBody
    public Map<String, Object> upload(MultipartFile img, HttpServletRequest request) throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        String realPath = request.getServletContext().getRealPath("/img");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String newname = new Date().getTime() + "_" + img.getOriginalFilename();
        img.transferTo(new File(realPath, newname));
        //获取网站的协议名
        String scheme = request.getScheme();
        //获取本机的ip地址
        InetAddress localHost = InetAddress.getLocalHost();
        String hostAddress = localHost.getHostAddress();
        //获取端口号
        int port = request.getServerPort();
        //获取当前项目名
        String contextPath = request.getContextPath();

        String url = scheme + "://" + hostAddress + ":" + port + contextPath + "/img/" + newname;
        map.put("error", 0);
        map.put("url", url);
        return map;
    }

    @ResponseBody
    @RequestMapping("see")
    public Map<String, Object> see(HttpSession session, HttpServletRequest request) throws UnknownHostException {
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        String realPath = session.getServletContext().getRealPath("/img");
        File file = new File(realPath);
        String[] nemes = file.list();
        for (String s : nemes) {
            HashMap<String, Object> map1 = new HashMap<>();
            map.put("is_dir", false);
            map.put("has_file", false);
            //获取文件的大小
            File file1 = new File(realPath, s);
            long length = file1.length();
            map1.put("filesize", length);
            map1.put("dir_path", "");
            map1.put("is_photo", true);
            //获取文件的后缀名
            String s1 = s.substring(s.lastIndexOf(".") + 1);
            map1.put("filetype", s1);
            map1.put("filename", s);
            if (s.contains("_")) {
                String s2 = s.split("_")[0];
                Long aLong = Long.valueOf(s2);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format1 = format.format(aLong);
                map1.put("datetime", format1);
            } else {
                map1.put("datetime", new Date());
            }
            list.add(map1);
        }
        map.put("moveup_dir_path", "");
        map.put("current_dir_path", "");
        //获取当前网站的协议名  http
        String scheme = request.getScheme();
        //获取当前本机ip地址
        InetAddress localHost = InetAddress.getLocalHost();
        String hostAddress = localHost.getHostAddress();
        //获取当前端口号
        int port = request.getServerPort();
        //获取项目名
        String path = request.getContextPath();
        String url = scheme + "://" + hostAddress + ":" + port + path + "/img/";
        map.put("current_url", url);
        int size = list.size();
        map.put("total_count", size);
        map.put("file_list", list);
        return map;
    }

    @RequestMapping("bo")
    @ResponseBody
    public void op(String value) {
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-48a79c8caec74faa96e33004dbe0b253");
        goEasy.publish("164channel", value);
        System.out.println(value);
    }


}
