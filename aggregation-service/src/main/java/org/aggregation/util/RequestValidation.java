package org.aggregation.util;

import java.util.Locale;
import java.util.Set;

public class RequestValidation {
	
    private static final Set<String> ISO_COUNTRIES = Set.of(Locale.getISOCountries());

    private RequestValidation() {}

    public static boolean isValidISOCountry(String countryCode) {
        return ISO_COUNTRIES.contains(countryCode);
    }

    public static boolean isNumericWithRequiredDigit(String orderNumber){
        if(orderNumber.length()!=9)
            return false;
        try{
            int i = Integer.parseInt(orderNumber);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}
