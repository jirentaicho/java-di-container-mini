package vol;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Finder {
	
	public static List<String> getClassList(String packageName) throws IOException{
	    final char PACKAGE_SEP = '.';
	    final char PACKAGE_RESOURCE_SEP = '/';
	    
	    String packageResource 
	        = packageName.replace(PACKAGE_SEP, PACKAGE_RESOURCE_SEP);
	    ClassLoader loader = ClassLoader.getSystemClassLoader();
	    URL resource = loader.getResource(packageResource);
	    File files = new File(resource.getFile());
	    
	    List<String> classNames = new ArrayList<>();
	    for(String file : files.list()) {
	        if (file.matches(".*\\.class$")) {
	        	String className = packageName + PACKAGE_SEP + file.replaceAll("\\.class$","");
	            classNames.add(className);
	        } else {
	        	// パッケージの場合再帰処理する
	        	String subPathName = packageName + "." + file.toString();
	        	classNames.addAll(getClassList(subPathName));
	        }
	    }
	    return classNames;
	}
}
