package zzang.taehun.com.appfilter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

import zzang.taehun.com.appfilter.utils.newXml.XmlItem;
import zzang.taehun.com.appfilter.utils.xml.SharePreferences;

public class MainActivity extends AppCompatActivity {
    SharePreferences mPref;
    ArrayList<XmlItem> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPref = new SharePreferences(this);
        data = new ArrayList<>();
    }

    public void callWeb(View view) {
        httpCall();
    }

    public void httpCall() {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getNearbyMsrstnList?tmX=244148.546388&tmY=412423.75772&pageNo=1&numOfRows=10&ServiceKey=yjxbBD8bhhkgxWURpzKvfg4qS88k%2BFznDHef0CskHjcNSogjFB3EWKk6UX0No9aln7J3FsjIa%2B8fElMoc1Xc9Q%3D%3D&_returnType=json";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("", "xml : " + response);

                try {
                    String tempdata = "";
                    data.clear();
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    factory.setNamespaceAware(true); //xml 네임스페이스 지원 여부 설정
                    XmlPullParser xpp = factory.newPullParser();
                    xpp.setInput(new StringReader(response));
                    int eventType = xpp.getEventType();

                    boolean bSet = false;
                    XmlItem item = new XmlItem("", "", "");
                    String gTag_name="";
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        if (eventType == XmlPullParser.START_TAG) {
                            String tag_name = xpp.getName();
                            gTag_name = tag_name;
                            if (tag_name.equals("stationName") | tag_name.equals("addr") | tag_name.equals("tm"))
                                bSet = true;
                        } else if (eventType == XmlPullParser.TEXT) {
                            if (bSet) {
                                if (gTag_name.equals("stationName")) {
                                    item.setStationName(xpp.getText());
                                } else if (gTag_name.equals("addr")) {
                                    item.setAddr(xpp.getText());

                                } else if (gTag_name.equals("tm")) {
                                    item.setTm(xpp.getText());
                                }
                                bSet = false;
                            }
                        } else if (eventType == XmlPullParser.END_TAG) ;
                        eventType = xpp.next();
                        if(item.checkDataCount()){
                            data.add(item);
                            item = new XmlItem("","","");
                        }
                    }
                    Log.d("", "tempdata : " + data.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }
}
