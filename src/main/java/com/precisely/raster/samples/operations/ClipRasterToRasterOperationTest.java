package com.precisely.raster.samples.operations;

import static com.precisely.raster.samples.operations.CalculateOperationTest.rasterEngine;


import com.precisely.raster.common.OutputRasterDetails;
import com.precisely.raster.common.RasterInputDetails;
import com.precisely.raster.operations.RasterOperations;

public class ClipRasterToRasterOperationTest {

  private static void ClipRaster_RetainOutside(String inputFile1, String inputFile2, String outputFile, String driverId){

    try{

      RasterInputDetails raster1 = new RasterInputDetails(inputFile1, 0, 0);
      RasterInputDetails raster2 = new RasterInputDetails(inputFile2, 0, 0);
      OutputRasterDetails outputRaster = new OutputRasterDetails(outputFile, driverId);

      RasterOperations operations = rasterEngine.getRasterOperations();

      operations.ClipRasterToRaster(raster1, raster2, outputRaster,
          false, null);

    }
    catch(Exception ex)
    {
      throw ex;
    }

  }

  public static void main(String[] args){
    try {
      String filePath = System.getProperty("user.dir") + "/data/";
      String inputPath1 = filePath+"mrr/ImagePalette_1.mrr";
      String inputPath2 = filePath+"mrr/ImagePalette_1.mrr";
      String outputPath = filePath+"mrr/OUTPUT_ImagePalette_1.mrr";

      ClipRaster_RetainOutside(inputPath1, inputPath2, outputPath, "MI_MRR");

      RasterOperations operations = rasterEngine.getRasterOperations();
      operations.Delete(outputPath,null);
      System.out.println("Unit Test for ClipRasterToRaster Retaining Outside for Image Palette MRR is successful!");
    }
    catch (Exception ex) {
      throw ex;
    }

  }

}
