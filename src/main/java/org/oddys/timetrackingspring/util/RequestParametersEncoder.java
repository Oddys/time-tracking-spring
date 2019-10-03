package org.oddys.timetrackingspring.util;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class RequestParametersEncoder {
    private final String ENCODING;

    public RequestParametersEncoder(String encoding) {
        ENCODING = encoding;
    }

    public String encodeQueryParameters(String url, Map<String, String> parameters) {
        return parameters.keySet().stream()
                .map(key -> key + "=" + encodeValue(parameters.get(key)))
                .collect(Collectors.joining("&", url + "?", ""));
    }

    private String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, ENCODING);
        } catch (UnsupportedEncodingException e) {
            log.error("Failed to encode a query parameter with a given encoding");
            throw  new EncodingException(e);
        }
    }
}
