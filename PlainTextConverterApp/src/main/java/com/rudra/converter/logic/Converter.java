package com.rudra.converter.logic;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Converter {

    public static String toJson(String input) {
        Map<String, String> map = parseInput(input);
        return new JSONObject(map).toString(2);
    }

    public static String toXml(String input) {
        Map<String, String> map = parseInput(input);
        StringBuilder sb = new StringBuilder();
        sb.append("<root>\n");
        map.forEach((k, v) -> {
            sb.append("  <").append(escapeXml(k)).append(">")
              .append(escapeXml(v)).append("</").append(escapeXml(k)).append(">\n");
        });
        sb.append("</root>");
        return sb.toString();
    }

    public static String toCsv(String input) {
        Map<String, String> map = parseInput(input);
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String k : map.keySet()) {
            if (!first) sb.append(",");
            sb.append(escapeCsv(k));
            first = false;
        }
        sb.append("\n");
        first = true;
        for (String v : map.values()) {
            if (!first) sb.append(",");
            sb.append(escapeCsv(v));
            first = false;
        }
        return sb.toString();
    }

    public static String toJsonl(String input) {
        Map<String, String> map = parseInput(input);
        return new JSONObject(map).toString();
    }

    /**
     * Parse plain text input into ordered key/value pairs.
     * Accepts separators: comma, newline, semicolon.
     * Accepts key/value separators: ':' or '='.
     * If a line has no key/value, it will be added as "valueN".
     */
    private static Map<String, String> parseInput(String input) {
        Map<String, String> map = new LinkedHashMap<>();
        if (input == null || input.isBlank()) return map;

        // Normalize separators to newline for tokenization
        String normalized = input.replace(";", "\n").replace(",", "\n");
        StringTokenizer st = new StringTokenizer(normalized, "\n");
        int anon = 1;
        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            if (token.isEmpty()) continue;
            String key = null;
            String value = null;
            int idx = token.indexOf(':');
            if (idx < 0) idx = token.indexOf('=');
            if (idx >= 0) {
                key = token.substring(0, idx).trim();
                value = token.substring(idx + 1).trim();
            } else {
                // Try splitting by whitespace into two parts
                String[] parts = token.split("\\s+", 2);
                if (parts.length == 2) {
                    key = parts[0].trim();
                    value = parts[1].trim();
                } else {
                    key = "value" + anon++;
                    value = token;
                }
            }
            if (key.isEmpty()) key = "value" + anon++;
            if (value == null) value = "";
            map.put(sanitizeKey(key), value);
        }
        return map;
    }

    private static String sanitizeKey(String key) {
        return key.replaceAll("[^A-Za-z0-9_\\-]", "_");
    }

    private static String escapeXml(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&apos;");
    }

    private static String escapeCsv(String s) {
        if (s == null) return "";
        String out = s.replace("\"", "\"\"");
        if (out.contains(",") || out.contains("\"") || out.contains("\n")) {
            return "\"" + out + "\"";
        }
        return out;
    }
}

