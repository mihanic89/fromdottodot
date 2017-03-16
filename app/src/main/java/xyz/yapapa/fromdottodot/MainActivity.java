package xyz.yapapa.fromdottodot;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
        implements ColorPickerDialog.OnColorChangedListener
{
            int[] intDrawables ;
            int i=0;
            AlertDialog dialog;
             MyPaintView mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        loadDrawables();
        RelativeLayout Rlmain = (RelativeLayout) findViewById(R.id.rl);
        RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        //final MyPaintView mv = (MyPaintView) findViewById(R.id.myPaintView);
        mv= new MyPaintView(this);
        Rlmain.addView (mv,lParams);
        mv.setDrawingCacheEnabled(true);
        mv.setBackgroundResource(intDrawables[i]);//set the back ground if you wish to

        // setContentView(mv);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(0xFFFF0000);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(20);
        mEmboss = new EmbossMaskFilter(new float[] { 1, 1, 1 },
                0.4f, 6, 3.5f);
        mBlur = new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL);

       ImageButton btnColor = (ImageButton) findViewById(R.id.btnColor);
        btnColor.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             new ColorPickerDialog(MainActivity.this, MainActivity.this, mPaint.getColor()).show();
         }
        });

        ImageButton btnUndo = (ImageButton) findViewById(R.id.btnUndo);
        btnUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //undo last paint
            }
        });

        ImageButton btnDelete = (ImageButton) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clear all draw
                Toast.makeText(MainActivity.this, "click!",
                        Toast.LENGTH_LONG).show();
                mv.clearPath();

            }
        });

        ImageButton btnNextPic = (ImageButton) findViewById(R.id.btnNextPic);
        btnNextPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                if (i<intDrawables.length )
                {mv.setBackgroundResource(intDrawables[i]);}
                else {i=0;
                    mv.setBackgroundResource(intDrawables[i]);}



            }
        });

        ImageButton btnPrevPic = (ImageButton) findViewById(R.id.btnPrevPic);
        btnPrevPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i--;
                if (i<0 )
                {i=intDrawables.length-1;
                    mv.setBackgroundResource(intDrawables[i]);}
                else {mv.setBackgroundResource(intDrawables[i]);}

            }
        });
    }



    private void loadDrawables() {
        intDrawables = new int[]{R.drawable.d01,
                R.drawable.d02,
                R.drawable.d03,
                R.drawable.d04,
                R.drawable.d05,
                R.drawable.d06,
                R.drawable.d07,
                R.drawable.d08,
                R.drawable.d09,
                                    };
    }

    private Paint       mPaint;
            private MaskFilter mEmboss;
            private MaskFilter  mBlur;

            public void colorChanged(int color) {
                mPaint.setColor(color);
            }



            public class MyPaintView extends View {

                private static final float MINP = 0.25f;
                private static final float MAXP = 0.75f;
                private Bitmap  mBitmap;
                private Canvas mCanvas;
                private Path mPath;
                private Paint   mBitmapPaint;
                Context context;

                public MyPaintView(Context context) {
                    super(context);

                    this.context=context;
                    mPath = new Path();
                    mBitmapPaint = new Paint(Paint.DITHER_FLAG);

                }

                public MyPaintView (android.content.Context context, android.util.AttributeSet attrs){
                    super(context, attrs);
                    this.context=context;
                    mPath = new Path();
                    mBitmapPaint = new Paint(Paint.DITHER_FLAG);
                }

                public void clearPath(){
                    mPaint.setXfermode(null);
                    mPaint.setAlpha(0xFF);

                    mPath = new Path();
                    Paint mPaint = new Paint();
                    mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                    mPaint.setAlpha(0x80);
                    mCanvas.drawRect(0, 0, 0, 0, mPaint);

                }


                public MyPaintView(Context context, AttributeSet attrs, int defStyle) {
                    super(context, attrs, defStyle);
                    this.context=context;
                    mPath = new Path();
                    mBitmapPaint = new Paint(Paint.DITHER_FLAG);
                }



                @Override
                protected void onSizeChanged(int w, int h, int oldw, int oldh) {
                    super.onSizeChanged(w, h, oldw, oldh);
                    mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                    mCanvas = new Canvas(mBitmap);

                }

                @Override
                protected void onDraw(Canvas canvas) {
                    super.onDraw(canvas);


                    canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);

                    canvas.drawPath(mPath, mPaint);
                }

                private float mX, mY;
                private static final float TOUCH_TOLERANCE = 4;

                private void touch_start(float x, float y) {
//showDialog();
                    mPath.reset();
                    mPath.moveTo(x, y);
                    mX = x;
                    mY = y;

                }
                private void touch_move(float x, float y) {
                    float dx = Math.abs(x - mX);
                    float dy = Math.abs(y - mY);
                    if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                        mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
                        mX = x;
                        mY = y;
                    }
                }
                private void touch_up() {
                    mPath.lineTo(mX, mY);
// commit the path to our offscreen
                    mCanvas.drawPath(mPath, mPaint);
// kill this so we don't double draw
                    mPath.reset();
                    mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
//mPaint.setMaskFilter(null);
                }

                @Override
                public boolean onTouchEvent(MotionEvent event) {
                    float x = event.getX();
                    float y = event.getY();

                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            touch_start(x, y);
                            invalidate();
                            break;
                        case MotionEvent.ACTION_MOVE:

                            touch_move(x, y);
                            invalidate();
                            break;
                        case MotionEvent.ACTION_UP:
                            touch_up();
                            invalidate();
                            break;
                    }
                    return true;
                }
            }

            private static final int COLOR_MENU_ID = Menu.FIRST;
            private static final int EMBOSS_MENU_ID = Menu.FIRST + 1;
            private static final int BLUR_MENU_ID = Menu.FIRST + 2;
            private static final int ERASE_MENU_ID = Menu.FIRST + 3;
            private static final int SRCATOP_MENU_ID = Menu.FIRST + 4;
            private static final int Save = Menu.FIRST + 5;

            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                super.onCreateOptionsMenu(menu);

                menu.add(0, COLOR_MENU_ID, 0, "Color").setShortcut('3', 'c');
                menu.add(0, EMBOSS_MENU_ID, 0, "Emboss").setShortcut('4', 's');
                menu.add(0, BLUR_MENU_ID, 0, "Blur").setShortcut('5', 'z');
                menu.add(0, ERASE_MENU_ID, 0, "Erase").setShortcut('5', 'z');
                menu.add(0, SRCATOP_MENU_ID, 0, "SrcATop").setShortcut('5', 'z');
                menu.add(0, Save, 0, "Save").setShortcut('5', 'z');

                return true;
            }

            @Override
            public boolean onPrepareOptionsMenu(Menu menu) {
                super.onPrepareOptionsMenu(menu);
                return true;
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                mPaint.setXfermode(null);
                mPaint.setAlpha(0xFF);

                switch (item.getItemId()) {
                    case COLOR_MENU_ID:
                        new ColorPickerDialog(this, this, mPaint.getColor()).show();
                        return true;
                    case EMBOSS_MENU_ID:
                        if (mPaint.getMaskFilter() != mEmboss) {
                            mPaint.setMaskFilter(mEmboss);
                        } else {
                            mPaint.setMaskFilter(null);
                        }
                        return true;
                    case BLUR_MENU_ID:
                        if (mPaint.getMaskFilter() != mBlur) {
                            mPaint.setMaskFilter(mBlur);
                        } else {
                            mPaint.setMaskFilter(null);
                        }
                        return true;
                    case ERASE_MENU_ID:
                        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                        mPaint.setAlpha(0x80);
                        return true;
                    case SRCATOP_MENU_ID:

                        mPaint.setXfermode(new PorterDuffXfermode(
                                PorterDuff.Mode.SRC_ATOP));
                        mPaint.setAlpha(0x80);
                        return true;
                    case Save:
                        /*
                        AlertDialog.Builder editalert = new AlertDialog.Builder(MainActivity.this);
                        editalert.setTitle("Please Enter the name with which you want to Save");
                        final EditText input = new EditText(MainActivity.this);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.FILL_PARENT,
                                LinearLayout.LayoutParams.FILL_PARENT);
                        input.setLayoutParams(lp);
                        editalert.setView(input);
                        editalert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                String name= input.getText().toString();
                                Bitmap bitmap = mv.getDrawingCache();

                                String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                                File file = new File("/sdcard/"+name+".png");
                                try
                                {
                                    if(!file.exists())
                                    {
                                        file.createNewFile();
                                    }
                                    FileOutputStream ostream = new FileOutputStream(file);
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 10, ostream);
                                    ostream.close();
                                    mv.invalidate();
                                }
                                catch (Exception e)
                                {
                                    e.printStackTrace();
                                }finally
                                {

                                    mv.setDrawingCacheEnabled(false);
                                }
                            }
                        });

                        editalert.show();
                        */
                        return true;
                }
                return super.onOptionsItemSelected(item);
            }

        }