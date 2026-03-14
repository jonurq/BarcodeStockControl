# Implementation Plan

## Phase 1: Architecture Setup and Offline Storage
- [~] Task: Setup Local Database for Stock Items
    - [ ] Define `StockItem` entity/model (barcode, quantity).
    - [ ] Create Data Access Object (DAO) or repository for insert, update, delete, and getAll queries.
    - [ ] Setup Dependency Injection for the repository.
- [ ] Task: Write tests for Local Storage
    - [ ] Implement database/repository unit tests.
- [ ] Task: Conductor - User Manual Verification 'Phase 1' (Protocol in workflow.md)

## Phase 2: ML Kit Integration and Camera UI
- [ ] Task: Implement CameraX preview
    - [ ] Setup permissions and CameraX lifecycle binding in Jetpack Compose.
- [ ] Task: Integrate ML Kit Barcode Scanning
    - [ ] Create an `ImageAnalysis.Analyzer` using ML Kit for barcode detection.
    - [ ] Add haptic/audio feedback on successful scan.
- [ ] Task: Conductor - User Manual Verification 'Phase 2' (Protocol in workflow.md)

## Phase 3: Main UI, Grid Selector, and Export
- [ ] Task: Implement Quantity Selector Grid UI
    - [ ] Create Compose layout for quick number selection.
    - [ ] Handle duplicate code logic (overwrite warning).
- [ ] Task: Implement Main Screen List
    - [ ] Show scanned items with swipe-to-delete.
    - [ ] Implement "Delete All" with confirmation.
- [ ] Task: Implement Export functionality
    - [ ] Generate text payload and trigger Android share intent.
- [ ] Task: Conductor - User Manual Verification 'Phase 3' (Protocol in workflow.md)