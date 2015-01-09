package org.cytoscape.slimscape.internal;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rayne on 9/01/15.
 */
public class SlimfinderPrepareResults extends JPanel {
    // Get line by line.
    // Break into sections based on ###---### lines
    // I want after 3 and after 4

    // Gets the page URL and convert it to an array of strings
    public SlimfinderPrepareResults(String url) throws Exception {

        List<String> csv = new ArrayList<String>();
        List<String> occ = new ArrayList<String>();

        // Gets site data
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));



        String inputLine;

        int separators = 0;

        while ((inputLine = in.readLine()) != null) {
            if (inputLine.equals("###~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~###")) {
                separators++;
            }
            if (separators == 3) {  // Add to the csv component
                csv.add(inputLine);
            }
            if (separators == 4) { // Add to occ
                occ.add(inputLine);
            }
            if (separators > 4) {
                break;
            }
        }
        in.close();

        // YAY THIS WORKS!
        // Now I just need to make them into panels.

    }


}
