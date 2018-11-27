package com.guocai.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//开启多个线程写内容到sql文件中
//只能有一个在写.其他的全部等待.

public class TSaveRoom
{
    static File roomFilterLogFile = new File("roomFilter.log");

    static WriteSqlFile writeSqlFile = new WriteSqlFile();

    //线程池
    static ExecutorService pool = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws Exception
    {
        long begin = System.currentTimeMillis();

        String path = "D:\\baiduyundownload\\如家汉庭等酒店2000W开房数据\\2000W\\1-200W.csv";
        File tempFile = new File(path);

        BufferedReader reader = new BufferedReader(new FileReader(tempFile));
        String str = null;

        int i = 0;
        while ((str = reader.readLine()) != null)
        {
            String[] strs = str.split(",");

            if (strs.length < 8)
            {
                writeLog("此条数据不录入：:0", Arrays.toString(strs));
                continue;
            }

            //数据过滤.
            //|--过滤掉 英文与特殊字符
            String name = strs[0].trim();
            // if (!ZhengzeValidate.isChina(name))
            // {
            //     writeLog("此条数据不录入：:0", Arrays.toString(strs));
            //     continue;
            // }

            try
            {
                i++;
                String card = strs[4];
                String gender = strs[5];
                String birthday = strs[6];
                String address = strs[7];
                String zip = strs[8];
                String mobile = strs[20];
                String email = strs[22];
                String version = strs[31];

                //				List<String> lists = Arrays.asList(name, card, gender, birthday, address, zip, mobile, email, version);
                //				if (version.trim().length() > 0)
                //				{
                //					System.out.println(lists);
                //					System.out.println(version);
                //				}
                //读取一条数据，保存一条进数据库
                //			System.out.println(name);
                //			System.out.println(str);
                //			System.out.println(name);
                //			System.out.println(card);
                //			System.out.println(gender);

                //生成sql语句
                String writeSqlStr = "INSERT INTO `t_room_record` VALUES (null,':0', ':1', ':2', ':3', ':4', ':5', ':6', ':7');";

                writeSqlStr = tm(writeSqlStr, name, card, gender, birthday, address, zip, mobile, email, version);

                final String tempSql = writeSqlStr;

                //				System.out.println(writeSqlStr);

                pool.execute(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            writeSqlFile.save(tempSql);
                        } catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                });

                //				System.out.println(String.format("开启了%s条线程(保存了%s条数据)", i, i));

            } catch (Exception e)
            {
                writeLog("录入错误的数据：:0", Arrays.toString(strs));
            }
        }

        writeSqlFile.flush();

        writeSqlFile.closeWrite();

        pool.shutdown();

        long end = System.currentTimeMillis() - begin;

        System.out.println(String.format("任务完成时间:%s", end));

    }

    public static void writeLog(String str, Object... values)
    {
        //可以换成 System.out.print 直接输出
        System.out.println(tm(str, values) + "\r\n");
    }

    public static String tm(String str, Object... values)
    {
        for (int i = 0; i < values.length; i++)
        {
            if (values[i] == null)
            {
                continue;
            }

            String val = values[i].toString();
            str = str.replace(":" + i, val);
        }
        return str;
    }

}

class WriteSqlFile
{
    final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    static File sqlFile = new File("roomSql.sql");

    static List<String> sqlList = new ArrayList<String>();

    static BufferedWriter bw = null;

    static
    {
        try
        {

            if (!sqlFile.exists())
            {
                if (!sqlFile.createNewFile())
                {
                    throw new RuntimeException("创建文件失败：" + sqlFile.getAbsolutePath());
                }
            }

            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sqlFile, true), "UTF-8"));

        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void flush() throws Exception
    {
        System.out.println(String.format("flush线程 线程号：%s 需要保存数据的集合长度:%s", Thread.currentThread().getId(), sqlList.size()));
        for (String str : sqlList)
        {
            bw.write(str + "\r\n");
        }

        sqlList.clear(); //清空数据

        System.out.println(String.format("flush线程 线程号：%s 完成", Thread.currentThread().getId()));
    }

    public void closeWrite() throws Exception
    {
        bw.flush();
        bw.close();

        System.out.println("关闭流");
    }

    public void save(String sqlStr) throws Exception
    {
        readWriteLock.writeLock().lock();

        if (sqlList.size() > 10000)
        {
            long begin = System.currentTimeMillis();

            System.out.println(String.format("保存线程 线程号：%s 需要保存数据的集合长度:%s", Thread.currentThread().getId(), sqlList.size()));

            for (String str : sqlList)
            {
                bw.write(str + "\r\n");
            }

            sqlList.clear(); //清空数据.

            long end = System.currentTimeMillis() - begin;

            System.out.println(String.format("保存线程 线程号：%s 任务完成时间:%s", Thread.currentThread().getId(), end));

        } else
        {
            sqlList.add(sqlStr);
            //			System.out.println("添加了一条数据：" + sqlStr);

        }

        readWriteLock.writeLock().unlock();
    }

}
