package org.oobootcamp.warmup.length;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LengthTest {
    @Test
    void should_return_a_number() {
        Length length = new Length(100, Unit.CM);
        Integer value = length.getValue();

        assertTrue(value instanceof Integer);
    }

    @Test
    void should_return_true_when_two_length_value_and_unit_is_equal() throws Exception {
        Length source = new Length(10, Unit.CM);
        Length target = new Length(10, Unit.CM);

        assertTrue(source.isEqual(target));
    }

    @Test
    void should_return_false_when_two_length_value_is_not_equal() throws Exception {
        Length source = new Length(10, Unit.CM);
        Length target = new Length(100, Unit.CM);

        assertFalse(source.isEqual(target));
    }

    @Test
    void should_throw_exception_when_two_length_unit_is_not_equal() throws Exception {
        Length source = new Length(10, Unit.M);
        Length target = new Length(10, Unit.CM);

        assertFalse(source.isEqual(target));
    }

    @Test
    void should_return_true_when_source_length_is_larger_than_target() throws Exception {
        Length source = new Length(100, Unit.CM);
        Length target = new Length(10, Unit.CM);

        assertTrue(source.isLarger(target));
    }

    @Test
    void should_throw_exception_when_source_length_unit_is_different_with_target() throws Exception {
        Length source = new Length(10, Unit.DM);
        Length target = new Length(100, Unit.CM);

        assertFalse(source.isLarger(target));
    }
}
