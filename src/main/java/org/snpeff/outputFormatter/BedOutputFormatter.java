package org.snpeff.outputFormatter;

import java.util.HashSet;

import org.snpeff.interval.Marker;
import org.snpeff.interval.Variant;
import org.snpeff.snpEffect.VariantEffect;

/**
 * Formats output as BED file
 * 
 * Referneces: http://genome.ucsc.edu/FAQ/FAQformat.html#format1
 * 
 * @author pcingola
 */
public class BedOutputFormatter extends OutputFormatter {

	public BedOutputFormatter() {
		super();
		outOffset = 0; // Bed format is zero-based
	}

	/**
	 * Finish up section
	 * @param marker
	 */
	@Override
	public String endSection(Marker marker) {
		// Ignore other markers (e.g. seqChanges)
		if (marker instanceof Variant) return super.endSection(marker);
		return null;
	}

	@Override
	public void setOutOffset(int outOffset) {
		throw new RuntimeException("Cannot set output offset on '" + this.getClass().getSimpleName() + "' formatter!");
	}

	@Override
	public void startSection(Marker marker) {
		// Ignore other markers (e.g. seqChanges)
		if (marker instanceof Variant) super.startSection(marker);
	}

	/**
	 * Show all effects
	 */
	@Override
	public String toString() {
		Variant seqChange = (Variant) section;

		// Show results
		HashSet<String> chEffs = new HashSet<String>();
		for (VariantEffect varEff : variantEffects) {
			// If it is not filtered out by changeEffectResutFilter  => Show it
			if ((variantEffectResutFilter == null) || (!variantEffectResutFilter.filter(varEff))) {

				StringBuffer sb = new StringBuffer();
				sb.append(varEff.effect(true, false, false, useSequenceOntology, false));

				Marker m = varEff.getMarker();
				if (m != null) chEffs.add(m.idChain("|", ":", useGeneId, varEff));
			}

		}

		StringBuilder changeEffSb = new StringBuilder();
		changeEffSb.append(seqChange.getId()); // Start with the old 'name' field
		for (String chEff : chEffs) {
			changeEffSb.append(";");
			changeEffSb.append(chEff);
		}

		return chrStr + seqChange.getChromosomeName() //
				+ "\t" + (seqChange.getStart() + outOffset) //
				+ "\t" + (seqChange.getEnd() + 1) // End base is not included in BED format
				+ "\t" + changeEffSb.toString() //
		// + "\t" + (!Double.isNaN(seqChange.getScore()) ? seqChange.getScore() : "") //
		;
	}

	/**
	 * Show header
	 */
	@Override
	public String toStringHeader() {
		return "# SnpEff version " + version + "\n" //
				+ "# Command line: " + commandLineStr + "\n" //
				+ "# Chromo\tStart\tEnd\tName;Effect|Gene|BioType\tScore"; //
	}
}
