package custom.widgets.texttoggle;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * TextToggle: like a bottom
 */
public class TextToggle extends LinearLayout implements View.OnClickListener {

    int mColor;

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
        mColor = getResources().getColor(R.color.before_selection);
    }

    public boolean isPressed() {
        return mColor == getResources().getColor(R.color.after_selection);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.order_az_button) {
            Resources res = getResources();
            TextView text = (TextView) findViewById(R.id.order_az);
            int before = res.getColor(R.color.after_selection);
            int after = res.getColor(R.color.before_selection);
            mColor = mColor == before ? after : before;
            text.setTextColor(mColor);
        }
    }
}
