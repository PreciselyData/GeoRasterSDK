![Precisely](Precisely_Logo.png "Precisely")
# GeoRasterSDK Sample
This sample gradle project of GeoRasterSDK demonstrates the usage of various GeoRasterSDK APIs.
The `/src/` folder contains sample code of different APIs and their usage.

## Data
This sample application is using `/data/DC_MRR_CLIP_5M.MRR`, which has one **continuous** field and one **concrete** band.

## Building the sample
1. Download the Spectrum GeoRasterSDK for Big Data distribution and extract the contents.
2. Copy the _<extracted_GeoRasterSDK_Zip>/sdk/lib/spectrum-raster-sdk-&lt;version&gt;.jar_ into the `/lib` directory.
3. Depending upon client OS, copy the contents of `/resources/libs/win64` or `/resources/libs/linux64` into the `/resources/nativeLibs` directory.
4. Run the following command from the root directory to build the project:
    ```
    gradlew build
    ```
   This command will generate the respective jar that will contain all the compiled code.

## Executing the sample
To execute the sample, complete the following steps:
1. Execute the jar with the fully qualified class name for the respective GeoRasterSDK APIs using the command mentioned below:
   ```sh
   java -cp build/libs/raster-sdk-samples-<version>.jar <fully_qualified_api_class_name>
    ```
2. **(Only for Linux OS)** Execute the following command on the terminal to set the environment variable:
   ```sh
   export LD_LIBRARY_PATH=<PATH_TO_GEORASTERSDK_SAMPLE_PROJECT>/resources/nativeLibs
   ```

**Sample execution command:**
1. To run getCellValue sample:
   ```sh
      java -cp build/libs/raster-sdk-samples-1.0.jar com.precisely.raster.samples.api.GetCellValueSample
    ```
2. To run getLineStatistics sample:
   ```sh
      java -cp build/libs/raster-sdk-samples-1.0.jar com.precisely.raster.samples.api.GetLineStatisticsSample
    ```
3. To run getPolygonStatistics sample:
   ```sh
      java -cp build/libs/raster-sdk-samples-1.0.jar com.precisely.raster.samples.api.GetPolygonStatisticsSample
    ```
4. To run getCrossSectionInfo sample:
   ```sh
      java -cp build/libs/raster-sdk-samples-1.0.jar com.precisely.raster.samples.api.GetCrossSectionInfoSample
    ```
5. To run RasterRenderer sample:
   ```sh
      java -cp build/libs/raster-sdk-samples-1.0.jar com.precisely.raster.samples.api.RasterRendererSample
    ```

## Custom Changes
For the simplicity of GeoRasterSDK usage, the sample application consists of one .MRR (Multi Resolution Raster) file and the input values used in the sample code are in accordance with the raster dataset.

To make custom changes, follow the below steps:
1. Navigate to `RasterUtility.java` class file in `src/main/java/com/precisely/raster/samples/util` directory.
2. Provide/Replace hardcoded values as per raster dataset used in the `/data/` directory and follow the aforementioned steps to build and execute the sample project.