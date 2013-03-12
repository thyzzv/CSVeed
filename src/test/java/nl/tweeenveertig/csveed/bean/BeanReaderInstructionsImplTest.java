package nl.tweeenveertig.csveed.bean;

import nl.tweeenveertig.csveed.test.model.BeanSimple;
import org.junit.Test;

import static junit.framework.Assert.assertNull;

public class BeanReaderInstructionsImplTest {

    @Test
    public void propertyNameIsNull() {
        BeanReaderInstructionsImpl instructions = new BeanReaderInstructionsImpl(BeanSimple.class);
        assertNull(instructions.getProperties().fromName("definitelyNotHere"));
    }

}