package com.aquilifer.reporting.pdf.renderer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.aquilifer.reporting.pdf.renderer.element.SVGReplacedElement;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xhtmlrenderer.extend.ReplacedElement;
import org.xhtmlrenderer.extend.ReplacedElementFactory;
import org.xhtmlrenderer.extend.UserAgentCallback;
import org.xhtmlrenderer.layout.LayoutContext;
import org.xhtmlrenderer.render.BlockBox;
import org.xhtmlrenderer.resource.XMLResource;
import org.xhtmlrenderer.simple.extend.FormSubmissionListener;

import java.io.FileInputStream;
import java.io.InputStream;

public class SVGReplacedElementFactory implements ReplacedElementFactory {

    private static final String MEDIA_BASE_PATH = "com/aquilifer/reporting/media/";

    @Override
    public ReplacedElement createReplacedElement(LayoutContext c, BlockBox box,
                                                 UserAgentCallback uac, int cssWidth, int cssHeight) {
        Element element = box.getElement();
        String nodeName = element.getNodeName();
        String className = element.getAttribute("class");
        String mediaType = element.getAttribute("data-type");
        // Replace any <div class="media" data-src="image.png" /> with the
        // binary data of `image.png` into the PDF.
        if ("div".equals(nodeName) && "media".equals(className) && "svg".equals(mediaType)) {

            if (!element.hasAttribute("data-src")) {
                throw new RuntimeException("An element with class `media` is missing a `data-src` attribute indicating the media file.");
            }
            InputStream input = null;
            try {
                input = this.getClass().getClassLoader().getResourceAsStream(MEDIA_BASE_PATH + element.getAttribute("data-src"));
                Document svgDocument = XMLResource.load(input).getDocument();
                return new SVGReplacedElement(svgDocument, cssWidth, cssHeight);
            } catch (Exception e) {
                throw new RuntimeException("There was a problem trying to read a template embedded graphic.", e);
            } finally {
                IOUtils.closeQuietly(input);
            }

        }
        return null;
    }

    @Override
    public void reset() {
    }

    @Override
    public void remove(Element e) {
    }

    @Override
    public void setFormSubmissionListener(FormSubmissionListener listener) {
    }
}