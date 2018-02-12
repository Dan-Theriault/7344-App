package jasonngor.com.sustainabilitylifestylescorecard;

import android.content.Context;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by dianamilton on 2/8/18.
 */

public class Note implements Serializable {

    private long aDateTime;
    private String aTitle;
    private String aContent;

    public Note (long dateTime, String title, String content) {
        aContent = content;
        aTitle = title;
        aDateTime = dateTime;
    }

    public void setDateTime(long dateTime) {
        aDateTime = dateTime;
    }

    public void setTitle(String title) {
        aTitle = title;
    }

    public void setContent(String content) {
        aContent = content;
    }

    public long getDateTime() {
        return aDateTime;
    }

    public String getTitle() {
        return aTitle;
    }

    public String getContent() {
        return aContent;
    }

    public String getDateTimeFormatted(Context context) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy HH:mm:ss",
                context.getResources().getConfiguration().locale);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(aDateTime));
    }

}