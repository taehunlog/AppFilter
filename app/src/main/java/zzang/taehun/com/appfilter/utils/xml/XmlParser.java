package zzang.taehun.com.appfilter.utils.xml;


/**
 * Created by taehun on 2017-03-24.
 */

public class XmlParser {

    public static boolean resultSentPushKey(String xmlData){
        XmlWorker xmlWork = XmlWorker.makeWorker(xmlData);
        APIparser.xmlElement root = xmlWork.getRoot();
        String response = root._val("response");
        String header = root._val("header");
        String body = root._val("body");
        String items = root._val("items");
        String item = root._val("item");
        return item.equalsIgnoreCase("Y");
    }
}
