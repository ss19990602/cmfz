package com.baizhi.serviceimpl;

import com.baizhi.entity.Album;
import com.baizhi.mapper.AlbumMapper;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    AlbumMapper albumMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> selectPage(Integer start, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        Integer s = (start - 1) * rows;
        List<Album> albums = albumMapper.selectAll(s, rows);
        Integer count = albumMapper.count();
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;//页数
        map.put("rows", albums);
        map.put("records", count);
        map.put("total", total);
        map.put("page", start);
        return map;
    }

    @Override
    public String edit(String oper, Album album, String id) {
        if ("del".equals(oper)) {
            String[] split = id.split(",");
            for (String s : split) {
                albumMapper.delete(s);
            }
            return null;
        } else if ("add".equals(oper)) {
            String s = UUID.randomUUID().toString();
            album.setId(s);
            album.setUploadtime(new Date());
            album.setReleasetime(new Date());
            album.setCount(0);
            album.setScore(0.0);
            albumMapper.insertAlbum(album);
            return s;
        } else {
            albumMapper.UpdateState(id, album.getState());
        }
        return null;
    }

    public void updateSrc(MultipartFile multipartFile, String id, HttpSession session) {
        String realPath = session.getServletContext().getRealPath("/img");
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
        albumMapper.UpdateSrc(id, newname);
    }
}
