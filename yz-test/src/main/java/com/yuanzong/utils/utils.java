package com.yuanzong.utils;

import java.io.File;

/**
 * @author xiechaofeng
 * @date 2018/8/17 9:46
 * @desc
 */
public class utils
{
        public static String getDataDir(Class c) {
            File dir = new File(System.getProperty("user.dir"));
            dir = new File(dir, "src");
            dir = new File(dir, "main");
            dir = new File(dir, "resources");

            return dir.toString() + File.separator;
        }
}

