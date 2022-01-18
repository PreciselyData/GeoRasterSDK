/*
 * Copyright 2022, Precisely. All rights reserved.
 * This document contains unpublished, confidential, and proprietary information of Precisely.
 * No disclosure or use of any portion of the contents of this document may be made without the express written consent of Precisely.
 *
 */
package com.precisely.raster.samples.api;

import com.precisely.raster.io.RasterBandInfo;
import com.precisely.raster.io.RasterDataset;
import com.precisely.raster.io.RasterPolygonStatistics;

import static com.precisely.raster.samples.util.RasterUtility.BAND_INDEX;
import static com.precisely.raster.samples.util.RasterUtility.COORDINATE_SYSTEM;
import static com.precisely.raster.samples.util.RasterUtility.DATAFILE;
import static com.precisely.raster.samples.util.RasterUtility.FIELD_INDEX;
import static com.precisely.raster.samples.util.RasterUtility.FORMAT_OUTPUT;
import static com.precisely.raster.samples.util.RasterUtility.ISOLATED_PROCESS;
import static com.precisely.raster.samples.util.RasterUtility.POLYGON_WKB_STRING;
import static com.precisely.raster.samples.util.RasterUtility.getRasterBandInfo;
import static com.precisely.raster.samples.util.RasterUtility.getRasterDataset;
import static com.precisely.raster.samples.util.RasterUtility.getRasterPolygonStatistics;
import static com.precisely.raster.samples.util.RasterUtility.setEnvironment;

public final class GetPolygonStatisticsSample {

    /**
     * Private Constructor.
     */
    private GetPolygonStatisticsSample() {
        // DO NOTHING.
    }

    /**
     * Main method to execute GetPolygonStatistics Sample.
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
         * GetPolygonStatistics from the given Raster Dataset.
         */
        RasterPolygonStatistics polygonStatistics = getRasterPolygonStatistics(rasterDataset);

        /*
         * Display getPolygonStatistics for provided POLYGON_WKB_STRING and COORDINATE_SYSTEM.
         */
        System.out.println("\n" + FORMAT_OUTPUT);
        System.out.println("\t\t\tGET POLYGON STATISTICS OUTPUT");
        System.out.println(FORMAT_OUTPUT);
        System.out.println("\tRaster File used: " + DATAFILE);
        System.out.println("\tRaster Field Index: " + FIELD_INDEX);
        System.out.println("\tRaster Band Index: " + BAND_INDEX);
        System.out.println("\tWKB String: " + POLYGON_WKB_STRING);
        System.out.println("\tCoordinate System: " + COORDINATE_SYSTEM);
        System.out.println("\tRaster Band Data Type: " + rasterBandInfo.getDataType());
        System.out.println("\tMinimum Value: " + polygonStatistics.getMin());
        System.out.println("\tMaximum Value: " + polygonStatistics.getMax());
        System.out.println("\tMean: " + polygonStatistics.getMean());
        System.out.println("\tMedian: " + polygonStatistics.getMedian());
        System.out.println("\tMode: " + polygonStatistics.getMode());
        System.out.println("\tRange: " + polygonStatistics.getRange());
        System.out.println("\tStandard Deviation: " + polygonStatistics.getStdDev());
        System.out.println("\tLower Quartile: " + polygonStatistics.getLowerQuartile());
        System.out.println("\tUpper Quartile: " + polygonStatistics.getUpperQuartile());
        System.out.println("\tInter Quartile Range: " + polygonStatistics.getInterQuartileRange());
        System.out.println("\tNode Sum: " + polygonStatistics.getNodeSum());
        System.out.println("\tPercentage of Null Cells: " + polygonStatistics.getNullCellPercentage());
        System.out.println("\tCoefficient Of Variance: " + polygonStatistics.getCoefficientOfVariance());
        System.out.println("\tNumber of Non-Null Cells: " + polygonStatistics.getNumCells());
        System.out.println("\tNumber of Null Cells: " + polygonStatistics.getNumNullCells());
        System.out.println(FORMAT_OUTPUT);
    }
}

