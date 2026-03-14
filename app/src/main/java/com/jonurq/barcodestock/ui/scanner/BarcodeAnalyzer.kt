package com.jonurq.barcodestock.ui.scanner

import android.annotation.SuppressLint
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

class BarcodeAnalyzer(
    private val onBarcodeDetected: (String) -> Unit
) : ImageAnalysis.Analyzer {

    private val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
        .build()

    private val scanner = BarcodeScanning.getClient(options)
    
    // Simple debouncing to avoid reading the same code 60 times a second
    private var lastScannedCode = ""
    private var lastScannedTime = 0L

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            
            scanner.process(image)
                .addOnSuccessListener { barcodes ->
                    if (barcodes.isNotEmpty()) {
                        val firstBarcode = barcodes.first()
                        firstBarcode.rawValue?.let { code ->
                            val currentTime = System.currentTimeMillis()
                            // Debounce for 1.5 seconds to prevent spam
                            if (code != lastScannedCode || (currentTime - lastScannedTime) > 1500) {
                                lastScannedCode = code
                                lastScannedTime = currentTime
                                onBarcodeDetected(code)
                            }
                        }
                    }
                }
                .addOnFailureListener {
                    // Handle failure gracefully
                }
                .addOnCompleteListener {
                    // Close the image so CameraX can proceed to the next frame
                    imageProxy.close()
                }
        } else {
            imageProxy.close()
        }
    }
}
