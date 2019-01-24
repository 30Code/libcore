package cn.linhome.library.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

import cn.linhome.library.utils.DividerItemDecorationExtend;

/**
 * Created by Administrator on 2016/8/30.
 */
public class SDRecyclerView extends RecyclerView
{
    public SDRecyclerView(Context context)
    {
        super(context);
        init();
    }

    public SDRecyclerView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public SDRecyclerView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init();
    }

    private void init()
    {
        setLinearLayoutManager(RecyclerView.VERTICAL);
    }

    //----------Linear----------

    /**
     * 设置线性布局管理器
     *
     * @param orientation {@link RecyclerView#VERTICAL} 或者 {@link RecyclerView#HORIZONTAL}
     */
    public void setLinearLayoutManager(int orientation)
    {
        if (orientation == RecyclerView.VERTICAL || orientation == RecyclerView.HORIZONTAL)
        {
            final LinearLayoutManager manager = new LinearLayoutManager(getContext());
            manager.setOrientation(orientation);
            setLayoutManager(manager);
        } else
        {
            throw new IllegalArgumentException("orientation must be RecyclerView.VERTICAL or RecyclerView.HORIZONTAL");
        }
    }

    /**
     * 返回线性布局管理器
     *
     * @return
     */
    public LinearLayoutManager getLinearLayoutManager()
    {
        final LayoutManager manager = getLayoutManager();
        if (manager instanceof LinearLayoutManager)
        {
            return (LinearLayoutManager) manager;
        }
        return null;
    }

    //----------Grid----------

    /**
     * 设置网格布局管理器
     *
     * @param orientation {@link RecyclerView#VERTICAL} 或者 {@link RecyclerView#HORIZONTAL}
     * @param spanCount   单行或者单列的网格数量
     */
    public void setGridLayoutManager(int orientation, int spanCount)
    {
        if (orientation == RecyclerView.VERTICAL || orientation == RecyclerView.HORIZONTAL)
        {
            final GridLayoutManager manager = new GridLayoutManager(getContext(), spanCount);
            manager.setOrientation(orientation);
            setLayoutManager(manager);
        } else
        {
            throw new IllegalArgumentException("orientation must be RecyclerView.VERTICAL or RecyclerView.HORIZONTAL");
        }
    }

    /**
     * 返回网格布局管理器
     *
     * @return
     */
    public GridLayoutManager getGridLayoutManager()
    {
        final LayoutManager manager = getLayoutManager();
        if (manager instanceof GridLayoutManager)
        {
            return (GridLayoutManager) manager;
        }
        return null;
    }

    //----------StaggeredGrid----------

    /**
     * 设置瀑布流布局管理器(仅讨论竖直方向)
     * @param spanCount   单行或者单列的网格数量
     */
    public void setStaggeredGridLayoutManager(int spanCount)
    {
        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(spanCount, RecyclerView.VERTICAL);
        setLayoutManager(manager);
    }

    /**
     * 返回瀑布流布局管理器
     *
     * @return
     */
    public StaggeredGridLayoutManager getStaggeredGridLayoutManager()
    {
        final LayoutManager manager = getLayoutManager();
        if (manager instanceof StaggeredGridLayoutManager)
        {
            return (StaggeredGridLayoutManager) manager;
        }
        return null;
    }

    //----------scroll----------

    /**
     * 获得item的数量
     *
     * @return
     */
    public int getItemCount()
    {
        final Adapter adapter = getAdapter();
        if (adapter != null)
        {
            return adapter.getItemCount();
        }
        return 0;
    }

    /**
     * 是否处于RecyclerView.SCROLL_STATE_IDLE空闲状态
     *
     * @return
     */
    public boolean isIdle()
    {
        return getScrollState() == RecyclerView.SCROLL_STATE_IDLE;
    }

    /**
     * 滚动到布局开始的位置
     */
    public void scrollToStart()
    {
        scrollToPosition(0);
    }

    /**
     * 滚动到布局结束的位置
     */
    public void scrollToEnd()
    {
        final int count = getItemCount();
        if (count > 0)
        {
            scrollToPosition(count - 1);
        }
    }

    //----------divider start----------

    /**
     * 添加横分割线
     *
     * @param drawable
     */
    public void addDividerHorizontal(Drawable drawable)
    {
        addDividerHorizontal(drawable, 0);
    }

    /**
     * 添加横分割线
     *
     * @param drawable
     * @param padding  分割线左右padding
     */
    public void addDividerHorizontal(Drawable drawable, int padding)
    {
        DividerItemDecorationExtend divider = new DividerItemDecorationExtend(DividerItemDecorationExtend.HORIZONTAL);
        divider.setDrawable(drawable);
        divider.setPadding(padding);
        addItemDecoration(divider);
    }

    /**
     * 添加竖分割线
     *
     * @param drawable
     */
    public void addDividerVertical(Drawable drawable)
    {
        addDividerVertical(drawable, 0);
    }

    /**
     * 添加竖分割线
     *
     * @param drawable
     * @param padding  分割线上下padding
     */
    public void addDividerVertical(Drawable drawable, int padding)
    {
        DividerItemDecorationExtend divider = new DividerItemDecorationExtend(DividerItemDecorationExtend.VERTICAL);
        divider.setDrawable(drawable);
        divider.setPadding(padding);
        addItemDecoration(divider);
    }

    //----------divider end----------

    /**
     * 第一个item是否完全可见
     *
     * @return
     */
    public boolean isFirstItemCompletelyVisible()
    {
        boolean result = false;
        int count = getItemCount();
        if (count > 0)
        {
            LayoutManager layoutManager = getLayoutManager();
            if (layoutManager instanceof GridLayoutManager)
            {
                if (getGridLayoutManager().findFirstCompletelyVisibleItemPosition() == 0)
                {
                    result = true;
                }
            } else if (layoutManager instanceof LinearLayoutManager)
            {
                if (getLinearLayoutManager().findFirstCompletelyVisibleItemPosition() == 0)
                {
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * 最后一个item是否完全可见
     *
     * @return
     */
    public boolean isLastItemCompletelyVisible()
    {
        boolean result = false;

        int count = getItemCount();
        if (count > 0)
        {
            LayoutManager layoutManager = getLayoutManager();
            if (layoutManager instanceof GridLayoutManager)
            {
                if (getGridLayoutManager().findLastCompletelyVisibleItemPosition() == count - 1)
                {
                    result = true;
                }
            } else if (layoutManager instanceof LinearLayoutManager)
            {
                if (getLinearLayoutManager().findLastCompletelyVisibleItemPosition() == count - 1)
                {
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public void onScrollStateChanged(int state)
    {
        super.onScrollStateChanged(state);
        if (isIdle())
        {
            if (isLastItemCompletelyVisible() && mIsSlidingToLast)
            {
                if (mOnScrollCallBack != null)
                {
                    mOnScrollCallBack.onLoadMore();
                }
            }
        }
    }

    @Override
    public void onScrolled(int dx, int dy)
    {
        super.onScrolled(dx, dy);
        //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
        if (dx > 0 || dy > 0)
        {
            //大于0表示正在向(右)下滚动
            mIsSlidingToLast = true;
        } else
        {
            //小于等于0表示停止或向(左)上滚动
            mIsSlidingToLast = false;
        }
    }

    // 用来标记是否正在向最后一个滑动
    private boolean mIsSlidingToLast= false;

    private OnScrollCallBack mOnScrollCallBack;

    public void addOnScrollCallBack(OnScrollCallBack onScrollCallBack)
    {
        this.mOnScrollCallBack = onScrollCallBack;
    }

    public interface OnScrollCallBack
    {
        // 加载更多
        void onLoadMore();
    }
}
