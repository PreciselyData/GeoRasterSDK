![Precisely](Precisely_Logo.png "Precisely")
# GeoRasterSDK Sample
This sample gradle project of GeoRasterSDK demonstrates the usage of various GeoRasterSDK APIs.
The `/src/` folder contains sample code of different APIs and their usage.

## Data
This sample application is using `/data/DC_MRR_CLIP_5M.MRR`, which has one **continuous** field and one **concrete** band.

## Building the sample
1. Download the Spectrum GeoRasterSDK distribution from Jfrog (https://jfrog.precisely.engineering/artifactory/sky-raw-snapshot/com/pb/miraster/Spatial_1/SpatialServer-Releases/com/precisely/rastersdk/geo-raster-sdk/2.0.1/geo-raster-sdk-2.0.1.zip)  and extract the contents.
2. Extract the zip into the `/lib` directory.
3. Run the following command from the root directory to build the project:
    ```
    gradlew build
    ```
   This command will generate the respective jar that will contain all the compiled code.

4. Go to the src/main/java/com/precisely/raster/samples/io/SampleTests.java
5. Run the test
6. (Only for Linux OS) Execute the following command on the terminal to set the environment variable:
export LD_LIBRARY_PATH=lib/geo-raster-sdk-2.0.1/resources/linux64/
7. you can also run the test from command line in linux using -
java -Dcom.precisely.raster.lib.dir=lib/geo-raster-sdk-2.0.1/resources/linux64/ -cp build/libs/geo-raster-sdk-samples-1.0.jar com.precisely.raster.samples.io.SampleTests
8. To can also run the test from command line in windows using -> 
java -Dcom.precisely.raster.lib.dir=lib\geo-raster-sdk-2.0.1\resources\win64\ -cp build\libs\geo-raster-sdk-samples-1.0.jar com.precisely.raster.samples.io.SampleTests
