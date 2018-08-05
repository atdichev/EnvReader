package reader.parsers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import reader.EnvException;

class TextParser implements PropertyParser {

    @Override
    public String get(String fileName) {
    	URL url = getClass().getResource(fileName);
        if (url == null) {
            throw new EnvException(new FileNotFoundException());
        }    	
        String content = "";
        try {
        	content = new String ( Files.readAllBytes( Paths.get(url.toURI()) ) );
		} catch (IOException e) {
			throw new EnvException(e);
		} catch (URISyntaxException e) {
			throw new EnvException(e);
		}
        
        return content;
    }

    @Override
    public void reLoad() {
        //
    }

}
