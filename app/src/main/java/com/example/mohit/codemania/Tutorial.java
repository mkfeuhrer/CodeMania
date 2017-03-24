package com.example.mohit.codemania;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * Created by prasoon on 23/3/17.
 */

public class Tutorial {
    private String heading;
    private String description;
    private String url;
    private String website;

    Tutorial(String head, String desc, String ur, String site)
    {
        heading = head;
        description = desc;
        url = ur;
        website = site;
    }
    public String getHeading()
    {
        return heading;
    }
    public String getDescription()
    {
        return description;
    }
    public String getUrl()
    {
        return url;
    }
    public String getWebsite()
    {
        return website;
    }
}
