package at.tugraz.flipvloppers.flipvloppers2015.controller;

/**
 * Created by Admin on 03.06.2015.
 */
public class EmojiController {
    public String parseEmoji(String old)
    {
        old = old.replaceAll(":smile:", "[img src=ic_pro_smile/]");
        old = old.replaceAll(":sad:", "[img src=ic_pro_sad/]");
        old = old.replaceAll(":angry:", "[img src=ic_pro_angry/]");
        old = old.replaceAll(":anonymous:", "[img src=ic_pro_anonymous/]");
        old = old.replaceAll(":bigeyes:", "[img src=ic_pro_bigeyes/]");
        old = old.replaceAll(":blink:", "[img src=ic_pro_blink/]");
        old = old.replaceAll(":coffee:", "[img src=ic_pro_coffee/]");
        old = old.replaceAll(":devil:", "[img src=ic_pro_devil/]");
        old = old.replaceAll(":gentleman:", "[img src=ic_pro_gentleman/]");
        old = old.replaceAll(":party:", "[img src=ic_pro_party/]");
        old = old.replaceAll(":thumb:", "[img src=ic_pro_thumbs_up/]");
        old = old.replaceAll(":tongue:", "[img src=ic_pro_tongue/]");
        return old;
    }
}
