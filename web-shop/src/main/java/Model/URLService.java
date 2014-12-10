package Model;

public class URLService {
	public static String getUrlPart(String url, String from, String to){
		String[] parts = url.split("/");
		StringBuilder result = new StringBuilder();
		for(String part:parts){
			if(!part.equals(from) && !part.equals(to))
				result.append(part.concat("/"));
			if(part.equals(to))break;
		}
		String caturl = result.toString();
		if(caturl.startsWith("/"))caturl = caturl.substring(1, caturl.length());
		if(caturl.endsWith("/"))caturl = caturl.substring(0, caturl.length()-1);
		return caturl;
	}
	
	public static String getUrlAfter(String url, String from){
		String[] parts = url.split("/");
		StringBuilder result = new StringBuilder();
		for(String part:parts){
			if(!part.equals(from))
				result.append(part.concat("/"));
			
		}
		String caturl = result.toString();
		if(caturl.startsWith("/"))caturl = caturl.substring(1, caturl.length());
		if(caturl.endsWith("/"))caturl = caturl.substring(0, caturl.length()-1);
		return caturl;
	}
}
