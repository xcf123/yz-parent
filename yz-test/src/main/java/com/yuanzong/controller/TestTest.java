package com.yuanzong.controller;


import com.yuanzong.beans.SchoolBeans.SchoolBean;

import com.yuanzong.utils.SchoolUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.context.request.NativeWebRequest;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xiechaofeng
 * @date 2018/7/12 11:49
 * @desc
 */
public class TestTest
{
    public static void main2(String[] args) throws IOException {
        File file = new File("C:\\Users\\xcf\\Desktop\\oxford_culture.sql");
        List<String> utf8 = FileUtils.readLines(file, "utf8");
        List<String> newList=new  ArrayList<>();
        for (String s : utf8) {
            s+=";";
            newList.add(s);
            System.out.println(1);
        }
        FileUtils.writeLines(new File("C:\\Users\\xcf\\Desktop\\oxford_culture2.sql"),newList,false);
    }

    public static void main333(String[] args) throws Exception
    {
        int num=1;
        for (int j = 2; j <4 ; j++)
        {
            int max=0;
            if(j==2){
                max=1161;
            }else {
                max=1305;
            }
            for (int i = 0; i <max ; i++)
            {
                Document doc = Jsoup.connect("http://xuexiao.51sxue.com/slist/?t="+j+"&areaCodeS=&page="+i).get();
                Elements replyBox = doc.select("div.reply_box");
                for (Element box : replyBox)
                {
                    SchoolBean bean= new SchoolBean();
                    Elements schoolDetail = box.select("div.school_m_main");
                    Elements li = schoolDetail.get(0).getElementsByTag("li");
                    //得到学校名
                    Elements h3 = li.get(0).getElementsByTag("h3");
                    Elements a = h3.get(0).getElementsByTag("a");
                    String text = a.get(0).text();
                    String val = text;
                    bean.schoolName=val;

                    //得到省市区
                    Elements b = li.get(1).getElementsByTag("b");
                    String address = b.get(0).text();
                    String[] split = address.split(" ");
                    if(split.length==3){
                        bean.province=split[0];
                        bean.city=split[1];
                        bean.area=split[2];
                    }else if(split.length==2){
                        bean.province = split[0];
                        bean.city = split[0];
                        bean.area = split[1];
                    }else {
                        bean.province = split[0];
                    }

                    //得到小学或者初中
                    if(j==2){
                        bean.type=1;
                    }else if(j==3){
                        bean.type=2;
                    }
                    System.out.println("-->"+num);
                    num++;
                    SchoolUtils.recordInsertSql(bean);

                    //	1) DriverManager:用于注册数据库驱动
                    Class.forName("com.mysql.jdbc.Driver");
                    //	DriverManager.registerDriver(driver);
                    //	2) Connection: 表示与数据库创建的连接
                    String url="jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                    String user = "root";
                    String password = "123456";
                    Connection connection =
                            DriverManager.getConnection(url, user, password);
                    //	3) Statement: 操作数据库sql语句的对象
                    Statement statement = connection.createStatement();
                    String sql =  "insert into tb_school (`schoolName`,`province`,`city`,`area`,`type`)" +
                            "values ('" + bean.schoolName +"','" +bean.province+ "','"+bean.city+"','"+bean.area+"'" +
                            ",'"+bean.type+"');";
                    //	4) ResultSet: 结果集或一张虚拟表
                    boolean execute = statement.execute(sql);
                    statement.close();
                    connection.close();
                    if(i%50==0){
                        try
                        {
                            Thread.sleep(2000);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        FileUtils.writeLines(new File("D:\\Download\\school.sql"), SchoolUtils.sql,false);

    }

    public static void main22(String[] args) throws Exception
    {
        int num=7500;
        for (int i = 751; i <1305 ; i++)
        {
            Document doc = Jsoup.connect("http://xuexiao.51sxue.com/slist/?t="+3+"&areaCodeS=&page="+i).get();
            Elements replyBox = doc.select("div.reply_box");
            for (Element box : replyBox)
            {
                SchoolBean bean= new SchoolBean();
                Elements schoolDetail = box.select("div.school_m_main");
                Elements li = schoolDetail.get(0).getElementsByTag("li");
                //得到学校名
                Elements h3 = li.get(0).getElementsByTag("h3");
                Elements a = h3.get(0).getElementsByTag("a");
                String text = a.get(0).text();
                String val = text;
                bean.schoolName=val;

                //得到省市区
                Elements b = li.get(1).getElementsByTag("b");
                String address = b.get(0).text();
                String[] split = address.split(" ");
                if(split.length==3){
                    bean.province=split[0];
                    bean.city=split[1];
                    bean.area=split[2];
                }else if(split.length==2){
                    bean.province = split[0];
                    bean.city = split[0];
                    bean.area = split[1];
                }else {
                    bean.province = split[0];
                }

                //得到小学或者初中

                bean.type=2;

                System.out.println("-->"+num);
                num++;
                SchoolUtils.recordInsertSql(bean);

                //	1) DriverManager:用于注册数据库驱动
                Class.forName("com.mysql.jdbc.Driver");
                //	DriverManager.registerDriver(driver);
                //	2) Connection: 表示与数据库创建的连接
                String url="jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                String user = "root";
                String password = "123456";
                Connection connection =
                        DriverManager.getConnection(url, user, password);
                //	3) Statement: 操作数据库sql语句的对象
                Statement statement = connection.createStatement();
                String sql =  "insert into tb_school (`schoolName`,`province`,`city`,`area`,`type`)" +
                        "values ('" + bean.schoolName +"','" +bean.province+ "','"+bean.city+"','"+bean.area+"'" +
                        ",'"+bean.type+"');";
                //	4) ResultSet: 结果集或一张虚拟表
                boolean execute = statement.execute(sql);
                statement.close();
                connection.close();
                if(i%50==0){
                    try
                    {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main3(String[] args)
    {
        String s="1asadsa.mp3";
            s=s.replaceAll("\\..*?$","");
        System.out.println(s);
    }

    public static void main33(String[] args) throws IOException, ParserConfigurationException, TransformerException
    {
        File file=new File("C:\\Users\\Administrator\\Desktop\\习题.doc");
        InputStream is = new FileInputStream(file);
        HWPFDocument hwpfDocument = new HWPFDocument(is);
        org.w3c.dom.Document newDocument = XMLHelper.getDocumentBuilderFactory().newDocumentBuilder().newDocument();
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(newDocument);
        wordToHtmlConverter.processDocument(hwpfDocument);
        StringWriter stringWriter = new StringWriter();
        Transformer transformer = TransformerFactory.newInstance().newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        transformer.setOutputProperty(OutputKeys.METHOD, "html");

        transformer.transform(new DOMSource(wordToHtmlConverter.getDocument()), new StreamResult(stringWriter));
        String s = stringWriter.toString();
        System.out.println(s);
    }

    public static void main(String[] args) throws IOException
    {
        File file=new File("C:\\Users\\Think\\Desktop\\新余四中作息时间表.xlsx");
        XSSFWorkbook book = new XSSFWorkbook(new FileInputStream(file));
        Sheet sheet = book.getSheetAt(0);
        int mergedRegions = sheet.getNumMergedRegions(); // 取得2个合并单元格
        int lastRowNum = sheet.getLastRowNum();
        System.out.println(lastRowNum);
        Row row2 = sheet.getRow(0);
        Map<Integer,String> category = new HashMap<Integer, String>();
        for(int j = 0 ; j < mergedRegions; j++ ){
            CellRangeAddress rangeAddress = sheet.getMergedRegion(j);
            int firstRow = rangeAddress.getFirstColumn();
            int lastRow = rangeAddress.getLastColumn();
            System.out.println(j+"firstRow:"+firstRow);
            System.out.println(j+"lastRow"+lastRow);
            category.put(rangeAddress.getFirstColumn(), rangeAddress.getLastColumn()+"-"+row2.getCell(firstRow).toString());
        }
        for( int rowNum = 1 ; rowNum <= sheet.getLastRowNum() ; rowNum++ ){
            Row row = sheet.getRow(rowNum);
            if(row == null){
                continue;
            }
            short lastCellNum = row.getLastCellNum();
            System.out.println("lastCellNum"+lastCellNum);
            String cate = "";
            Integer maxIndex = 0;
            for( int col = row.getFirstCellNum() ; col < lastCellNum ; col++ ){
                Cell cell = row.getCell(col);
                if(cell == null ){
                    continue;
                }
                if("".equals(cell.toString())){
                    continue;
                }
                int columnIndex = cell.getColumnIndex();
                String string = category.get(columnIndex);
                if(string != null && !string.equals("")){
                    String[] split = string.split("-");
                    cate = split[1];
                    maxIndex = Integer.parseInt(split[0]);
                    Object cellValue = "";
                    //一条新的timeTable

                    System.out.println(cate+"<-->"+cellValue);
                }else {
                    Object cellValue = "";
                    switch (cell.getCellType())
                    {
                        case Cell.CELL_TYPE_NUMERIC:
                            short format = cell.getCellStyle().getDataFormat();
                            //处理日期格式
                            if(HSSFDateUtil.isCellDateFormatted(cell)){
                                SimpleDateFormat sdf = null;
                                Date date = null;
                                if (format == 20 || format == 32) {
                                    sdf = new SimpleDateFormat("HH:mm");
                                    date = cell.getDateCellValue();
                                } else if (format == 14 || format == 31 || format == 57 || format == 58) {
                                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    double value = cell.getNumericCellValue();
                                    date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
                                }else if (format==178){
                                    sdf=new SimpleDateFormat("HH:mm:ss");
                                    date = cell.getDateCellValue();
                                }
                                cellValue = sdf.format(date);
                            }
                            //处理数字格式
                            else
                            {
                                double value = cell.getNumericCellValue();
                                CellStyle style = cell.getCellStyle();
                                DecimalFormat decimalFormat = new DecimalFormat();
                                String temp = style.getDataFormatString();
                                // 单元格设置成常规
                                if (temp.equals("General")) {
                                    decimalFormat.applyPattern("#");
                                }
                                cellValue = decimalFormat.format(value);
                            }
                            break;
                        case Cell.CELL_TYPE_STRING:
                            cellValue = cell.getStringCellValue();
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            cellValue = cell.getBooleanCellValue();
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            cellValue = cell.getCellFormula();
                            break;
                        default:
                            cellValue = "";
                            break;
                    }
                    //如果当前便利的列编号小于等于合并单元格的结束,说明分类还是上面的分类名称
                    if(columnIndex<=maxIndex){
                        System.out.println(cate+"<----->"+cellValue);
                    }else {
                        System.out.println("分类未知"+"<-->"+cell.toString());
                    }
                }
            }

        }
    }
}
