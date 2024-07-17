package com.precisely.raster.samples.operations;

import static com.precisely.raster.samples.operations.CalculateOperationTest.filePath;


import com.precisely.raster.RasterEngines;
import com.precisely.raster.common.PolygoniseType;
import com.precisely.raster.common.RasterInputDetails;
import com.precisely.raster.common.internal.PolygoniseParameters;
import com.precisely.raster.io.RasterDataset;
import com.precisely.raster.io.RasterEngine;
import com.precisely.raster.operations.RasterOperations;


public class PolygoniseOperationTest {

  public static void Polygonise_RasterExtent(String inputFilePath ,  String outputTabFilePath )
  {
    RasterEngine rasterEngine= RasterEngines.getRasterEngine(false);
    try
    {
      RasterOperations operations = rasterEngine.getRasterOperations();
      operations.Polygonise(new RasterInputDetails(inputFilePath),outputTabFilePath,new PolygoniseParameters(
          PolygoniseType.RasterExtent),null);
    }
    catch(Exception ex)
    {
      throw ex;
    }
  }


public static void main(String [] args)
{
  try{
    String inputFilePath= filePath + "grc/SeattleLULC.grc";
    String outputTabFilePath= filePath + "grc/Java_SeattleLULC_polygonise.TAB";
    Polygonise_RasterExtent(inputFilePath ,outputTabFilePath);
    System.out.println("Unit test Polygonise Classified Raster extent is successful");
  }
  catch (Exception ex)
  {
    throw ex;
  }
}
}
