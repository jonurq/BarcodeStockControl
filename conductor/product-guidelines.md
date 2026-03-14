# Product Guidelines

## 1. UI and Design System
- **Framework:** The UI strictly adheres to **Material Design 3 (Material You)**.
- **Implementation:** Utilize Jetpack Compose's Material 3 components (e.g., Cards, FABs, Scaffold, typography) to ensure a native, modern Android feel.
- **Goal:** The application should feel cohesive, respecting system themes (light/dark mode) and dynamic colors where appropriate.

## 2. Tone of Voice and Messaging
- **Style:** **Friendly & Helpful**.
- **Copywriting:** Use approachable, conversational language instead of robotic error codes.
  - *Example:* "Oops! We couldn't read that barcode, try getting closer."
- **Goal:** Reduce the stress of repetitive tasks by making the app feel cooperative and forgiving.

## 3. Error Handling and User Feedback
- **Approach:** **Subtle & Non-blocking**.
- **Execution:**
  - Avoid modal dialogs that require manual dismissal for regular operational feedback.
  - Use transient indicators like Snackbars for textual feedback.
  - Incorporate short haptic feedback (vibrations) and subtle audio cues for successes and errors so the user doesn't have to look at the screen constantly.
- **Goal:** Maintain continuous flow; the user should never be slowed down by the application's feedback mechanisms.

## 4. Accessibility and Ergonomics
- Prioritize clear spacing, readable text contrast, and large touch targets (minimum 48dp) to support fast and imprecise interactions.