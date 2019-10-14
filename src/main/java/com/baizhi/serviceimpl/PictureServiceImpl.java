package com.baizhi.serviceimpl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.picture;
import com.baizhi.mapper.PictureMapper;
import com.baizhi.service.PictureService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {
    @Autowired
    PictureMapper mapper;

    @Override
    public Map<String, Object> selectPage(Integer start, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        Integer c = (start - 1) * rows;
        List<picture> pictures = mapper.selectPicture(c, rows);
        Integer count = mapper.count();//总条数
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;//页数
        map.put("rows", pictures);
        map.put("records", count);
        map.put("total", total);
        map.put("page", start);
        return map;
    }

    @Override
    public String edit(String oper, picture picture, String id) {
        if (oper.equals("del")) {
            String[] split = id.split(",");
            for (int i = 0; i < split.length; i++) {
                mapper.DeletePicture(split[i]);
            }
            return null;
        } else if (oper.equals("add")) {
            String s = UUID.randomUUID().toString();
            picture.setId(s);
            picture.setDate(new Date());
            mapper.insertPicture(picture);
            return s;
        } else {
            mapper.Update(picture.getId(), picture.getState());
            return null;
        }
    }

    @Override
    public void Update(MultipartFile src, String id, HttpSession session) {
        String realPath = session.getServletContext().getRealPath("/img");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String name = src.getOriginalFilename();
        String newname = new Date().getTime() + "_" + name;
        try {
            src.transferTo(new File(realPath, newname));
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapper.Updatesrc(id, newname);
    }

    @Override
    public void derive(HttpServletRequest request, HttpServletResponse response) {
        List<picture> pictures = mapper.selectAll();
        for (picture picture : pictures) {
            picture.setSrc("D:\\coud\\164中期项目\\cmfz\\src\\main\\webapp\\img\\" + picture.getSrc());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("轮播图片信息", "轮播图", "表1"), picture.class, pictures);
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            response.setHeader("content-disposition", "attachment");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
/*        //获取目标文件夹的路径
        String realPath = request.getSession().getServletContext().getRealPath("/dc");
        //读入
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(realPath,"/picture.xls"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //写出
        ServletOutputStream os= null;
        try {
            os = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //设置响应头
        try {
            response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode("/picture.xls","utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            IOUtils.copy(fis,os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //关流
        IOUtils.closeQuietly(fis);
        IOUtils.closeQuietly(os);*/
    }
}
