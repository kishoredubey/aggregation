package org.aggregation.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RequestValidationTest {

    @Test
    public void test_isNumericWithRequiredDigit_success() {
        boolean numericWithRequiredDigit = RequestValidation.isNumericWithRequiredDigit("123456789");
        assertThat(numericWithRequiredDigit).isTrue();
    }

    @Test
    public void test_isNumericWithRequiredDigit_fail() {
        boolean numericWithRequiredDigit = RequestValidation.isNumericWithRequiredDigit("abc");
        assertThat(numericWithRequiredDigit).isFalse();
    }

    @Test
    public void test_isValidISOCountry_success() {
        boolean isValidCountryCode = RequestValidation.isValidISOCountry("NL");
        assertThat(isValidCountryCode).isTrue();
    }

    @Test
    public void test_isValidISOCountry_fail() {
        boolean isValidCountryCode = RequestValidation.isValidISOCountry("USA");
        assertThat(isValidCountryCode).isFalse();
    }

}
