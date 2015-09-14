package ca.mcgill.mcb.pcingola.snpEffect.testCases.integration;

import org.junit.Test;

import ca.mcgill.mcb.pcingola.util.Gpr;

/**
 * Test cases for HGVS notation on insertions
 */
public class TestCasesIntegrationHgvsIns extends TestCasesIntegrationBase {

	//	boolean debug = false;
	//	boolean verbose = false || debug;

	public TestCasesIntegrationHgvsIns() {
		super();
	}

	//	/**
	//	 * Check HGVS annotations
	//	 */
	//	public void checkHgvs(String genome, String vcfFile, int minCheck) {
	//		List<VcfEntry> list = snpEffect(genome, vcfFile, null);
	//
	//		int countCheck = 0;
	//		for (VcfEntry ve : list) {
	//			if (verbose) System.out.println(ve);
	//
	//			String transcriptId = ve.getInfo("TR");
	//			if (verbose) System.out.println("\tLooking for transcript '" + transcriptId + "'");
	//			for (VcfEffect veff : ve.getVcfEffects()) {
	//
	//				if (veff.getTranscriptId().equals(transcriptId)) {
	//					if (verbose) {
	//						System.out.println("\t" + veff);
	//						System.out.println("\t\tHGVS.p: " + veff.getHgvsP() + "\t\tHGVS.c: " + veff.getHgvsC());
	//					}
	//
	//					// Compare against expected result
	//					String expectedHgvsC = ve.getInfo("HGVSC");
	//					if (expectedHgvsC != null) {
	//						String actualHgvsC = veff.getHgvsC();
	//						Assert.assertEquals("HGVS.c mismatch", expectedHgvsC, actualHgvsC);
	//						countCheck++;
	//					}
	//
	//					String expectedHgvsP = ve.getInfo("HGVSP");
	//					if (expectedHgvsP != null) {
	//						String actualHgvsP = veff.getHgvsP();
	//						Assert.assertEquals("HGVS.p mismatch", expectedHgvsP, actualHgvsP);
	//						countCheck++;
	//					}
	//				}
	//			}
	//		}
	//
	//		if (verbose) System.out.println("Total checked: " + countCheck);
	//		Assert.assertTrue("Too few variants checked: " + countCheck, countCheck >= minCheck);
	//	}
	//
	//	/**
	//	 * Calculate snp effect for an input VCF file
	//	 */
	//	public List<VcfEntry> snpEffect(String genome, String vcfFile, String otherArgs[]) {
	//		// Arguments
	//		ArrayList<String> args = new ArrayList<String>();
	//		if (otherArgs != null) {
	//			for (String a : otherArgs)
	//				args.add(a);
	//		}
	//		args.add(genome);
	//		args.add(vcfFile);
	//
	//		SnpEff cmd = new SnpEff(args.toArray(new String[0]));
	//		SnpEffCmdEff cmdEff = (SnpEffCmdEff) cmd.snpEffCmd();
	//		cmdEff.setVerbose(verbose);
	//		cmdEff.setSupressOutput(!verbose);
	//		cmdEff.setFormatVersion(EffFormatVersion.FORMAT_EFF_4);
	//
	//		// Run command
	//		List<VcfEntry> list = cmdEff.run(true);
	//		Assert.assertTrue("Errors while executing SnpEff", cmdEff.getTotalErrs() <= 0);
	//
	//		// Check that there were no errors
	//		Assert.assertFalse("Annotation finished with errors", cmdEff.getTotalErrs() > 0);
	//
	//		return list;
	//	}

	/**
	 * Insertion / duplication issues
	 */
	@Test
	public void test_02_hgvs_insertions_chr1() {
		Gpr.debug("Test");
		checkHgvs("testHg19Chr1", "tests/hgvs_ins_dups_chr1.vcf", 4);
	}

	/**
	 * Insertion / duplication issues
	 */
	@Test
	public void test_03_hgvs_insertions_chr3() {
		Gpr.debug("Test");
		checkHgvs("testHg19Chr3", "tests/hgvs_ins_dups_chr3.vcf", 2);
	}

	/**
	 * Insertion / duplication issues
	 */
	@Test
	public void test_04_hgvs_insertions_chr4() {
		Gpr.debug("Test");
		checkHgvs("testHg19Chr4", "tests/hgvs_ins_dups_chr4.vcf", 2);
	}

	/**
	 * Insertion / duplication issues
	 */
	@Test
	public void test_05_hgvs_insertions_chr19() {
		Gpr.debug("Test");
		checkHgvs("testHg19Chr19", "tests/hgvs_ins_dups_chr19.vcf", 2);
	}

	/**
	 * Insertion / duplication issues
	 */
	@Test
	public void test_06_hgvs_insertions_chr7() {
		Gpr.debug("Test");
		checkHgvs("testHg19Chr7", "tests/hgvs_ins_chr7.vcf", 2);
	}

	/**
	 * This frameshift caused an exception while processing HGVS protein notation
	 */
	@Test
	public void test_07_hgvs_insertions1() {
		Gpr.debug("Test");

		String genomeName = "testHg3775Chr1";
		String vcf = "tests/hgvs_ins_07.vcf";

		snpEffect(genomeName, vcf, null);
	}

}
