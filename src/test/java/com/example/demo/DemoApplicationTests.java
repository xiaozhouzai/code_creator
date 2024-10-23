package com.example.demo;


import com.example.demo.system.user.dao.SysUsersMapper;
import com.example.demo.system.user.domain.SysUsers;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import jakarta.annotation.Resource;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.hssf.extractor.OldExcelExtractor;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private MinioClient minioClient;

    @Resource
    private SysUsersMapper sysUsersMapper;

    @Test
    void contextLoads() {
        SysUsers sysUsers = new SysUsers();
        sysUsers.setUsername("lcy");
        sysUsers.setPassword("158574");
        sysUsers.setNickname("李成阳");
        sysUsers.setMobile("17538718072");
        sysUsers.setSex(0);
        sysUsers.setStatus(0);
        sysUsersMapper.insert(sysUsers);
    }


    @Test
    void testMinio() {
        try (FileInputStream fileInputStream = new FileInputStream("C:\\IdeaFile\\IDEA\\JavaProject\\demo\\src\\main\\resources\\static\\12.png")) {
            minioClient.putObject(PutObjectArgs.builder().bucket("buildyourdream")
                    .object("12.png").stream(fileInputStream, fileInputStream.available(), -1)
                    .build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试一对多导出
     */
    @Test
    void test() {

    }

    @Test
    void testMinioDownload() throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        URL url = new URL("http://minio.lyqf.cn");
        MinioClient client = new MinioClient.Builder()
                .endpoint(url) // 访问地址
                .credentials("minioadmin", "minioadmin") // 访问密钥和秘密密钥
                .build();
//        // 生成Presigned GET链接
//        String bucketName = "java"; // 存储桶名称
//        String objectName = "path/to/your/file.txt"; // 文件名
//        URL url = new URL("http://minio.lyqf.cn/minio-preview/test/WX20240904-171458@2x.png");
//        // 打开连接
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        // 设置请求方法为GET
//        connection.setRequestMethod("GET");
//        // 检查HTTP响应码，200表示成功
//        int responseCode = connection.getResponseCode();
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            // 获取文件输入流
//            File file = new File("C:\\IdeaFile\\IDEA\\JavaProject\\demo\\src\\main\\resources\\static\\test.png");
//            try (FileOutputStream fos = new FileOutputStream(file)) {
//                // 读取文件内容并写入文件
//                byte[] buffer = new byte[1024];
//                int bytesRead;
//                while ((bytesRead = connection.getInputStream().read(buffer)) != -1) {
//                    fos.write(buffer, 0, bytesRead);
//                }
//            }
//        }
//        client.getObject()
        GetObjectArgs args = GetObjectArgs.builder().bucket("test").object("WX20240904-171458@2x.png").build();
        InputStream inputStream = client.getObject(args);
        File file = new File("C:\\IdeaFile\\IDEA\\JavaProject\\demo\\src\\main\\resources\\static\\test.png");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            // 读取文件内容并写入文件
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }

    @Test
    void testCaptcha() throws ClassNotFoundException {
        String driver = "com.mysql.cj.jdbc.Driver";
        //如果使用的是jdbc:mysql://127.0.0.1:3306/myschool;那么我们要注意必须将配置里面的远程访问打开
        String url = "jdbc:mysql://124.70.22.59:3306/bys_new_test_bak";   // 指JDBC连接方式；   jdbc:mysql
        //指你的本机地址；	 localhost
        //SQL数据库的端口号；	3306
        //要连接的数据库;      myschool
        String userName = "root"; //账号
        String userPwd = "DkATELGJr5bDA6axZLbH";//密码
        String sql = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'bys_new_dev_bak'";
        Class.forName(driver);  //加载驱动
        Connection conn = null;
        ResultSet rs = null;  //结果集合
        PreparedStatement pstmt = null;
        List<String> tableList = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(url, userName, userPwd); //可以使用DriverManager获取连接对象
            pstmt = conn.prepareStatement(sql);//通过连接对象Connection得到一个PreparedStatement对象，他可以帮助我们完成针对数据库的增删查改操作
            //增删改都是对数据库的记录进行更改，他们都是使用executeUpdate这个方法来完成，返回值都是受影响行数
            rs = pstmt.executeQuery();
            while (rs.next()) {
                //下面两种凡是的区别，一般使用第二种方式，第一种方式如果我现在调换了列的位置，那么我们要去修改下面的代码
                //第二种方式根据列名来获取值，那么我们不管怎么去修改列的位置，都不会影响到我们的取值
                tableList.add(rs.getString("table_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();   //关闭操作节省资源
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        tableList.forEach(System.out::println);
    }

    @Test
    void testReadOldXls() throws IOException {
        OldExcelExtractor oldExcelExtractor = new OldExcelExtractor(new FileInputStream("C:\\IdeaFile\\IDEA\\JavaProject\\demo\\src\\main\\resources\\static\\损坏文件.xls"));
        int biffVersion = oldExcelExtractor.getBiffVersion();
        int fileType = oldExcelExtractor.getFileType();
        POITextExtractor metadataTextExtractor = oldExcelExtractor.getMetadataTextExtractor();
        RecordInputStream inputStream = (RecordInputStream) metadataTextExtractor.getDocument();
        inputStream.nextRecord();
        int nextSid = inputStream.getNextSid();
        System.out.println(nextSid);
        System.out.println("版本号:" + biffVersion + ",文件类型:" + fileType);
    }



    @Test
    void testConvertOldXls() {
        File newFile = new File("C:\\IdeaFile\\IDEA\\JavaProject\\demo\\src\\main\\resources\\static\\new.xlsx");
        File oldFile = new File("C:\\IdeaFile\\IDEA\\JavaProject\\demo\\src\\main\\resources\\static\\工作簿2是.xls");

        ActiveXComponent xl = new ActiveXComponent("Excel.Application");//这里也可以Word等
        xl.setProperty("Visible", new Variant(false));
        Dispatch workbook = xl.getProperty("Workbooks").toDispatch();
        workbook = Dispatch.invoke(workbook, "Open", Dispatch.Method, new Object[]{
                oldFile.getAbsolutePath(), new Variant(false)}, new int[1]).toDispatch();
        Dispatch.call(workbook, "SaveAS", newFile.getAbsolutePath(), 51);//51代表什么，参考下表
        Dispatch.call(workbook, "Close", new Variant(false));
    }

    @Test
    void convertXlsToXlsx() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("C:\\IdeaFile\\IDEA\\JavaProject\\demo\\src\\main\\resources\\static\\损坏文件.xls");
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        // 将Workbook写入到一个新的Excel文件，这里保存为.xlsx格式
        HSSFWorkbook xssfWorkbook = new HSSFWorkbook();
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            Sheet newSheet = xssfWorkbook.createSheet(sheet.getSheetName());
            for (Row row : sheet) {
                Row newRow = newSheet.createRow(row.getRowNum());
                for (Cell cell : row) {
                    Cell newCell = newRow.createCell(cell.getColumnIndex());
                    newCell.setCellValue(cell.getStringCellValue()); // 假设是字符串类型，根据实际情况调整
                }
            }
        }
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\IdeaFile\\IDEA\\JavaProject\\demo\\src\\main\\resources\\static\\new.xls");
        xssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        xssfWorkbook.close();
        workbook.close();
        fileInputStream.close();
        System.out.println("转换完成");
        Workbook hssfWorkbook = WorkbookFactory.create(new FileInputStream("C:\\IdeaFile\\IDEA\\JavaProject\\demo\\src\\main\\resources\\static\\new.xls"));
        int numberOfSheets = hssfWorkbook.getNumberOfSheets();
        List<Map<Integer, Object>> list = new ArrayList<>();

        for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = hssfWorkbook.getSheetAt(i);
            for (Row cells : sheet) {
                for (Cell cell : cells) {
                    Map<Integer, Object> map = new HashMap<>();
                    System.out.println(cell.getColumnIndex() + "::::" + cell.getStringCellValue());
                    map.put(cell.getColumnIndex(), cell.getStringCellValue());
                    list.add(map);
                }
            }
        }

    }

    @Test
    void testHeBing() {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Sheet1");
        HSSFRow header1 = sheet.createRow(0);
        HSSFRow header2 = sheet.createRow(1);
        Map<Integer,String> headersMap1 = new HashMap<>();
        Map<Integer,String> headersMap2 = new HashMap<>();
        headersMap1.put(0, "姓名");
        headersMap1.put(1, "年龄");
        headersMap1.put(2, "订单信息");
        headersMap1.put(3, "");
        headersMap1.put(4, "");
        headersMap2.put(0, "");
        headersMap2.put(1, "");
        headersMap2.put(2, "ID");
        headersMap2.put(3, "用途");
        headersMap2.put(4, "金额");
        // 设置样式
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 设置水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER); // 设置垂直居中

        for (int i = 0; i < headersMap1.keySet().size(); i++) {
            HSSFCell cell1 = header1.createCell(i, CellType.STRING);
            HSSFCell cell2 = header2.createCell(i, CellType.STRING);
            cell1.setCellValue(headersMap1.get(i));
            cell1.setCellStyle(style);
            cell2.setCellValue(headersMap2.get(i));
            cell2.setCellStyle(style);
        }
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 4));
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));
        // 创建数据行
        List<Map<String, String>> dataList = new ArrayList<>();
        dataList.add(Map.of("姓名", "张三", "年龄", "25", "ID", "001", "用途", "购买图书", "金额", "100"));
        dataList.add(Map.of("姓名", "张三", "年龄", "25", "ID", "002", "用途", "购买商品", "金额", "200"));
        dataList.add(Map.of("姓名", "李四", "年龄", "30", "ID", "003", "用途", "购买文具", "金额", "50"));
        dataList.add(Map.of("姓名", "李四", "年龄", "30", "ID", "003", "用途", "购买文具", "金额", "50"));
        dataList.add(Map.of("姓名", "王五", "年龄", "60", "ID", "003", "用途", "购买文具", "金额", "50"));
        dataList.add(Map.of("姓名", "李四", "年龄", "30", "ID", "003", "用途", "购买文具", "金额", "50"));
        dataList.add(Map.of("姓名", "李四", "年龄", "30", "ID", "003", "用途", "购买文具", "金额", "50"));

        // 记录上一个姓名和年龄以及对应的行号
        String prevName = "";
        String prevAge = "";
        // 起始行号初始化为 -1
        int prevRowIndex = -1; // 上一个行号初始化为 -1

        // 创建自定义的 Comparator
        Comparator<Map<String, String>> comparator = Comparator
                .comparing((Map<String, String> map) -> map.get("姓名"))
                .thenComparing(map -> map.get("年龄"));

        // 使用 stream().sorted() 方法进行排序
        List<Map<String, String>> sortedDataList = dataList.stream()
                .sorted(comparator)
                .toList();

        int rowIndex = 2; // 数据行开始的行数
        for (Map<String, String> data : sortedDataList) {
            HSSFRow row = sheet.createRow(rowIndex);
            row.setRowStyle(style);
            row.createCell(0).setCellValue(data.get("姓名"));
            row.createCell(1).setCellValue(data.get("年龄"));
            row.createCell(2).setCellValue(data.get("ID"));
            row.createCell(3).setCellValue(data.get("用途"));
            row.createCell(4).setCellValue(data.get("金额"));

            // 如果姓名或年龄与上一条不同，并且上一个行号不为 -1，表示有合并区间需要添加
            if (!data.get("姓名").equals(prevName) || !data.get("年龄").equals(prevAge)) {
                if (prevRowIndex != -1) {
                    // 添加合并区间
                    if (prevRowIndex + 1 != rowIndex) { // 至少有两个相同的条目
                        sheet.addMergedRegion(new CellRangeAddress(prevRowIndex, rowIndex - 1, 0, 0));
                        sheet.addMergedRegion(new CellRangeAddress(prevRowIndex, rowIndex - 1, 1, 1));
                    }
                }
                prevRowIndex = rowIndex; // 更新上一个行号
            }

            prevName = data.get("姓名");
            prevAge = data.get("年龄");
            rowIndex++;
        }

        // 处理最后一个合并区间
        if (prevRowIndex != -1 && prevRowIndex + 1 != rowIndex) { // 至少有两个相同的条目
            sheet.addMergedRegion(new CellRangeAddress(prevRowIndex, rowIndex - 1, 0, 0));
            sheet.addMergedRegion(new CellRangeAddress(prevRowIndex, rowIndex - 1, 1, 1));
        }

        try (FileOutputStream fileOut = new FileOutputStream("C:\\IdeaFile\\IDEA\\JavaProject\\demo\\src\\main\\resources\\static\\合并单元格.xls")) {
            // 将工作簿写入文件输出流
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 关闭工作簿
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
