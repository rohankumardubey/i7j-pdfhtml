package com.itextpdf.html2pdf.css.w3c.css_backgrounds;

import com.itextpdf.html2pdf.css.w3c.W3CCssTest;

// TODO DEVSIX-1457 support background-position
// TODO DEVSIX-2105 support background-clip
// TODO DEVSIX-4389 support background-color
public class BackgroundColorPaddingBoxTest extends W3CCssTest {
    @Override
    protected String getHtmlFileName() {
        return "background_color_padding_box.htm";
    }
}