/*
 * Copyright 2022, Precisely. All rights reserved.
 * This document contains unpublished, confidential, and proprietary information of Precisely.
 * No disclosure or use of any portion of the contents of this document may be made without the express written consent of Precisely.
 *
 */
package com.precisely.raster.samples.api;

import com.precisely.raster.io.RasterEngine;

import static com.precisely.raster.samples.util.RasterUtility.ISOLATED_PROCESS;
import static com.precisely.raster.samples.util.RasterUtility.RENDER_OUTPUT;
import static com.precisely.raster.samples.util.RasterUtility.getRasterEngine;
import static com.precisely.raster.samples.util.RasterUtility.renderRaster;
import static com.precisely.raster.samples.util.RasterUtility.setEnvironment;

public final class RasterRendererSample {

    /**
     * Private Constructor.
     */
    private RasterRendererSample() {
        // DO NOTHING
    }

    /**
     * Main method to execute RasterRenderer Sample.
     */
    public static void main(String[] args) {

        /*
         * Setting the Environment Variable Required for RasterSDK.
         */
        setEnvironment();

        /*
         * Get the RasterEngine based on the isolated flag.
         */
        RasterEngine rasterEngine = getRasterEngine(ISOLATED_PROCESS);

        /*
         * Create RasterRenderer and save the image file the provided output directory.
         */
        renderRaster(rasterEngine, RENDER_OUTPUT);
    }
}
