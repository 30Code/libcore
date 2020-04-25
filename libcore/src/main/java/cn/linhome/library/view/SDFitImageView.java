package cn.linhome.library.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import cn.linhome.lib.utils.FViewUtil;

public class SDFitImageView extends AppCompatImageView
{
    public SDFitImageView(Context context)
    {
        super(context);
        init();
    }

    public SDFitImageView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init();
    }

    public SDFitImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    private void init()
    {
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Drawable drawable = getDrawable();
        if (drawable != null)
        {
            FViewUtil.scaleHeight(this, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
    }
}
