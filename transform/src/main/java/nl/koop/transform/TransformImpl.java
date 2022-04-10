package nl.koop.transform;

import net.sf.saxon.s9api.*;

public class TransformImpl implements Transform {
  @Override
  public String name() {
    return "PlainTransform";
  }

  @Override
  public boolean needsSaxonEE() {
    return false;
  }

  @Override
  public void run() throws SaxonApiException {
    Processor proc = new Processor(false);
    XQueryCompiler comp = proc.newXQueryCompiler();
    XQueryExecutable exp = comp.compile("<a b='c'>{5+2}</a>");
    Serializer out = proc.newSerializer(System.out);
    out.setOutputProperty(Serializer.Property.METHOD, "xml");
    out.setOutputProperty(Serializer.Property.INDENT, "yes");
    out.setOutputProperty(Serializer.Property.OMIT_XML_DECLARATION, "yes");
    exp.load().run(out);
  }
}
