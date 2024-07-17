package com.precisely.raster.samples.operations;
import static com.precisely.raster.samples.operations.CalculateOperationTest.rasterEngine;
import com.precisely.raster.common.RasterFieldType;
import com.precisely.raster.common.RasterFinalizationOptionsImpl;
import com.precisely.raster.common.internal.FieldBandFilter;
import com.precisely.raster.common.internal.RasterApiOptions;
import com.precisely.raster.common.internal.RasterClipExtent;
import com.precisely.raster.common.internal.RasterCreationOptions;
import com.precisely.raster.io.RasterDataset;
import com.precisely.raster.operations.RasterOperations;

public class ConvertOperationTest {

  private static void Convert_UsingDefaultParameters(String inputFile, String outputFile, String outputDriverID) {
    RasterDataset inputDataset1=null;
    try {
      RasterOperations operations = rasterEngine.getRasterOperations();
      inputDataset1=rasterEngine.open(inputFile);
      operations.Convert(inputFile, outputFile, outputDriverID);
    }
    catch(Exception ex)
    {
      throw ex;
    }
    finally {

      inputDataset1.close();


    }
  }
  public  static void test_ConvertFieldType_ClassifiedToImagePalette() {
    try {

      String filePath = System.getProperty("user.dir") + "/data/";
      String inputFilePath = filePath + "grc/SeattleLULC.grc";
      String outputFilePath = filePath + "grc/Converted_SeattleLULC.mrr";


      ConvertFieldType_ClassifiedToImagePaletteTest(inputFilePath, outputFilePath);
      RasterOperations operations = rasterEngine.getRasterOperations();
      operations.Delete(outputFilePath,null);
      System.out.println("Unit test for ConvertFieldType Operation from ClassifiedToImagePalette is successful!");
    }
    catch (Exception ex) {
      throw ex;
    }
  }
  private static void ConvertFieldType_ClassifiedToImagePaletteTest(String inputFile, String outputFile) {
    RasterDataset inputDataset1=null;

    try {
      RasterOperations operations = rasterEngine.getRasterOperations();

      RasterApiOptions apiOptions = new RasterApiOptions(new RasterCreationOptions(),
          new RasterFinalizationOptionsImpl(), new FieldBandFilter(), new RasterClipExtent());

      inputDataset1=rasterEngine.open(inputFile);
      operations.ConvertFieldType(inputFile, outputFile, "MI_MRR",
          RasterFieldType.IMAGE_PALETTE, apiOptions, null);
      System.out.println("Unit test for ConvertFieldType Operation from ClassifiedToImagePalette is successful!");
    }
    catch(Exception ex)
    {
      throw ex;
    }
    finally {
      inputDataset1.close();

    }
  }

  public static void ConvertWithDefaultParameters()
  {
    try {
      String filePath = System.getProperty("user.dir") + "/data/";
      String inputFilePath = filePath + "grd/AvgFamilyIncome.grd";
      String outputFilePath = filePath + "grd/Converted_AvgFamilyIncome.mrr";
      Convert_UsingDefaultParameters(inputFilePath, outputFilePath, "MI_MRR");
      RasterOperations operations = rasterEngine.getRasterOperations();
      operations.Delete(outputFilePath,null);
      System.out.println("Unit test for Convert Operation from GRDtoMRR is successful!");
    }
    catch(Exception ex)
    {
      throw ex;
    }
  }


  public static void main(String [] args)
  {
    test_ConvertFieldType_ClassifiedToImagePalette();
    ConvertWithDefaultParameters();

  }
}
