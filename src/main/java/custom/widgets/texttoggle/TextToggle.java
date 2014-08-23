package custom.widgets.texttoggle;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * TextToggle: like a bottom
 */
public class TextToggle extends LinearLayout implements View.OnClickListener {

    OnToggleListener onToggleListener;
    View layoutButton;
    TextView textView;
    int color, colorBefore, colorAfter;

    public TextToggle(Context context) {
        super(context);
        initialize(context);
    }

    public TextToggle(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public TextToggle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize(context);
    }

    protected void initialize(Context context) {
        Resources res = getResources();
        inflate(context, R.layout.texttoggle, this);

        layoutButton = findViewById(R.id.layout_button);
        layoutButton.setOnClickListener(this);

        textView = (TextView) findViewById(R.id.text);

        colorBefore = res.getColor(R.color.before_selection);
        colorAfter = res.getColor(R.color.after_selection);
    }

    public void setText(CharSequence text) {
        textView.setText(text);
    }

    public void setOnToggleListener(OnToggleListener onToggleListener) {
        this.onToggleListener = onToggleListener;
    }

    public boolean isPressed() {
        return color == colorAfter;
    }

    public void setPressed(boolean pressed) {
        textView.setTextColor((color = pressed ? colorAfter : colorBefore));
    }

    public void setClicked() {
        onClick(layoutButton);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.layout_button) {
            if (onToggleListener != null) {
                setPressed(!isPressed());
                onToggleListener.onToggle(this, isPressed());
            }
        }
    }

    public interface OnToggleListener {
        public void onToggle(TextToggle view, boolean pressed);
    }
}
