package eu.algites.pltf.knitstro.structure.version.component;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests for {@link AIcVersionComponent}.
 */
public class AItcVersionComponentTest {

    @Test
    public void testConstructorAndGetters() {
        AIcVersionComponent locComponent = new AIcVersionComponent(
                "major",
                AInVersionComponentValueType.NON_NEGATIVE_INTEGER,
                AInVersionComponentPrecedenceRole.PRECEDENCE
        );

        Assert.assertEquals(locComponent.code(), "major");
        Assert.assertEquals(locComponent.valueType(), AInVersionComponentValueType.NON_NEGATIVE_INTEGER);
        Assert.assertEquals(locComponent.precedenceRole(), AInVersionComponentPrecedenceRole.PRECEDENCE);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testConstructorRejectsBlankCode() {
        new AIcVersionComponent(
                "  ",
                AInVersionComponentValueType.TEXT,
                AInVersionComponentPrecedenceRole.METADATA
        );
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testConstructorRejectsIllegalCodeCharacters() {
        new AIcVersionComponent(
                "bad-code!",
                AInVersionComponentValueType.TEXT,
                AInVersionComponentPrecedenceRole.METADATA
        );
    }
}
