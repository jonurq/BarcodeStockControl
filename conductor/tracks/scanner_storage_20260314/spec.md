# Track Specification: Implement ML Kit Scanner and Offline Storage

## Objective
Implement the core functionality of the Barcode Stock Control app: a fast ML Kit-based barcode scanner, a quantity entry grid UI, and offline storage using Room or DataStore.

## Requirements
- **Scanner:** Integrate Google ML Kit Barcode Scanning. Must process frames directly from the CameraX preview in real-time.
- **Quantity UI:** Provide a grid of numbers for rapid quantity selection immediately after a successful scan.
- **Offline Storage:** Store scanned items (Barcode, Quantity, Timestamp) locally. Handle duplicate scans by prompting the user to overwrite.
- **List View:** Display all scanned items on the main screen with options to edit or delete (swipe-to-delete).
- **Haptic/Audio Feedback:** Vibrate or play a short sound on success/error.
- **Export:** Allow exporting the database to a simple text format (`Barcode,Stock\n`).