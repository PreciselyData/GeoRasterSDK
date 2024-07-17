package com.precisely.raster.samples.io;

import static com.precisely.raster.samples.io.ClassifiedRasterRandomBlockIteratorTester.filePath;
import static com.precisely.raster.samples.io.ClassifiedRasterRandomBlockIteratorTester.rasterEngine;
import com.mapinfo.midev.raster.mrr.jna.MRRLibrary;
import com.precisely.raster.common.IteratorMode;
import com.precisely.raster.common.RasterBandDataType;
import com.precisely.raster.common.RasterCacheMode;
import com.precisely.raster.internal.BlockBand;
import com.precisely.raster.internal.RasterRandomBlockIterator;
import com.precisely.raster.io.RasterDataset;
import com.precisely.raster.io.RasterInfo;
import java.util.ArrayList;
import java.util.List;

public class CreateImageryTIFFileFromExistingRasterUsingRBI {
  private static void RBI_CreateImageryTIFFileFromExistingRaster(String rasterFile, String outputFile) {
    RasterDataset rasterDataset = null;
    RasterDataset outputDataset = null;
    RasterRandomBlockIterator itRead = null;
    RasterRandomBlockIterator itWrite = null;

    try {
      rasterDataset = rasterEngine.open(rasterFile);
      RasterInfo rasterInfo = rasterDataset.getRasterInfo();

      // create output raster using modified raster info
      outputDataset = rasterEngine.createDataSet(outputFile, "MI_GeoTiff_IMG", rasterInfo, RasterCacheMode.BASE);

      long nCols = rasterDataset.getRasterInfo().getWidth().longValue();
      long nRows = rasterDataset.getRasterInfo().getHeight().longValue();

      List<Number> Data1 = new ArrayList<>();
      List<Boolean> validity = new ArrayList<Boolean>();

      // get random iterator for reading data from input raster
      itRead = rasterDataset.GetBlockIterator(0, 0, IteratorMode.READ, null, null);
      // get random iterator for writing data to output raster
      itWrite = outputDataset.GetBlockIterator(0, 0, IteratorMode.READ_WRITE, null, null);

      itRead.Begin();
      itWrite.Begin();

      for (int fieldIndex = 0; fieldIndex < rasterInfo.getFieldInfos().size(); fieldIndex++) {
        for (int bandIndex = 0; bandIndex < rasterInfo.getFieldInfos().get(fieldIndex).getBandInfos().size(); bandIndex++) {
          BlockBand bb = itRead.getBand(bandIndex);
          RasterBandDataType type = rasterInfo.getFieldInfos().get(fieldIndex).getBandInfos().get(bandIndex).getDataType();
          //for any band data type if size is less than 8 bits we force it to convert it to 8 bit,SIGNED_INT8 type
          if (MRRLibrary.MIR_DataTypeSizeInBits(type.value()) < 8) {
            type = RasterBandDataType.SIGNED_INT8;
          }
          bb.GetBlock(0, 0, nCols, nRows, type, Data1, validity, true);
          itWrite.getBand(bandIndex).SetBlock(0, 0, nCols, nRows, type, Data1, validity, true);
        }
      }
    } finally {
      // end the iterators
      if(itRead != null)
        itRead.close();
      if(itWrite != null)
        itWrite.close();

      // close the datasets
      if(rasterDataset != null)
        rasterDataset.close();
      if(outputDataset != null)
        outputDataset.close();
    }
  }

  public static void main(String [] args)
  {
    try {
      String inputFilePath =filePath+"tif/BigTIFF.tif";
      String outputFilePath = filePath+"tif/Output_BigTIFF.tif";
      RBI_CreateImageryTIFFileFromExistingRaster(inputFilePath, outputFilePath);
     // RasterOperations operations = rasterEngine.getRasterOperations();
     // operations.Delete(outputFilePath,null);
      System.out.println("RBI Write Test for BigTIFF.tif is successful!");
    }
    catch (Exception ex) {
      throw ex;
    }
  }


}
