package com.precisely.raster.samples.io;

import static com.precisely.raster.samples.io.ClassifiedRasterRandomBlockIteratorTester.filePath;
import static com.precisely.raster.samples.io.ClassifiedRasterRandomBlockIteratorTester.rasterEngine;
import com.mapinfo.midev.raster.mrr.jna.MRRLibrary;
import com.precisely.raster.common.IteratorMode;
import com.precisely.raster.common.RasterBandDataType;
import com.precisely.raster.common.RasterCacheMode;
import com.precisely.raster.internal.BlockBand;
import com.precisely.raster.io.ClassTable;
import com.precisely.raster.io.ClassTableRecord;
import com.precisely.raster.io.RandomBlockIterator;
import com.precisely.raster.io.RasterDataset;
import com.precisely.raster.io.RasterInfo;
import java.util.ArrayList;
import java.util.List;

public class CreateImagePaletteRasterUsingRBITester {


  private static void RBI_CreateImagePalette_FromExistingRaster(String rasterFile,
      String outputFile){
    RasterDataset rasterDataSet = null;
    RasterDataset outputDataSet = null;
    try {
      rasterDataSet = rasterEngine.open(rasterFile);
      RasterInfo rasterInfo = rasterDataSet.getRasterInfo();

      // create output raster using modified raster info
      outputDataSet = rasterEngine.createDataSet(outputFile, "MI_MRR", rasterInfo, RasterCacheMode.FULL);

      ClassTable inTable = rasterDataSet.getClassTables().get(0);
      ClassTable outTable = outputDataSet.getClassTables().get(0);

      int recordCount = inTable.getRecordCount();
      outTable.SetRecordCount(recordCount);

      for(int i = 0; i < recordCount; i++){
        ClassTableRecord record = inTable.getTableRecords().get(i);
        outTable.SetRecord(i, record);
      }

      long nCols = rasterDataSet.getRasterInfo().getWidth().longValue();
      long nRows = rasterDataSet.getRasterInfo().getHeight().longValue();

      List<Number> Data = new ArrayList<>();
      List<Boolean> validity = new ArrayList<Boolean>();

      try(RandomBlockIterator it_read = rasterDataSet.GetBlockIterator(0, 0, IteratorMode.READ, null, null)) {
        it_read.Begin();
        BlockBand readBand = it_read.getBand(0);
        try (RandomBlockIterator it_write = outputDataSet.GetBlockIterator(0, 0, IteratorMode.READ_WRITE, null, null)) {
          it_write.Begin();
          BlockBand writeBand = it_write.getBand(0);
          RasterBandDataType type = rasterInfo.getFieldInfos().get(0).getBandInfos().get(0).getDataType();
          //for any band data type if size is less than 8 bits we force it to convert it to 8 bit,SIGNED_INT8 type
          if (MRRLibrary.MIR_DataTypeSizeInBits(type.value()) < 8) {
            type = RasterBandDataType.SIGNED_INT8;
          }
          readBand.GetBlock(0, 0, nCols, nRows, type, Data, validity, true);
          writeBand.SetBlock(0, 0, nCols, nRows, type, Data, validity, true);
        }
      }
    }
    finally{
      if(rasterDataSet != null)
        rasterDataSet.close();
      if(outputDataSet != null)
        outputDataSet.close();
    }
  }

  public static void main (String [] args)
  {
    try {
      String inputFilePath = filePath+"mrr/ImagePalette_1.mrr";
      String outputFilePath = filePath+"mrr/RBI_NewImagePalette_1.mrr";
      RBI_CreateImagePalette_FromExistingRaster(inputFilePath, outputFilePath);
      System.out.println("RBI Unit Test for ImagePalette_1.mrr from existing raster is successful!");
    }
    catch (Exception ex) {
      throw ex;
    }
  }



}
