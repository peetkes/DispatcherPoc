package nl.koop.transform;

import net.sf.saxon.s9api.*;

import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class TransformXsltImpl implements Transform {
    @Override
    public String name() {
        return "XsltTransform";
    }

    @Override
    public boolean needsSaxonEE() {
        return false;
    }

    @Override
    public void run() throws SaxonApiException {
        Processor processor = new Processor(false);
        XsltCompiler compiler = processor.newXsltCompiler();
        XsltExecutable stylesheet = compiler.compile(new StreamSource(new File("styles/service-gml-km.xsl")));
        Serializer out = processor.newSerializer(System.out);
        out.setOutputProperty(Serializer.Property.METHOD, "xml");
        out.setOutputProperty(Serializer.Property.INDENT, "yes");
        Xslt30Transformer transformer = stylesheet.load30();
        transformer.transform(new StreamSource(new File("data/out.xml")), out);
    }
}
