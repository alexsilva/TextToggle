package custom.widgets.texttoggle;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * TextToggle: like a bottom
 */
public class TextToggle extends LinearLayout implements View.OnClickListener {

    public interface OnToggleListener {
        public void onToggle(boolean pressed);
    }
    OnToggleListener onToggleListener;
    int color, colorBefore, colorAfter;

    public TextToggle(Context context) {
        super(context);
        initialize(context);
    }

    public TextToggle(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    protected void initialize(Context context) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.texttoggle, this, true);

        findViewById(R.id.order_az_button).setOnClickListener(this);

        colorBefore = getResources().getColor(R.color.before_selection);
        colorAfter = getResources().getColor(R.color.after_selection);
    }

    public void setOnToggleListener(OnToggleListener onToggleListener) {
        this.onToggleListener = onToggleListener;
    }

    public boolean isPressed() {
        return color == colorAfter;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.order_az_button) {
            TextView text = (TextView) findViewById(R.id.order_az);
            text.setTextColor((color = color == colorBefore ? colorAfter : colorBefore));
            if (onToggleListener != null) {
                onToggleListener.onToggle(isPressed());
            }
        }
    }
}
