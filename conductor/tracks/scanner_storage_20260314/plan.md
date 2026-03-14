# Implementation Plan

## Phase 1: Architecture Setup and Offline Storage [checkpoint: 4c320aa]
- [x] Task: Setup Local Database for Stock Items [5dcb58e]
    - [x] Define `StockItem` entity/model (barcode, quantity).
    - [x] Create Data Access Object (DAO) or repository for insert, update, delete, and getAll queries.
    - [x] Setup Dependency Injection for the repository.
- [x] Task: Write tests for Local Storage
    - [x] Implement database/repository unit tests.
- [x] Task: Conductor - User Manual Verification 'Phase 1' (Protocol in workflow.md)

## Phase 2: ML Kit Integration and Camera UI [checkpoint: 643d561]
- [x] Task: Implement CameraX preview [27057f7]
    - [x] Setup permissions and CameraX lifecycle binding in Jetpack Compose.
- [x] Task: Integrate ML Kit Barcode Scanning [57b10b4]
    - [x] Create an `ImageAnalysis.Analyzer` using ML Kit for barcode detection.
    - [x] Add haptic/audio feedback on successful scan.
- [x] Task: Conductor - User Manual Verification 'Phase 2' (Protocol in workflow.md)

## Phase 3: Main UI, Grid Selector, and Export
- [x] Task: Implement Quantity Selector Grid UI [4f1346b]
    - [x] Create Compose layout for quick number selection.
    - [x] Handle duplicate code logic (overwrite warning).
- [~] Task: Implement Main Screen List
    - [ ] Show scanned items with swipe-to-delete.
    - [ ] Implement "Delete All" with confirmation.
- [ ] Task: Implement Export functionality
    - [ ] Generate text payload and trigger Android share intent.
- [ ] Task: Conductor - User Manual Verification 'Phase 3' (Protocol in workflow.md)