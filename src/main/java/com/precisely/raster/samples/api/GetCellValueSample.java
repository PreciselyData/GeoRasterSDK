/*
 * Copyright 2022, Precisely. All rights reserved.
 * This document contains unpublished, confidential, and proprietary information of Precisely.
 * No disclosure or use of any portion of the contents of this document may be made without the express written consent of Precisely.
 *
 */
package com.precisely.raster.samples.api;


import com.precisely.raster.io.CellValue;
import com.precisely.raster.io.RasterBandInfo;
import com.precisely.raster.io.RasterDataset;

import static com.precisely.raster.samples.util.RasterUtility.BAND_INDEX;
import static com.precisely.raster.samples.util.RasterUtility.DATAFILE;
import static com.precisely.raster.samples.util.RasterUtility.FIELD_INDEX;
import static com.precisely.raster.samples.util.RasterUtility.FORMAT_OUTPUT;
import static com.precisely.raster.samples.util.RasterUtility.ISOLATED_PROCESS;
import static com.precisely.raster.samples.util.RasterUtility.WORLD_X;
import static com.precisely.raster.samples.util.RasterUtility.WORLD_Y;
import static com.precisely.raster.samples.util.RasterUtility.getRasterCellValue;
import static com.precisely.raster.samples.util.RasterUtility.getRasterBandInfo;
import static com.precisely.raster.samples.util.RasterUtility.getRasterDataset;
import static com.precisely.raster.samples.util.RasterUtility.setEnvironment;

public final class GetCellValueSample {

    /**
     * Private Constructor.
     */
    private GetCellValueSample() {
        // DO NOTHING.
    }

    /**
     * Main method to execute GetCellValue Sample.
     */
    public static void main(String[] args) {

        /*
         * Setting up environment path for native binaries.
         */
        setEnvironment();

        /*
         * Get the RasterDataset using isolated flag as 'false' to run the RasterSDK in the same JVM.
         */
        RasterDataset rasterDataset = getRasterDataset(ISOLATED_PROCESS);

        /*
         * Get the RasterBandInfo from the given Raster Dataset.
         */
        RasterBandInfo rasterBandInfo = getRasterBandInfo(rasterDataset);

        /*
         * GetCellValue from the Raster Dataset.
         */
        CellValue cellValue = getRasterCellValue(rasterDataset);

        /*
         *  Display getCellValue for provided world co-ordinates.
         */
        System.out.println("\n" + FORMAT_OUTPUT);
        System.out.println("\t\t\tGET CELL VALUE OUTPUT");
        System.out.println(FORMAT_OUTPUT);
        System.out.println("\tRaster file used: " + DATAFILE);
        System.out.println("\tRaster Field Index: " + FIELD_INDEX);
        System.out.println("\tRaster Band Index: " + BAND_INDEX);
        System.out.println("\tRaster Band Data Type: " + rasterBandInfo.getDataType());
        System.out.println("\tWorld Coordinates (X,Y) : (" + WORLD_X + "," + WORLD_Y + ")");
        System.out.println("\tCellValue: " + cellValue.numberValue().floatValue());
        System.out.println(FORMAT_OUTPUT);
    }
}
