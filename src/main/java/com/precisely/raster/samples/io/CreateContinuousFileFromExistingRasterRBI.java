package com.precisely.raster.samples.io;

import com.precisely.raster.samples.EndToEndSamples;

public class CreateContinuousFileFromExistingRasterRBI {

    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir") + "/data/";

        try {
            String inputFile = filePath+"/mrr/DC_MRR_CLIP_5M.MRR";
            String outputFile = filePath+"Output_DC_MRR_CLIP_5M.MRR";
            EndToEndSamples.CreateContinuousFileFromExistingRasterRBI(inputFile, outputFile);
            System.out.println("Read and Write Raster using RandomIterator is successful!");
        }

        catch (Exception ex){
            throw ex;
        }
    }
}


