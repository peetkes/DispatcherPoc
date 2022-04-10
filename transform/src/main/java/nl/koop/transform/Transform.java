package nl.koop.transform;

import net.sf.saxon.s9api.SaxonApiException;

public interface Transform {
  /**
   * The name of the test
   * @return the name of the test
   */
  String name();

  /**
   * Ask if the test needs Saxon-EE (in some cases Saxon-PE is adequate)
   * @return true if the test will not run with Saxon-HE
   */
  boolean needsSaxonEE();

  /**
   * Run the test
   * @throws SaxonApiException if the test fails
   */
  void run() throws SaxonApiException;
}
