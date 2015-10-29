package com.acme.edu;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.fest.assertions.Assertions.*;

public interface SysoutCaptureAndAssertionAbility {
    public static final String SEP = System.lineSeparator();

    ByteArrayOutputStream OUT = new ByteArrayOutputStream();

    default void captureSysout() {
        System.setOut(new PrintStream(OUT));
    }

    default void assertSysoutEquals(String expected) {
        assertThat(OUT.toString()).isEqualTo(expected);
    }

    default void assertSysoutContains(String expected) {
        assertThat(OUT.toString()).contains(expected);
    }
}
