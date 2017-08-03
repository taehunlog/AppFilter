package zzang.taehun.com.appfilter.utils.xml;

import android.util.Log;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


class XmlWorker {
	private static final String TAG="XmlWorker";
	private APIparser apiHandler = null;

	private void SetHandler(APIparser _h){
		this.apiHandler = _h;
	}
	
	public  synchronized static XmlWorker makeWorker(String doc){

		XmlWorker work = new XmlWorker();
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			APIparser handler = new APIparser();
			reader.setContentHandler(handler);
			try{
				reader.parse(doc);
			}catch(Exception ex1){

				String str = doc;
				str = str.replaceAll("&", "%26");

				SAXParserFactory factory2 = SAXParserFactory.newInstance();
				SAXParser parser2 = factory2.newSAXParser();
				XMLReader reader2 = parser2.getXMLReader();
				APIparser handler2 = new APIparser();
				reader2.setContentHandler(handler2);
				InputStream istream2 = new ByteArrayInputStream(str.getBytes("UTF-8"));
				reader2.parse(new InputSource(istream2));
				work.SetHandler( handler2);				
				return work;
			}
			work.SetHandler( handler);
		}
		catch(Exception ex){
			ex.printStackTrace();
			Log.d(TAG ,doc);
			return null;
		}
		return work;
	}

	public APIparser.xmlElement getRoot(){
		return apiHandler.rootEl;
	}
}
