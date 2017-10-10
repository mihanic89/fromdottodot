package xyz.yapapa.fromdottodot.ui.component;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import static android.R.attr.screenSize;

/**
 * Created by Misha on 17.03.2017.
 */

public class SquareImageView extends AppCompatImageView {


    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
       // if (screenSize>= Configuration.SCREENLAYOUT_SIZE_LARGE) width = (width*6)%10;
        setMeasuredDimension(width, width);
    }

}