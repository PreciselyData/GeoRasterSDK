package com.precisely.raster.samples.io;
import static com.precisely.raster.samples.io.ClassifiedRasterRandomBlockIteratorTester.filePath;


import com.mapinfo.midev.raster.mrr.jna.MRRLibrary;
import com.precisely.raster.RasterEngines;
import com.precisely.raster.common.IteratorMode;
import com.precisely.raster.common.RasterBandDataType;
import com.precisely.raster.common.RasterBandType;
import com.precisely.raster.common.RasterCacheMode;
import com.precisely.raster.internal.BlockBand;
import com.precisely.raster.internal.RasterRandomBlockIterator;
import com.precisely.raster.io.RasterDataset;
import com.precisely.raster.io.RasterEngine;
import com.precisely.raster.io.RasterInfo;
import java.util.ArrayList;
import java.util.List;

public class CreateContinuousGRDFileFromExistingRasterUsingRBI {



  public static void RBI_CreateContinuousFileFromExistingRaster(String rasterFile,
      String outputFile) {
    RasterEngine rasterEngine = RasterEngines.getRasterEngine(false);
    RasterDataset rasterDataSet = null;
    RasterDataset outputDataSet = null;
    RasterRandomBlockIterator itRead = null;
    RasterRandomBlockIterator itWrite = null;

    try {
      rasterDataSet = rasterEngine.open(rasterFile);
      RasterInfo rasterInfo = rasterDataSet.getRasterInfo();

      // create output raster using modified raster info
      outputDataSet = rasterEngine.createDataSet(outputFile, "MI_VerticalMapper_GRD", rasterInfo,
          RasterCacheMode.BASE);

      long nCols = rasterDataSet.getRasterInfo().getWidth().longValue();
      long nRows = rasterDataSet.getRasterInfo().getHeight().longValue();

      List<Number> Data = new ArrayList<>();
      List<Boolean> validity = new ArrayList<Boolean>();

      // get random iterator for reading data from input raster
      itRead = rasterDataSet.GetBlockIterator(0, 0, IteratorMode.READ, null, null);
      // get random iterator for writing data to output raster
      itWrite = outputDataSet.GetBlockIterator(0, 0, IteratorMode.READ_WRITE, null, null);

      itRead.Begin();
      itWrite.Begin();

      for (int fieldIndex = 0; fieldIndex < rasterInfo.getFieldInfos().size(); fieldIndex++) {
        for (int bandIndex = 0;
            bandIndex < rasterInfo.getFieldInfos().get(fieldIndex).getBandInfos().size();
            bandIndex++) {
          if (rasterInfo.getFieldInfos().get(fieldIndex).getBandInfos().get(bandIndex)
              .getBandType() == RasterBandType.CONCRETE) {
            BlockBand bb = itRead.getBand(bandIndex);
            RasterBandDataType type = rasterInfo.getFieldInfos().get(fieldIndex)
                .getBandInfos().get(bandIndex).getDataType();

            if (MRRLibrary.MIR_DataTypeSizeInBits(type.value()) < 8) {
              type = RasterBandDataType.SIGNED_INT8;
            }
            bb.GetBlock(0, 0, nCols, nRows, type, Data, validity, true);
            itWrite.getBand(bandIndex)
                .SetBlock(0, 0, nCols, nRows, type, Data, validity, true);
          }
        }
      }
    } finally {
      // end the iterators
      if (itRead != null)
        itRead.close();
      if (itWrite != null)
        itWrite.close();

      // close the datasets
      if (rasterDataSet != null)
        rasterDataSet.close();
      if (outputDataSet != null)
        outputDataSet.close();
    }
  }


  public static void main (String [] args) throws Exception {
    try {
      String inputFilePath = filePath + "grd/AvgFamilyIncome.grd";
      String outputFilePath = filePath + "grd/RBINew_AvgFamilyIncome.grd";
      RBI_CreateContinuousFileFromExistingRaster(inputFilePath, outputFilePath);
      System.out.println("RTI Write Test for AvgFamilyIncome.grd is successful!");
    }
    catch (Exception ex) {
      throw ex;
    }
  }



}


