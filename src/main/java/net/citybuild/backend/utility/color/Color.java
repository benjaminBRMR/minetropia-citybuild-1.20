package net.citybuild.backend.utility.color;

import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class Color {


    public final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";

    public String gradient(String input, String[] color) {
        List<String> rgbList = new ArrayList<>(Arrays.asList(color));

        StringBuilder output = new StringBuilder();
        String[] lines = input.split("\n");

        for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
            String line = lines[lineIndex];
            char[] characters = line.toCharArray();

            for (int charIndex = 0; charIndex < characters.length; charIndex++) {
                char character = characters[charIndex];

                int colorIndex = (int) Math.floor((double) charIndex / characters.length * (rgbList.size() - 1));
                String format = "" + ChatColor.of(rgbList.get(colorIndex));

                output.append(format).append(character);
            }
            if (lineIndex < lines.length - 1) {
                output.append("\n");
            }
        }

        return output.toString();
    }

    private int[] hexToRGB(String hex) {
        hex = hex.replace("#", "");
        int r = Integer.parseInt(hex.substring(0, 2), 16);
        int g = Integer.parseInt(hex.substring(2, 4), 16);
        int b = Integer.parseInt(hex.substring(4, 6), 16);
        return new int[]{r, g, b};
    }

    private String RGBToHex(int[] rgb) {
        return String.format("#%02X%02X%02X", rgb[0], rgb[1], rgb[2]);
    }

    private String formatCharacter(char character, String format) {
        return format + character;
    }

    /**
     * @param text The string of text to apply color/effects to
     * @return Returns a string of text with color/effects applied
     */
    public String translateColorCodes(String text) {

        String[] texts = text.split(String.format(WITH_DELIMITER, "&"));

        StringBuilder finalText = new StringBuilder();

        for (int i = 0; i < texts.length; i++) {
            if (texts[i].equalsIgnoreCase("&")) {
                i++;
                if (texts[i].charAt(0) == '#') {
                    finalText.append(ChatColor.of(texts[i].substring(0, 7))).append(texts[i].substring(7));
                } else {
                    finalText.append(ChatColor.translateAlternateColorCodes('&', "&" + texts[i]));
                }
            } else {
                finalText.append(texts[i]);
            }
        }

        return finalText.toString();
    }

}
