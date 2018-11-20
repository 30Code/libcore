package cn.linhome.library.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * json文件解析
 */
public class ReadJsonFileUtil
{
    /**
     * 读取SD卡的json文件
     * @param fileName
     * @return
     */
    public static String getFileJson(String fileName)
    {
        StringBuilder sb = new StringBuilder();
        try
        {
            FileInputStream fs = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));

            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }

            br.close();
            fs.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return sb.toString();
    }

    /**
     * 读取assets的json文件
     * @param fileName
     * @return
     */
    public String getAssetsJson(Context context, String fileName)
    {
        StringBuilder sb = new StringBuilder();
        try
        {
            InputStreamReader isr = new InputStreamReader(context.getAssets().open(fileName), "UTF-8");//读取assets里的json文件
            BufferedReader br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }

            br.close();
            isr.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
