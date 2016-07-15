package com.receipt.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import com.receipt.main.cache.generators.CacheGenerators;
import com.receipt.managers.BasketManager;

/**
 * Example of using with files
 * @author attilio
 *
 */
public class Main {
	private static final Logger LOGGER = Logger.getLogger(BasketManager.class);
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		if (args.length == 2) {
			InputStream inputFile = toInputStream(args[0]);
			OutputStream outputFile = toOutputStream(args[1]);
			BasketManager bm = new BasketManager();
			bm.setCacheExemptProducts(CacheGenerators.cacheExemptProductsGenerator());
			bm.writeReceipts(inputFile, outputFile);
		}
		else System.out.println("Usage: java -jar sales-taxes-problem.jar fileInput fileOutput");
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("The task has been completed in: " + elapsedTime + " ms");
	}
	
	private static InputStream toInputStream(String fileInput) {
	    InputStream is = null;
	    try {
	        is = new FileInputStream(fileInput);
	    } catch (FileNotFoundException e) {
	    	LOGGER.error("File not found: " + fileInput);
	    } 
	    return is;
	}
	
	private static OutputStream toOutputStream(String fileOutput) {
		OutputStream os = null;
	    try {
	        os = new FileOutputStream(fileOutput);
	    } catch (FileNotFoundException e) {
	    	LOGGER.error("File not found: " + fileOutput);
	    } 
	    return os;
	}
}
