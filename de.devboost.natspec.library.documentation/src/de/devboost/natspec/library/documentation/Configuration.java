package de.devboost.natspec.library.documentation;

/**
 * Generator configuration
 * 
 * @author skrause
 */
public class Configuration {

	private boolean tableOfFigures = true;
	private boolean copyImages = true;

	/**
	 * determines, if the table of figures shall be created
	 */
	public boolean isTableOfFigures() {
		return tableOfFigures;
	}

	/**
	 * defines, if the table of figures shall be created
	 */
	public Configuration setTableOfFigures(boolean tableOfFigures) {
		this.tableOfFigures = tableOfFigures;
		return this;
	}

	/**
	 * determines, if the images shall be copied into the target folder
	 */
	public boolean isCopyImages() {
		return copyImages;
	}

	/**
	 * defines, if the images shall be copied into the target folder
	 */
	public Configuration setCopyImages(boolean copyImages) {
		this.copyImages = copyImages;
		return this;
	}

}
