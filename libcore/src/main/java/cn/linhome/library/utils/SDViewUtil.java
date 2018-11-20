package cn.linhome.library.utils;

import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

import cn.linhome.library.SDLibrary;

@Deprecated
public class SDViewUtil
{
    /**
     * 用dialog替代PopupWindow
     *
     * @param pop
     * @param view
     * @param marginBottom
     */
    @Deprecated
    public static void showPopTop(PopupWindow pop, View view, int marginBottom)
    {
        int[] location = getLocationOnScreen(view);
        int x = location[0] - getScreenWidth() / 2 + view.getWidth() / 2;
        int y = getScreenHeight() - location[1] + marginBottom;
        pop.showAtLocation(view, Gravity.BOTTOM, x, y);
    }

    /**
     * 获得view在屏幕上的坐标
     *
     * @param view
     * @return 数组第一个元素是x坐标，第二个是y坐标
     */
    public static int[] getLocationOnScreen(View view)
    {
        int[] location = {0, 0};
        if (view != null)
        {
            view.getLocationOnScreen(location);
        }
        return location;
    }

    /**
     * 获得屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth()
    {
        DisplayMetrics metrics = getDisplayMetrics();
        return metrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @return
     */
    public static int getScreenHeight()
    {
        DisplayMetrics metrics = getDisplayMetrics();
        return metrics.heightPixels;
    }

    public static DisplayMetrics getDisplayMetrics()
    {
        return SDLibrary.getInstance().getContext().getResources().getDisplayMetrics();
    }
}
