package com.precisely.raster.samples.operations;

import static com.precisely.raster.samples.operations.CalculateOperationTest.filePath;
import static com.precisely.raster.samples.operations.CalculateOperationTest.rasterEngine;

import com.precisely.raster.common.RasterResampleMethod;
import com.precisely.raster.io.RasterDataset;
import com.precisely.raster.io.RasterInfo;
import com.precisely.raster.operations.RasterOperations;

public class ReprojectOperationTest {

  private static void ReprojectType1_GRD(String inputFile, String outputFile) {
    RasterDataset inputDataSet = null;
    RasterDataset outputDataSet = null;
    try {
      inputDataSet = rasterEngine.open(inputFile);
      RasterInfo rasterInfo = inputDataSet.getRasterInfo();

      String outputCoordString = "CoordSys Earth Projection 1, 104";
      RasterOperations operations = rasterEngine.getRasterOperations();

      operations.Reproject(inputFile, outputFile, "MI_VerticalMapper_GRD",
          rasterInfo.getCoordSysString(), outputCoordString, 0.000690256,
          0.000690256, RasterResampleMethod.Nearest);

    } catch (Exception ex) {
      throw ex;
    } finally {
      if (outputDataSet != null) {
        outputDataSet.close();
      }
      inputDataSet.close();

    }
  }

    public static void main(String[] args)
    {
      String inputFilePath= filePath + "grd/AvgFamilyIncome.grd";
      String outputFilePath= filePath + "grd/Reproject_AvgFamilyIncome.grd";
      ReprojectType1_GRD(inputFilePath, outputFilePath);
      RasterOperations operations = rasterEngine.getRasterOperations();
      operations.Delete(outputFilePath,null);
      System.out.println("Unit Test for Reproject operation is successful!");

    }




}
