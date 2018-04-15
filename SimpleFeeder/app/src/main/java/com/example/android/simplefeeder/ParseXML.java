package com.example.android.simplefeeder;

import android.text.Html;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;
import static android.R.id.list;
import static android.util.Xml.newPullParser;
import static org.xmlpull.v1.XmlPullParser.END_TAG;

/**
 * Created by lenovo on 4/14/2018.
 */

public class ParseXML {

    private static final String ns = null;

    public static List<Details> parse(InputStream is) throws XmlPullParserException, IOException {

        Log.d("result", "in parse class parse method");
        try {
         //   XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(is, null);
            parser.nextTag();
            Log.d("result", parser.getName());
            Log.d("result", "in make connection  method try block");
            return readFeed(parser);
        } finally {
            is.close();
            Log.d("result", "in make connection  method final block");
        }


    }

    public static List<Details> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
       List<Details> listvalue=new ArrayList<>();
        parser.require(XmlPullParser.START_TAG, ns, "rss");
        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            String name = parser.getName();
           if (parser.getEventType() != XmlPullParser.START_TAG)
              continue;
       //     Log.d("result", parser.getName());
            if (parser.getName().equals("item")) {
                listvalue.add(readValues(parser));
            } else

           //    skip(parser);
            Log.d("result", "in else loooooooop");

        }

        return listvalue;
    }

    public static Details readValues(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "item");
        String title = null;
        String description = null;
        String link = null;

        while (parser.next() != XmlPullParser.END_DOCUMENT) {

            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            Log.d("result", name);
            if (name.equals("title"))
            {  title=readTitle(parser);
                Log.d("result", title);}

            else if(name.equals("description"))
            {
                description=readDescription(parser);
                Log.d("result", "in description condition");
                Log.d("result", description);
            }
            else if(name.equals("media:thumbnail")) {

                link = readLink(parser); Log.d("result", "in link condition");
                Log.d("result", link);
                break;
            }
            else
                ;
              //  skip(parser);

        }
        Log.d("result", title);
        Log.d("result", description);
        Log.d("result", link);
        return new Details(title,description,link);

    }
    public static String readTitle(XmlPullParser parser)throws XmlPullParserException,IOException
    {
     parser.require(XmlPullParser.START_TAG,ns,"title");
        String val=readText(parser);
        parser.require(END_TAG,ns,"title");
        return Html.fromHtml(val).toString();

    }
    public static String readDescription(XmlPullParser parser)throws XmlPullParserException,IOException
    {
        parser.require(XmlPullParser.START_TAG,ns,"description");
        String val=readText(parser);
        parser.require(END_TAG,ns,"description");
        return Html.fromHtml(val).toString();

    }
    public static String readLink(XmlPullParser parser)throws XmlPullParserException,IOException
    {
        parser.require(XmlPullParser.START_TAG, ns, "media:thumbnail");
        String thumbnailUrl = parser.getAttributeValue(null, "url");
        parser.nextTag();
        return thumbnailUrl;
    }
    public static String readText(XmlPullParser parser)throws XmlPullParserException,IOException
    {
     String result="";
        if(parser.next()==XmlPullParser.TEXT)
        {
            result=parser.getText();
            parser.nextTag();
        }
        return result;
    }
    public static void skip(XmlPullParser parser)throws XmlPullParserException,IOException

    {
        if(parser.getEventType()!=XmlPullParser.START_TAG)
            throw new IllegalStateException();
        int depth=1;
        parser.next();
        while(depth!=0)
        {


                if (parser.getEventType() == XmlPullParser.START_TAG) {
                    depth++;
                    parser.next();
                } else if (parser.getEventType() == END_TAG) {
                    depth--;
                    parser.next();
                } else
                    parser.next();

        }
    }
}
