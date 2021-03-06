package org.dpadgett.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class CountdownTextView extends TextView {

	// the timestamp which we are counting down to
	private long endingTime = 0;
	
	// a prefix to prepend to the timer text
	private String textPrefix = "";

	public CountdownTextView(Context context) {
		super(context);
	}

	public CountdownTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CountdownTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setEndingTime(long endingTime) {
		this.endingTime  = endingTime;
	}
	
	public void setTextPrefix(String textPrefix) {
		this.textPrefix = textPrefix;
	}
	
	public void forceUpdate(long timestamp) {
		setTimerText(timestamp);
	}
	
	private void setTimerText(long timestamp) {
		long remaining = Math.max(0, endingTime - timestamp);
		setText(textPrefix + getTimerText(remaining));
	}

	private void setTimerText() {
		setTimerText(System.currentTimeMillis());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		setTimerText();
		super.onDraw(canvas);
	}

	private static String getTimerText(long remainingTime) {
		if (remainingTime % 1000 > 0) {
			remainingTime += 1000;
		}
		remainingTime /= 1000;
		long secs = remainingTime % 60;
		remainingTime /= 60;
		long mins = remainingTime % 60;
		remainingTime /= 60;
		long hours = remainingTime;
		return String.format("%02d:%02d:%02d", hours, mins, secs);
	}
}
