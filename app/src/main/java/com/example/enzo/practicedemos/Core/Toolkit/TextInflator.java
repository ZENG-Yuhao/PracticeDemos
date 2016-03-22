package com.example.enzo.practicedemos.Core.Toolkit;

/**
 * Created by Enzo(ZENG Yuhao) on 16/3/22.
 */
public class TextInflator
{
    private static final String EMPTY_STR = "";
    private static final String LINE_BREAK = "\n";

    public static final int MODE_LINE_NUM = 0;
    public static final int MODE_NO_LINE_NUM = 1;

    public static String inflate()
    {
        return inflate("TEXT TEXT TEXT TEXT ", 100, MODE_LINE_NUM);
    }

    public static String inflate(int repeat)
    {
        return inflate("TEXT TEXT TEXT TEXT ", repeat, MODE_LINE_NUM);
    }

    public static String inflate(String textToInflate, int repeat)
    {
        return inflate(textToInflate, repeat, MODE_LINE_NUM);
    }

    public static String inflate(String textToInflate, int repeat, int mode)
    {
        if (textToInflate.length() == 0) return "";

        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < repeat; i++)
        {
            String line_num = (mode == MODE_LINE_NUM) ? (":" + i) : (EMPTY_STR);
            strBuilder.append(textToInflate);
            strBuilder.append(line_num);
            strBuilder.append(LINE_BREAK);
        }

        return strBuilder.toString();
    }
}
