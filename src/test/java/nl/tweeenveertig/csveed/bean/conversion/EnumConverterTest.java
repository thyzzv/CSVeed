package nl.tweeenveertig.csveed.bean.conversion;

import nl.tweeenveertig.csveed.token.ParseState;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class EnumConverterTest {

    @Test
    public void convertEnum() throws Exception {
        EnumConverter<ParseState> converter = new EnumConverter<ParseState>(ParseState.class);
        assertEquals(ParseState.SKIP_LINE, converter.fromString("SKIP_LINE"));
    }
}
