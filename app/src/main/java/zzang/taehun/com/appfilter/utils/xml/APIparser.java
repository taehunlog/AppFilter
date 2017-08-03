package zzang.taehun.com.appfilter.utils.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;

class APIparser extends DefaultHandler {
	private int EL_depth = -1;
	private boolean currentElement = false;
	
	class xmlElement {
		xmlElement parent = null;
		LinkedList<xmlElement> childs = null;
		String nodeName = "";
		String nodeValue = "";
		private String fndName = "";
		private int fndLocation = -1;
		
		public String _name(){
			return nodeName;
		}
		
		String _val(){
			return nodeValue;
		}
		
		public String _val(String name){
			xmlElement cld = _child(name);
			if(cld != null){
				return cld._val(); 
			}
			return "";
		}
		public String _val_NULL(String name){
			xmlElement cld = _child(name);
			if(cld != null){
				return cld._val(); 
			}
			return null;
		}
		
		public int _child(){
			if(childs != null && childs.size() > 0){
				return childs.size();
			}
			return 0;
		}
		
		xmlElement _child(int location){
			if( childs==null || location >= childs.size())
				return null;
			try{
				return childs.get(location);
			}catch(Exception e){
				e.printStackTrace();
				return null;

			}			
		}
		
		xmlElement _child(String name){
			if(childs != null){
				if(fndName.equals(name) && fndLocation >= 0){
					return _child(fndLocation);
				}else{
					fndName = "";
					fndLocation = -1;
					for(int i=0;i < childs.size();i++){
						if(childs.get(i).nodeName.equalsIgnoreCase(name)){
							fndName = name;
							fndLocation = i;
							return _child(i); 
						}
					}
				}
			}			
			
			return null;		//  이것을 toString .. 또는 Value 에서 사용하기 위해서.
		}
		
		public xmlElement _next(){
			if(parent != null ){
				boolean bFind = false;
				for( xmlElement element : parent.childs){
					if( bFind )return element;
					if( element == this)bFind= true;
				}
			}
			return null;
		}
		
		
		public xmlElement _prev(){
			
			if(parent != null ){
				xmlElement before = null;
				for( xmlElement element : parent.childs){
					if( element == this)return before;
					before = element;
				}
			}
			return null;
		}
		
		
	}
	
	xmlElement rootEl = null;
	private xmlElement currEl = null;
	
	/** Called when tag starts ( ex:- <name>AndroidPeople</name> -- <name> )*/
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		EL_depth++;
		xmlElement xmlEl = new xmlElement();
		xmlEl.nodeName = qName;
		if(EL_depth == 0){
			rootEl = xmlEl; 
		}else{
			xmlEl.parent = currEl;
		}		
		currEl = xmlEl;
		currentElement = true;
	}

	/** Called when tag closing ( ex:- <name>AndroidPeople</name> -- </name> )*/
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		if(currEl.parent != null){
			if(currEl.parent.childs == null) currEl.parent.childs = new LinkedList<xmlElement>();
			//currEl.location = currEl.parent.childs.size();
			//currEl.parent.childs.add(currEl.location, currEl);
			currEl.parent.childs.add(currEl);
		}
		EL_depth--;		
		if(EL_depth > -1) currEl = currEl.parent;				
		else currEl = null;
		currentElement = false;		
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(length > 0) currEl.nodeValue += new String(ch, start, length);
		
	}
}
