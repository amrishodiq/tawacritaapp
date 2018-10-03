package shodiq.amri.tawacrita.topic;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Attr;

import shodiq.amri.tawacrita.R;

public class TopicView extends CardView implements View.OnClickListener {

    private ImageView avatar;
    private TextView title;
    private LinearLayout content;
    private TopicModel model = new TopicModel("");
    private int leftWidth = 2, rightWidth = 3;

    public TopicView(Context context) {
        super(context);
        initViews(context);
    }

    public TopicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
        initAttribute(attrs);
    }

    public TopicView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews(context);
        initAttribute(attrs);
    }

    private void initViews(Context context) {
        ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.view_topic, this);
    }

    private void initAttribute(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TopicView);
        leftWidth = typedArray.getInt(R.styleable.TopicView_leftWidth, 1);
        rightWidth = typedArray.getInt(R.styleable.TopicView_rightWidth, 1);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        avatar = findViewById(R.id.avatar);
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);

        findViewById(R.id.expand).setOnClickListener(this);
    }

    public void expandContent() {
        findViewById(R.id.divider).setVisibility(GONE);
        findViewById(R.id.content).setVisibility(GONE);
    }

    public void shrinkContent() {
        findViewById(R.id.divider).setVisibility(VISIBLE);
        findViewById(R.id.content).setVisibility(VISIBLE);
    }

    public void setModel(TopicModel model) {
        this.model = model;
        notifyDataSetChanged();
    }

    public TopicModel getModel() {
        return model;
    }

    public void notifyDataSetChanged() {
        this.title.setText(model.title);
        this.content.removeAllViews();
        for (KeyValue item: model.items) {
            addContent(item);
        }
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public String getTitle() {
        return (String) title.getText();
    }

    public void addContent(KeyValue item) {
        LinearLayout panel = new LinearLayout(getContext());
        panel.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        panel.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams layoutParamKey = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                (float) rightWidth
        );
        LinearLayout.LayoutParams layoutParamValue = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                (float) leftWidth
        );

        TextView key = new TextView(getContext(), null, R.style.TopicContentKey);
        key.setText(item.key);
        key.setLayoutParams(layoutParamKey);
        panel.addView(key);

        TextView value = new TextView(getContext(), null, R.style.TopicContentValue);
        value.setText(item.value);
        value.setLayoutParams(layoutParamValue);
        panel.addView(value);

        content.addView(panel);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.expand:
                Log.d("AMRI", "Clicked");
                Log.d("AMRI", content.getVisibility()+" "+GONE);
                if (content.getVisibility() == VISIBLE) {
                    expandContent();
                } else {
                    shrinkContent();
                }
                break;
        }
    }
}
