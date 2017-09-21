package com.f1reking.emojitool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvEmoji;
    public static final String TEXT =
        "这一段测试文字,可以中:smile:间夹表:cry:情，也可以一下子好多表情:shy::dizzy::hug:，只要符合表情规则的图片也可以:icon:，也可以自定义规则加表情。";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tvEmoji = (TextView) findViewById(R.id.tv_emoji);
        tvEmoji.append(EmojiUtils.getEmojiString(this, TEXT, tvEmoji));
    }
}
