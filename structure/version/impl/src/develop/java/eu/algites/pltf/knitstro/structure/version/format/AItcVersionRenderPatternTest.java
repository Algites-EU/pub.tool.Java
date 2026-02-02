package eu.algites.pltf.knitstro.structure.version.format;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

/**
 * Tests for {@link AIcVersionRenderPattern}.
 */
public class AItcVersionRenderPatternTest {

    @Test
    public void testReferencedComponentCodesExtractsUniqueCodesInOrder() {
        AIcVersionRenderPattern locPattern = new AIcVersionRenderPattern("${year}.${month}_${lane}-${rev}+${build}");

        Set<String> locCodes = locPattern.referencedComponentCodes();

        Assert.assertEquals(locCodes.size(), 5);
        Assert.assertTrue(locCodes.contains("year"));
        Assert.assertTrue(locCodes.contains("month"));
        Assert.assertTrue(locCodes.contains("lane"));
        Assert.assertTrue(locCodes.contains("rev"));
        Assert.assertTrue(locCodes.contains("build"));
    }

    @Test
    public void testReferencedComponentCodesEmptyWhenNoPlaceholders() {
        AIcVersionRenderPattern locPattern = new AIcVersionRenderPattern("v1.2.3");

        Assert.assertTrue(locPattern.referencedComponentCodes().isEmpty());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testConstructorRejectsBlankSource() {
        new AIcVersionRenderPattern("   ");
    }
}
