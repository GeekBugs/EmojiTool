
/*
 * Copyright 2017 F1ReKing. http://F1ReKing.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.f1reking.emojitool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.SpannableString;
import android.text.Spanned;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author: huangyh
 * date: 2017/7/4 18:24
 * desc: 表情包工具类
 */
public class EmojiUtils {

    private static Map<String, Integer> emojiMap;

    static {
        emojiMap = new HashMap<>();
        emojiMap.put(":smile:", R.drawable.e_smile);
        emojiMap.put(":sad:", R.drawable.e_sad);
        emojiMap.put(":biggrin:", R.drawable.e_biggrin);
        emojiMap.put(":cry:", R.drawable.e_cry);
        emojiMap.put(":huffy:", R.drawable.e_huffy);
        emojiMap.put(":shocked:", R.drawable.e_shocked);
        emojiMap.put(":tongue:", R.drawable.e_tongue);
        emojiMap.put(":shy:", R.drawable.e_shy);
        emojiMap.put(":titter:", R.drawable.e_titter);
        emojiMap.put(":sweat:", R.drawable.e_sweat);
        emojiMap.put(":mad:", R.drawable.e_mad);
        emojiMap.put(":lol:", R.drawable.e_lol);
        emojiMap.put(":loveliness:", R.drawable.e_loveliness);
        emojiMap.put(":funk:", R.drawable.e_funk);
        emojiMap.put(":curse:", R.drawable.e_curse);
        emojiMap.put(":dizzy:", R.drawable.e_dizzy);
        emojiMap.put(":shutup:", R.drawable.e_shutup);
        emojiMap.put(":sleepy:", R.drawable.e_sleepy);
        emojiMap.put(":hug:", R.drawable.e_hug);
        emojiMap.put(":victory:", R.drawable.e_victory);
        emojiMap.put(":sun:", R.drawable.e_sun);
        emojiMap.put(":moon:", R.drawable.e_moon);
        emojiMap.put(":kiss:", R.drawable.e_kiss);
        emojiMap.put(":handshake:", R.drawable.e_handshake);
        emojiMap.put(":icon:", R.drawable.e_icon);
    }

    private static int getImgByName(String imgName) {
        Integer integer = emojiMap.get(imgName);
        return integer == null ? -1 : integer;
    }

    /**
     * 正则匹配处理表情
     *
     * @param context 上下文
     * @param content 内容
     * @param tv TextView
     * @return 处理后的内容
     */
    public static SpannableString getEmojiString(Context context, String content, TextView tv) {
        SpannableString spannableString = new SpannableString(content);
        String regex = ":[a-z]+:";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(spannableString);
        if (matcher.find()) {
            matcher.reset(); //匹配重置
            while (matcher.find()) {
                //获取匹配的字符
                String key = matcher.group();
                //匹配字符串的开始位置
                int start = matcher.start();
                //根据匹配到的表情文字标识获取到对应的表情包
                Integer imgRes = EmojiUtils.getImgByName(key);
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), imgRes);
                if (bitmap != null) {
                    int size = (int) tv.getTextSize();
                    //压缩表情包
                    bitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);
                    //设置表情和文字居中显示
                    EmojiImageSpan imageSpan = new EmojiImageSpan(context, bitmap, 2);
                    spannableString.setSpan(imageSpan, start, start + key.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }
        return spannableString;
    }
}
