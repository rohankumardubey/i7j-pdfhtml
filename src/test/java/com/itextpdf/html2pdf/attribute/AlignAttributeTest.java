/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2022 iText Group NV
    Authors: iText Software.

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License version 3
    as published by the Free Software Foundation with the addition of the
    following permission added to Section 15 as permitted in Section 7(a):
    FOR ANY PART OF THE COVERED WORK IN WHICH THE COPYRIGHT IS OWNED BY
    ITEXT GROUP. ITEXT GROUP DISCLAIMS THE WARRANTY OF NON INFRINGEMENT
    OF THIRD PARTY RIGHTS

    This program is distributed in the hope that it will be useful, but
    WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
    or FITNESS FOR A PARTICULAR PURPOSE.
    See the GNU Affero General Public License for more details.
    You should have received a copy of the GNU Affero General Public License
    along with this program; if not, see http://www.gnu.org/licenses or write to
    the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
    Boston, MA, 02110-1301 USA, or download the license from the following URL:
    http://itextpdf.com/terms-of-use/

    The interactive user interfaces in modified source and object code versions
    of this program must display Appropriate Legal Notices, as required under
    Section 5 of the GNU Affero General Public License.

    In accordance with Section 7(b) of the GNU Affero General Public License,
    a covered work must retain the producer line in every PDF that is created
    or manipulated using iText.

    You can be released from the requirements of the license by purchasing
    a commercial license. Buying such a license is mandatory as soon as you
    develop commercial activities involving the iText software without
    disclosing the source code of your own applications.
    These activities include: offering paid services to customers as an ASP,
    serving PDFs on the fly in a web application, shipping iText with a closed
    source product.

    For more information, please contact iText Software Corp. at this
    address: sales@itextpdf.com
 */
package com.itextpdf.html2pdf.attribute;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.ExtendedHtmlConversionITextTest;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.kernel.utils.CompareTool;
import com.itextpdf.test.annotations.type.IntegrationTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(IntegrationTest.class)
public class AlignAttributeTest extends ExtendedHtmlConversionITextTest {

    public static final String sourceFolder = "./src/test/resources/com/itextpdf/html2pdf/attribute/AlignAttributeTest/";
    public static final String destinationFolder = "./target/test/com/itextpdf/html2pdf/attribute/AlignAttributeTest/";

    @BeforeClass
    public static void beforeClass() {
        createDestinationFolder(destinationFolder);
    }

    @Test
    public void alignImgTest01() throws IOException, InterruptedException {
        // vertical-alignment values top, middle and bottom are not currently supported for inline-block elements and images
        convertToPdfAndCompare("alignImgTest01", sourceFolder, destinationFolder);
    }

    @Test
    public void imgAttrAlignLeftReadOrderPdfTest() throws IOException, InterruptedException {
        String pdfDestinationFile = destinationFolder + "imgAttrAlignLeftReadOrderPdf.pdf";
        String htmlSource = sourceFolder + "imgAttrAlignLeftReadOrderPdf.html";
        String cmpPdfDestinationFile = sourceFolder + "cmp_imgAttrAlignLeftReadOrderPdf.pdf";

        FileOutputStream fileOutputStream = new FileOutputStream(pdfDestinationFile);
        WriterProperties writerProperties = new WriterProperties();
        writerProperties.addXmpMetadata();

        PdfWriter pdfWriter = new PdfWriter(fileOutputStream, writerProperties);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.getCatalog().setLang(new PdfString("en-US"));
        pdfDocument.setTagged();

        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri(sourceFolder);

        FileInputStream fileInputStream = new FileInputStream(htmlSource);
        HtmlConverter.convertToPdf(fileInputStream, pdfDocument, converterProperties);

        Assert.assertNull(new CompareTool()
                .compareByContent(pdfDestinationFile, cmpPdfDestinationFile, destinationFolder));
    }
}
