/*
 * Copyright (c) ...
 */
package controls;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * The WebViewControl class provides methods to prepare URL for browser.
 * 
 * @version		0.1 14. May 2020
 * @author 		Andrej Marcan
 */
public class WebViewControl {
	/* base part of URL */
    private static final String URL_BASE = "https://www.sdrftyuiolmcardmarket.com/en/YuGiOh/Products/Singles/";
    
    /* Method prepareCardName prepares part of URL from provided string. */
    private static String prepareCardName(String name) {
        name=name.replace(' ', '-');
        return name;
    }
    
    /* Method prepareEditionName prepares part of URL from provided string. */
    private static String prepareEditionName(String edition) {
        String editionName = FileControl.getFullEditionName(edition);
        editionName=editionName.replace(' ', '-');
        return editionName;
    }
    
    /* Method prepareURL combines URL parts provided by prepareCardName and prepareEditionName methods. */
    private static String prepareURL (String cardName, String edition) {
        return prepareEditionName(edition) + '/' + prepareCardName(cardName);
    }
    
    /* Method openWebPage open browser at web page with wanted URL. */
    public static void openWebPage(String cardName, String edition) throws URISyntaxException, IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(new URI(URL_BASE + prepareURL(cardName, edition)));
    }
}










