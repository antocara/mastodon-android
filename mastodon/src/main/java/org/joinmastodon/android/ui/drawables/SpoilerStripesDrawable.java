package org.joinmastodon.android.ui.drawables;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import org.joinmastodon.android.MusktodonApp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SpoilerStripesDrawable extends Drawable{
	private Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);

	public SpoilerStripesDrawable(){
		paint.setColor(0xff000000);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(3);
	}

	@Override
	public void draw(@NonNull Canvas canvas){
		Rect bounds=getBounds();
		canvas.save();
		canvas.translate(bounds.left, bounds.top);
		canvas.clipRect(0, 0, bounds.width(), bounds.height());
		float scale= MusktodonApp.context.getResources().getDisplayMetrics().density;
		canvas.scale(scale, scale, 0, 0);

		float height=bounds.height()/scale;
		float y1=6.80133f;
		float y2=-1.22874f;
		while(y2<height){
			canvas.drawLine(-0.860365f, y1, 10.6078f, y2, paint);
			y1+=8.03007f;
			y2+=8.03007f;
		}

		canvas.restore();
	}

	@Override
	public void setAlpha(int alpha){

	}

	@Override
	public void setColorFilter(@Nullable ColorFilter colorFilter){

	}

	@Override
	public int getOpacity(){
		return PixelFormat.TRANSLUCENT;
	}
}
