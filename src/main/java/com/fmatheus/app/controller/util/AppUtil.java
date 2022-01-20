package com.fmatheus.app.controller.util;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.format.CNPJFormatter;
import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUtil {

    private AppUtil() {
        throw new IllegalStateException("Utility class");
    }


    public static String passwordEncoder(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }


    public static String convertFirstUppercaseCharacter(String value) {
        return Objects.nonNull(value) ? removeDuplicateSpace(CapitalizeUtil.capitalizeFully(value).trim()) : null;
    }

    public static String convertAllUppercaseCharacters(String value) {
        return Objects.nonNull(value) ? removeDuplicateSpace(value.toUpperCase().trim()) : null;
    }


    public static String convertAllLowercaseCharacters(String value) {
        return Objects.nonNull(value) ? removeDuplicateSpace(value.toLowerCase().trim()) : null;
    }

    public static String removeAllSpaces(String string) {
        return string.replace(" ", "");
    }


    public static String removeDuplicateSpace(String value) {
        return Objects.nonNull(value) ? value.replaceAll("\\s+", " ").trim() : null;
    }


    public static String removeSpecialCharacters(@NotNull @NotBlank String value) {
        return value.replaceAll("[^a-zA-Z0-9]", "");
    }

    public static String removeAccent(String value) {
        if (Objects.nonNull(value)) {
            String normalize = Normalizer.normalize(value, Normalizer.Form.NFD);
            normalize = normalize.replaceAll("[^\\p{ASCII}]", "");
            return normalize;
        }
        return null;
    }

    public static String removeExtension(String value) {
        return value.substring(0, value.lastIndexOf('.'));
    }

    public static String formatCNPJ(String value) {
        return Objects.nonNull(value) ? new CNPJFormatter().format(value) : null;
    }

    public static String formatCPF(String value) {
        return Objects.nonNull(value) ? new CPFFormatter().format(value) : null;
    }

    public static boolean validateCNPJ(String value) {
        var validator = new CNPJValidator();
        List<ValidationMessage> erros = validator.invalidMessagesFor(value);
        return erros.isEmpty();
    }

    public static boolean validateCPF(String value) {
        var validator = new CPFValidator();
        List<ValidationMessage> erros = validator.invalidMessagesFor(value);
        return erros.isEmpty();
    }

    public static int countCharacter(String value) {
        return Objects.nonNull(value) ? value.length() : 0;
    }

    public static String formatMask(String valor, String mascara) {

        StringBuilder dado = new StringBuilder();
        for (var i = 0; i < valor.length(); i++) {
            var c = valor.charAt(i);
            if (Character.isDigit(c)) {
                dado.append(c);
            }
        }
        int indMascara = mascara.length();
        int indCampo = dado.length();

        while (indCampo > 0 && indMascara > 0) {
            if (mascara.charAt(--indMascara) == '#') {
                indCampo--;
            }
        }

        StringBuilder saida = new StringBuilder();
        for (; indMascara < mascara.length(); indMascara++) {
            saida.append((mascara.charAt(indMascara) == '#') ? dado.charAt(indCampo++) : mascara.charAt(indMascara));
        }
        return saida.toString();
    }

    public static String returnFirstWord(String texto) {
        String pattern = "^([a-zA-ZÈ-Úè-ú]+)\\s";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(texto);
        if (m.find()) {
            return m.group(0);
        }
        return null;
    }

    public static String returnCharacter(String value, int position) {
        if (value.length() > position) {
            return value.substring(0, position);
        }
        return value;
    }

    /*
     * Retorna pate de uma string de acordo com o delimitador. Exemplo: API_REST.
     * Resultado: API
     */
    public static String returnDelimiterString(@NotNull @NotBlank String value, @NotNull @NotBlank String delimiter) {
        return value.contains(" ") ? value.substring(0, value.indexOf(delimiter)) : null;
    }

    /**
     * Remove parte de uma string. Exemplo: file.png > file
     */
    public static String removePartText(String value, String valueRemove) {
        return value.replace(valueRemove, "");
    }


    public static String formatMoney(BigDecimal value) {
        DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setCurrencySymbol("R$");
        decimalFormatSymbols.setMonetaryDecimalSeparator(',');
        decimalFormatSymbols.setGroupingSeparator('.');
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        return decimalFormat.format(value);
    }

}
