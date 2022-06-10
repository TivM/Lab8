package common.utility;

import common.data.SpaceMarine;
import resources.LocaleBundle;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.MissingResourceException;
import java.util.TreeMap;

public class Response implements Serializable {
    private ResponseCode responseCode;
    private String responseBody;
    private TreeMap<Integer, SpaceMarine> collection;

    public Response(ResponseCode responseCode, String responseBody, TreeMap<Integer, SpaceMarine> collection) {
        this.responseCode = responseCode;
        this.responseBody = responseBody;
        this.collection = collection;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public TreeMap<Integer, SpaceMarine> getCollection() {
        return collection;
    }

    public String localize(boolean isDate) {
        StringBuilder localizeStringBody = new StringBuilder();
        String[] lines = responseBody.split("\n");
        for (String line : lines) {
            if (line.isEmpty()) continue;
            String[] splitLine = line.split(":");
            try {
                localizeStringBody.append(LocaleBundle.getCurrentBundle().getString(splitLine[0]));
            } catch (MissingResourceException e) {
                localizeStringBody.append(splitLine[0]);
            }
            if (splitLine.length == 1) {
                localizeStringBody.append("\n");
                continue;
            }
            try {
                localizeStringBody.append(LocaleBundle.getCurrentBundle().getString(splitLine[1]));
            } catch (MissingResourceException e) {
                localizeStringBody.append(splitLine[1]);
            }
            localizeStringBody.append("\n");
        }
        return localizeStringBody.toString();
    }
}
