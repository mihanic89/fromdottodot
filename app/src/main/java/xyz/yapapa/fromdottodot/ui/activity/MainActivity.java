package xyz.yapapa.fromdottodot.ui.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import org.xdty.preference.colorpicker.ColorPickerDialog;
import org.xdty.preference.colorpicker.ColorPickerSwatch;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.yapapa.fromdottodot.R;
import xyz.yapapa.fromdottodot.manager.FileManager;
import xyz.yapapa.fromdottodot.manager.PermissionManager;
import xyz.yapapa.fromdottodot.ui.component.DrawingView;
import xyz.yapapa.fromdottodot.ui.dialog.StrokeSelectorDialog;


public class MainActivity extends AppCompatActivity
{
	@Bind(R.id.main_drawing_view)
	DrawingView mDrawingView;
	//@Bind(R.id.main_fill_iv)    ImageView mFillBackgroundImageView;
	@Bind(R.id.main_color_iv)   ImageView mColorImageView;
	@Bind(R.id.main_stroke_iv)  ImageView mStrokeImageView;
	@Bind(R.id.main_undo_iv)    ImageView mUndoImageView;
	@Bind(R.id.main_redo_iv)    ImageView mRedoImageView;
    @Bind(R.id.prev_pic)        ImageView mPrevImageView;
    @Bind(R.id.next_pic)        ImageView mNextImageView;
    @Bind(R.id.share)           ImageView mShareImageView;
    @Bind(R.id.delete)          ImageView mDeleteImageView;

	private int mCurrentBackgroundColor;
	private int mCurrentColor;
	private int mCurrentStroke;
	private static final int MAX_STROKE_WIDTH = 50;
    int[] intDrawables ;
    int i=0;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        loadDrawables();
		ButterKnife.bind(this);

		initDrawingView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.action_share:
				requestPermissionsAndSaveBitmap();
				break;
			case R.id.action_clear:
				mDrawingView.clearCanvas();
				break;
		}

		return super.onOptionsItemSelected(item);
	}

	private void initDrawingView()
	{
		mCurrentBackgroundColor = ContextCompat.getColor(this, android.R.color.white);
		mCurrentColor = ContextCompat.getColor(this, android.R.color.black);
		mCurrentStroke = 10;
		mDrawingView.setDrawingCacheEnabled(true);
		mDrawingView.setBackgroundResource(R.drawable.d01);//set the back ground if you wish to
		mDrawingView.setPaintColor(mCurrentColor);
		mDrawingView.setPaintStrokeWidth(mCurrentStroke);

	}

	private void startFillBackgroundDialog()
	{
		int[] colors = getResources().getIntArray(R.array.palette);

		ColorPickerDialog dialog = ColorPickerDialog.newInstance(R.string.color_picker_default_title,
				colors,
				mCurrentBackgroundColor,
				5,
				ColorPickerDialog.SIZE_SMALL);

		dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener()
		{

			@Override
			public void onColorSelected(int color)
			{
				mCurrentBackgroundColor = color;
				mDrawingView.setBackgroundColor(mCurrentBackgroundColor);
			}

		});

		dialog.show(getFragmentManager(), "ColorPickerDialog");
	}

	private void startColorPickerDialog()
	{
		int[] colors = getResources().getIntArray(R.array.palette);

		ColorPickerDialog dialog = ColorPickerDialog.newInstance(R.string.color_picker_default_title,
				colors,
				mCurrentColor,
				5,
				ColorPickerDialog.SIZE_SMALL);

		dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener()
		{

			@Override
			public void onColorSelected(int color)
			{
				mCurrentColor = color;
				mDrawingView.setPaintColor(mCurrentColor);
			}

		});

		dialog.show(getFragmentManager(), "ColorPickerDialog");
	}

	private void startStrokeSelectorDialog()
	{
		StrokeSelectorDialog dialog = StrokeSelectorDialog.newInstance(mCurrentStroke, MAX_STROKE_WIDTH);

		dialog.setOnStrokeSelectedListener(new StrokeSelectorDialog.OnStrokeSelectedListener()
		{
			@Override
			public void onStrokeSelected(int stroke)
			{
				mCurrentStroke = stroke;
				mDrawingView.setPaintStrokeWidth(mCurrentStroke);
			}
		});

		dialog.show(getSupportFragmentManager(), "StrokeSelectorDialog");
	}

	private void startShareDialog(Uri uri)
	{
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SEND);
		intent.setType("image/*");

		intent.putExtra(Intent.EXTRA_SUBJECT, "");
		intent.putExtra(Intent.EXTRA_TEXT, "");
		intent.putExtra(Intent.EXTRA_STREAM, uri);
		startActivity(Intent.createChooser(intent, "Share Image"));
	}

	private void requestPermissionsAndSaveBitmap()
	{
		if (PermissionManager.checkWriteStoragePermissions(this))
		{
			Uri uri = FileManager.saveBitmap(mDrawingView.getBitmap());
			startShareDialog(uri);
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
	{
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		switch (requestCode)
		{
			case PermissionManager.REQUEST_WRITE_STORAGE:
			{
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
				{
					Uri uri = FileManager.saveBitmap(mDrawingView.getBitmap());
					startShareDialog(uri);
				} else
				{
					Toast.makeText(this, "The app was not allowed to write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
				}
			}
		}
	}

	/*@OnClick(R.id.main_fill_iv)
	public void onBackgroundFillOptionClick()
	{
		startFillBackgroundDialog();
	}
	*/

	@OnClick(R.id.main_color_iv)
	public void onColorOptionClick()
	{
		startColorPickerDialog();
	}

	@OnClick(R.id.main_stroke_iv)
	public void onStrokeOptionClick()
	{
		startStrokeSelectorDialog();
	}

	@OnClick(R.id.main_undo_iv)
	public void onUndoOptionClick()
	{
		mDrawingView.undo();
	}

	@OnClick(R.id.share)
	public void onShareOptionClick()
	{
        requestPermissionsAndSaveBitmap();
	}

    @OnClick(R.id.delete)
    public void onDeleteOptionClick()
    {
        mDrawingView.clearCanvas();
    }

    @OnClick(R.id.main_redo_iv)
    public void onRedoOptionClick()
    {
        mDrawingView.redo();
    }

    @OnClick(R.id.prev_pic)
    public void onPrevOptionClick()
    {
        mDrawingView.clearCanvas();
        i--;
        if (i<0 )
        {i=intDrawables.length-1;
            mDrawingView.setBackgroundResource(intDrawables[i]);}
        else {mDrawingView.setBackgroundResource(intDrawables[i]);}
    }

    @OnClick(R.id.next_pic)
    public void onNextOptionClick(){
        mDrawingView.clearCanvas();
        i++;
        if (i<intDrawables.length )
        {mDrawingView.setBackgroundResource(intDrawables[i]);}
        else {i=0;
            mDrawingView.setBackgroundResource(intDrawables[i]);}
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
}
