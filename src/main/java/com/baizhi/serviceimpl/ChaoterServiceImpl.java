package com.baizhi.serviceimpl;


import com.baizhi.entity.Chapter;
import com.baizhi.mapper.ChapterMapper;
import com.baizhi.service.ChapterService;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
@Transactional
public class ChaoterServiceImpl implements ChapterService {
    @Autowired
    ChapterMapper chapterMapper;

    @Override
    public Map<String, Object> select(Integer start, Integer rows, String id) {
        HashMap<String, Object> map = new HashMap<>();
        Integer count = chapterMapper.count(id);
        Integer s = (start - 1) * rows;
        List<Chapter> chapters = chapterMapper.selectAll(s, rows, id);
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;//页数
        map.put("rows", chapters);
        map.put("records", count);
        map.put("total", total);
        map.put("page", start);
        return map;
    }

    @Override
    public String edit(String oper, Chapter chapter, String id, String fid) {
        if ("del".equals(oper)) {
            String[] split = id.split(",");
            for (String s : split) {
                chapterMapper.delete(s);
            }
            Integer count = chapterMapper.count(fid);
            chapterMapper.Updatecount(fid, count);
            return null;
        } else if ("add".equals(oper)) {
            System.out.println(fid);
            String s = UUID.randomUUID().toString();
            chapter.setId(s);
            chapter.setUploadtime(new Date());
            chapter.setAlbumId(fid);
            chapterMapper.insert(chapter);
            Integer count = chapterMapper.count(fid);
            chapterMapper.Updatecount(fid, count);
            return s;
        } else {
            chapterMapper.UpdateName(chapter.getId(), chapter.getTitle());
            return null;
        }

    }

    @Override
    public void Update(MultipartFile multipartFile, String id, HttpSession session) {
        String realPath = session.getServletContext().getRealPath("/mp3");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String name = multipartFile.getOriginalFilename();
        String newname = new Date().getTime() + "_" + name;
        try {
            multipartFile.transferTo(new File(realPath, newname));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String realPath1 = session.getServletContext().getRealPath("/mp3/" + newname);
        File file1 = new File(realPath1);
        //获取文件大小  单位是字节 byte
        long length = file1.length();
        //获取音频时长 单位是秒      AudioFile类需要引入额外依赖 jaudiotagger
        AudioFile read = null;
        try {
            read = AudioFileIO.read(file1);
        } catch (CannotReadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TagException e) {
            e.printStackTrace();
        } catch (ReadOnlyFileException e) {
            e.printStackTrace();
        } catch (InvalidAudioFrameException e) {
            e.printStackTrace();
        }
        AudioHeader header = read.getAudioHeader();
        int trackLength = header.getTrackLength();
        //获取分钟数
        Integer m = trackLength / 60;
        //获取秒秒数
        Integer s = trackLength % 60;
        // System.out.println(m+"分"+s+"秒");
        //将文件大小强转成double类型
        double size = (double) length;
        // System.out.println(size/1024/1024);
        //获取文件大小 单位是MB
        double ll = size / 1024 / 1024;
        //取double小数点后两位  四舍五入
        BigDecimal bg = new BigDecimal(ll).setScale(2, RoundingMode.UP);
        //System.out.println(bg+"MB");
        Chapter chapter = new Chapter();
        chapter.setId(id);
        chapter.setName(newname);
        chapter.setLength(m + "分" + s + "秒");
        chapter.setSize(bg + "MB");
        chapterMapper.Update(chapter);
    }

}
