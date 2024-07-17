package com.precisely.raster.samples.operations;
import com.precisely.raster.RasterEngines;
import com.precisely.raster.common.RasterBandDataType;
import com.precisely.raster.common.RasterExpressionInputs;
import com.precisely.raster.common.RasterFieldType;
import com.precisely.raster.common.RasterResampleMethod;
import com.precisely.raster.common.internal.RasterApiOptions;
import com.precisely.raster.io.RasterDataset;
import com.precisely.raster.io.RasterEngine;
import com.precisely.raster.operations.RasterOperations;

public class CalculateOperationTest {
 static RasterEngine rasterEngine = RasterEngines.getRasterEngine();
  static String filePath = System.getProperty("user.dir") + "/data/";

  private static void Calculate_Addition(String inputFile1, String inputFile2, String outputFile, String outputDriverID, RasterFieldType rasterFieldType, RasterApiOptions apioptions){
    try{
      RasterOperations operations = rasterEngine.getRasterOperations();


      String[] inArray = new String[] { inputFile1, inputFile2 };
      String[] aliases = new String[] { "GRID1", "GRID2" };
      int useInfoFromGrid = 0;
      String expression = "GRID1+GRID2";
      char decimalSeparator = '.';
      char argumentSeparator = ',';

      RasterExpressionInputs[] inputRasters = new RasterExpressionInputs[inArray.length];
      for (int n = 0; n < inArray.length; n++)
      {
        inputRasters[n]=new RasterExpressionInputs();
        ( inputRasters[n]).filePath=inArray[n];
        ( inputRasters[n]).alias=(aliases[n]);
        ( inputRasters[n]).field=0;
      }

      String[] expressions = { expression };
      boolean bValidExpression = true;
      try
      {
        operations.ValidateCalculatorExpression(
            inputRasters,
            0,
            expressions,
            decimalSeparator,
            argumentSeparator,
            null, null,
            true, rasterFieldType,true ,false ,null);
      }

      catch (Exception ex)
      {
        throw ex;
      }

      if(bValidExpression)
      {
        operations.Calculate(
            inputRasters,
            0,
            outputFile,
            outputDriverID,
            expressions,
            decimalSeparator,
            argumentSeparator,
            null, null, true,
            rasterFieldType, false, RasterBandDataType.UNDEFINED_DATA,
            RasterResampleMethod.Bicubic, true, false, apioptions, null);
      }

    }
    catch(Exception ex){
      throw ex;
    }
  }

  public static void main(String [] args)
  {
    RasterDataset inputDataSet1=null;
    RasterDataset inputDataSet2=null;
    RasterDataset outputDataSet=null;
    try{
      String inputFile1= filePath + "grd/AvgFamilyIncome.grd";
      String inputFile2= filePath + "grd/AvgFamilyIncome.grd";
      inputDataSet1 = rasterEngine.open(inputFile1);
      inputDataSet2 = rasterEngine.open(inputFile2);
      String outputFile =  filePath + "grd/Output_AvgFamilyIncome.grd";
      Calculate_Addition(inputFile1, inputFile2, outputFile, "MI_VerticalMapper_GRD", RasterFieldType.CONTINUOUS,null);
      //RasterOperations operations = rasterEngine.getRasterOperations();
      // operations.Delete(outputFile,null);
      System.out.println("Unit Test for Calculate Addition for GRD file is successful!");
    }

    catch(Exception ex){
      throw ex;
    }
    finally{
      inputDataSet2.close();
      inputDataSet1.close();
    }
  }



}
