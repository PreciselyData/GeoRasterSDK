package com.precisely.raster.samples.operations;

import static com.precisely.raster.samples.operations.CalculateOperationTest.filePath;
import static com.precisely.raster.samples.operations.CalculateOperationTest.rasterEngine;

import com.mapinfo.midev.raster.mrr.jna.MIR_RasterizeForegroundValueType;
import com.mapinfo.midev.raster.mrr.jna.MIR_RasterizeOperator;
import com.precisely.raster.common.MapInfoUnitCode;
import com.precisely.raster.operations.RasterOperations;

public class RasterizeOperationTest {

  private static void Rasterize_UsingVectorField(String strInVecFilePath, String strOutFilePath, String outputRasterDriverId) {

    try {

      //VectorToGrid operation with  Sum operator
      short[] vectorFields = {1};
      RasterOperations rasterOperations = rasterEngine.getRasterOperations();

      rasterOperations.Rasterize(strInVecFilePath, strOutFilePath, outputRasterDriverId, false,"",
          MapInfoUnitCode.METERS, 100.0f, 100.0f,
          MIR_RasterizeForegroundValueType.MIR_Rasterize_Field, 10.0f, MIR_RasterizeOperator.MIR_Rasterize_Sum,
          false, 100.0f, vectorFields, null, null);

    }
    catch(Exception ex)
    {
      throw ex;
    }
}


  public static void main(String[] args) {
    try {
      String inputFilePath= filePath + "VectorFiles/VecOverlap.TAB";
      String outputFilePath= filePath + "VectorFiles/Rasterized_NewVecOverlap.mrr";

      Rasterize_UsingVectorField(inputFilePath, outputFilePath, "MI_MRR");
      RasterOperations operations = rasterEngine.getRasterOperations();
      operations.Delete(outputFilePath,null);
      System.out.println("Unit test for Rasterize operation using vector field value is successful!");
    }
    catch(Exception ex)
    {
      throw ex;
    }
  }

}
