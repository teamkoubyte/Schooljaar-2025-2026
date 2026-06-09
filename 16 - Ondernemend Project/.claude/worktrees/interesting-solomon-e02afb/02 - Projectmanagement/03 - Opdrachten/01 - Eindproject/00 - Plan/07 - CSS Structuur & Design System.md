# CSS Structuur & Design System - Documentenkluis

## Overzicht

Dit project gebruikt **Custom CSS** zonder frameworks (geen Tailwind, Bootstrap, etc.). 
De CSS is georganiseerd in logische modules met herbruikbare classes en CSS Custom Properties.

---

## CSS File Structuur

```
resources/css/
├── variables.css      # CSS Custom Properties (kleuren, fonts, spacing)
├── reset.css          # CSS Reset/Normalize
├── base.css           # Base HTML element styles
├── layout.css         # Layout utilities (grid, flexbox, containers)
├── components.css     # Reusable UI components
├── forms.css          # Form elements
├── utilities.css      # Helper classes
└── responsive.css     # Media queries

public/css/
└── app.css           # Compiled/combined CSS
```

---

## 1. CSS Custom Properties (variables.css)

```css
:root {
    /* Colors */
    --color-primary: #3b82f6;
    --color-primary-dark: #2563eb;
    --color-primary-light: #60a5fa;
    
    --color-secondary: #8b5cf6;
    --color-secondary-dark: #7c3aed;
    
    --color-success: #10b981;
    --color-warning: #f59e0b;
    --color-error: #ef4444;
    --color-info: #06b6d4;
    
    /* Grays */
    --color-gray-50: #f9fafb;
    --color-gray-100: #f3f4f6;
    --color-gray-200: #e5e7eb;
    --color-gray-300: #d1d5db;
    --color-gray-400: #9ca3af;
    --color-gray-500: #6b7280;
    --color-gray-600: #4b5563;
    --color-gray-700: #374151;
    --color-gray-800: #1f2937;
    --color-gray-900: #111827;
    
    --color-white: #ffffff;
    --color-black: #000000;
    
    /* Typography */
    --font-sans: system-ui, -apple-system, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
    --font-mono: ui-monospace, "Cascadia Code", "Source Code Pro", Menlo, Monaco, monospace;
    
    --font-size-xs: 0.75rem;      /* 12px */
    --font-size-sm: 0.875rem;     /* 14px */
    --font-size-base: 1rem;       /* 16px */
    --font-size-lg: 1.125rem;     /* 18px */
    --font-size-xl: 1.25rem;      /* 20px */
    --font-size-2xl: 1.5rem;      /* 24px */
    --font-size-3xl: 1.875rem;    /* 30px */
    --font-size-4xl: 2.25rem;     /* 36px */
    
    --font-weight-normal: 400;
    --font-weight-medium: 500;
    --font-weight-semibold: 600;
    --font-weight-bold: 700;
    
    --line-height-tight: 1.25;
    --line-height-normal: 1.5;
    --line-height-relaxed: 1.75;
    
    /* Spacing */
    --spacing-1: 0.25rem;   /* 4px */
    --spacing-2: 0.5rem;    /* 8px */
    --spacing-3: 0.75rem;   /* 12px */
    --spacing-4: 1rem;      /* 16px */
    --spacing-5: 1.25rem;   /* 20px */
    --spacing-6: 1.5rem;    /* 24px */
    --spacing-8: 2rem;      /* 32px */
    --spacing-10: 2.5rem;   /* 40px */
    --spacing-12: 3rem;     /* 48px */
    --spacing-16: 4rem;     /* 64px */
    
    /* Border Radius */
    --radius-sm: 0.25rem;   /* 4px */
    --radius-md: 0.375rem;  /* 6px */
    --radius-lg: 0.5rem;    /* 8px */
    --radius-xl: 0.75rem;   /* 12px */
    --radius-full: 9999px;
    
    /* Shadows */
    --shadow-sm: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
    --shadow-md: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06);
    --shadow-lg: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05);
    --shadow-xl: 0 20px 25px -5px rgba(0, 0, 0, 0.1), 0 10px 10px -5px rgba(0, 0, 0, 0.04);
    
    /* Transitions */
    --transition-fast: 150ms ease-in-out;
    --transition-base: 300ms ease-in-out;
    --transition-slow: 500ms ease-in-out;
    
    /* Z-index */
    --z-dropdown: 100;
    --z-sticky: 200;
    --z-fixed: 300;
    --z-modal-backdrop: 400;
    --z-modal: 500;
    --z-popover: 600;
    --z-tooltip: 700;
    
    /* Container */
    --container-max-width: 1280px;
    --container-padding: var(--spacing-4);
}
```

---

## 2. Reset CSS (reset.css)

```css
/* Modern CSS Reset */
*, *::before, *::after {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
}

html {
    -webkit-text-size-adjust: 100%;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
}

body {
    line-height: var(--line-height-normal);
    font-family: var(--font-sans);
}

img, picture, video, canvas, svg {
    display: block;
    max-width: 100%;
}

input, button, textarea, select {
    font: inherit;
}

button {
    cursor: pointer;
    background: none;
    border: none;
}

a {
    color: inherit;
    text-decoration: none;
}

ul, ol {
    list-style: none;
}

table {
    border-collapse: collapse;
    border-spacing: 0;
}
```

---

## 3. Base Styles (base.css)

```css
body {
    font-size: var(--font-size-base);
    color: var(--color-gray-900);
    background-color: var(--color-gray-50);
    min-height: 100vh;
}

h1, h2, h3, h4, h5, h6 {
    font-weight: var(--font-weight-bold);
    line-height: var(--line-height-tight);
    margin-bottom: var(--spacing-4);
}

h1 { font-size: var(--font-size-4xl); }
h2 { font-size: var(--font-size-3xl); }
h3 { font-size: var(--font-size-2xl); }
h4 { font-size: var(--font-size-xl); }
h5 { font-size: var(--font-size-lg); }
h6 { font-size: var(--font-size-base); }

p {
    margin-bottom: var(--spacing-4);
    line-height: var(--line-height-relaxed);
}

a {
    color: var(--color-primary);
    transition: color var(--transition-fast);
}

a:hover {
    color: var(--color-primary-dark);
}

strong {
    font-weight: var(--font-weight-bold);
}

code {
    font-family: var(--font-mono);
    font-size: var(--font-size-sm);
    padding: 0.125rem 0.25rem;
    background-color: var(--color-gray-100);
    border-radius: var(--radius-sm);
}
```

---

## 4. Layout (layout.css)

```css
/* Container */
.container {
    width: 100%;
    max-width: var(--container-max-width);
    margin: 0 auto;
    padding: 0 var(--container-padding);
}

/* Flexbox utilities */
.flex {
    display: flex;
}

.flex-col {
    flex-direction: column;
}

.flex-wrap {
    flex-wrap: wrap;
}

.items-center {
    align-items: center;
}

.items-start {
    align-items: flex-start;
}

.items-end {
    align-items: flex-end;
}

.justify-center {
    justify-content: center;
}

.justify-between {
    justify-content: space-between;
}

.justify-start {
    justify-content: flex-start;
}

.justify-end {
    justify-content: flex-end;
}

.gap-1 { gap: var(--spacing-1); }
.gap-2 { gap: var(--spacing-2); }
.gap-3 { gap: var(--spacing-3); }
.gap-4 { gap: var(--spacing-4); }
.gap-6 { gap: var(--spacing-6); }
.gap-8 { gap: var(--spacing-8); }

/* Grid */
.grid {
    display: grid;
}

.grid-cols-1 { grid-template-columns: repeat(1, 1fr); }
.grid-cols-2 { grid-template-columns: repeat(2, 1fr); }
.grid-cols-3 { grid-template-columns: repeat(3, 1fr); }
.grid-cols-4 { grid-template-columns: repeat(4, 1fr); }

/* Layout Components */
.app-layout {
    min-height: 100vh;
    display: flex;
    flex-direction: column;
}

.app-header {
    background-color: var(--color-white);
    border-bottom: 1px solid var(--color-gray-200);
    padding: var(--spacing-4) 0;
}

.app-main {
    flex: 1;
    padding: var(--spacing-6) 0;
}

.app-footer {
    background-color: var(--color-gray-100);
    padding: var(--spacing-6) 0;
    margin-top: auto;
}

.sidebar {
    width: 250px;
    background-color: var(--color-white);
    border-right: 1px solid var(--color-gray-200);
    padding: var(--spacing-6);
}

.main-content {
    flex: 1;
    padding: var(--spacing-6);
}
```

---

## 5. Components (components.css)

```css
/* Buttons */
.btn {
    display: inline-flex;
    align-items: center;
    justify-content: center;
    padding: var(--spacing-2) var(--spacing-4);
    font-size: var(--font-size-base);
    font-weight: var(--font-weight-medium);
    border-radius: var(--radius-md);
    transition: all var(--transition-fast);
    cursor: pointer;
    border: 1px solid transparent;
    gap: var(--spacing-2);
}

.btn:disabled {
    opacity: 0.5;
    cursor: not-allowed;
}

.btn-primary {
    background-color: var(--color-primary);
    color: var(--color-white);
}

.btn-primary:hover:not(:disabled) {
    background-color: var(--color-primary-dark);
}

.btn-secondary {
    background-color: var(--color-gray-200);
    color: var(--color-gray-900);
}

.btn-secondary:hover:not(:disabled) {
    background-color: var(--color-gray-300);
}

.btn-danger {
    background-color: var(--color-error);
    color: var(--color-white);
}

.btn-success {
    background-color: var(--color-success);
    color: var(--color-white);
}

.btn-sm {
    padding: var(--spacing-1) var(--spacing-3);
    font-size: var(--font-size-sm);
}

.btn-lg {
    padding: var(--spacing-3) var(--spacing-6);
    font-size: var(--font-size-lg);
}

/* Cards */
.card {
    background-color: var(--color-white);
    border-radius: var(--radius-lg);
    box-shadow: var(--shadow-md);
    overflow: hidden;
}

.card-header {
    padding: var(--spacing-4) var(--spacing-6);
    border-bottom: 1px solid var(--color-gray-200);
}

.card-body {
    padding: var(--spacing-6);
}

.card-footer {
    padding: var(--spacing-4) var(--spacing-6);
    border-top: 1px solid var(--color-gray-200);
    background-color: var(--color-gray-50);
}

/* Alerts */
.alert {
    padding: var(--spacing-4);
    border-radius: var(--radius-md);
    margin-bottom: var(--spacing-4);
}

.alert-success {
    background-color: #d1fae5;
    color: #065f46;
    border: 1px solid #10b981;
}

.alert-error {
    background-color: #fee2e2;
    color: #991b1b;
    border: 1px solid #ef4444;
}

.alert-warning {
    background-color: #fef3c7;
    color: #92400e;
    border: 1px solid #f59e0b;
}

.alert-info {
    background-color: #cffafe;
    color: #155e75;
    border: 1px solid #06b6d4;
}

/* Modal */
.modal-backdrop {
    position: fixed;
    inset: 0;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: var(--z-modal-backdrop);
    display: flex;
    align-items: center;
    justify-content: center;
}

.modal {
    background-color: var(--color-white);
    border-radius: var(--radius-xl);
    box-shadow: var(--shadow-xl);
    max-width: 500px;
    width: 90%;
    max-height: 90vh;
    overflow-y: auto;
    z-index: var(--z-modal);
}

.modal-header {
    padding: var(--spacing-6);
    border-bottom: 1px solid var(--color-gray-200);
}

.modal-body {
    padding: var(--spacing-6);
}

.modal-footer {
    padding: var(--spacing-6);
    border-top: 1px solid var(--color-gray-200);
    display: flex;
    gap: var(--spacing-3);
    justify-content: flex-end;
}

/* Badge */
.badge {
    display: inline-flex;
    align-items: center;
    padding: 0.25rem 0.625rem;
    font-size: var(--font-size-xs);
    font-weight: var(--font-weight-medium);
    border-radius: var(--radius-full);
}

.badge-primary {
    background-color: var(--color-primary-light);
    color: var(--color-primary-dark);
}

.badge-success {
    background-color: #d1fae5;
    color: #065f46;
}

.badge-error {
    background-color: #fee2e2;
    color: #991b1b;
}

/* Breadcrumb */
.breadcrumb {
    display: flex;
    align-items: center;
    gap: var(--spacing-2);
    font-size: var(--font-size-sm);
    color: var(--color-gray-600);
}

.breadcrumb-separator {
    color: var(--color-gray-400);
}

/* Dropdown */
.dropdown {
    position: relative;
    display: inline-block;
}

.dropdown-menu {
    position: absolute;
    top: 100%;
    left: 0;
    margin-top: var(--spacing-2);
    background-color: var(--color-white);
    border: 1px solid var(--color-gray-200);
    border-radius: var(--radius-md);
    box-shadow: var(--shadow-lg);
    min-width: 200px;
    z-index: var(--z-dropdown);
}

.dropdown-item {
    display: block;
    padding: var(--spacing-2) var(--spacing-4);
    color: var(--color-gray-700);
    transition: background-color var(--transition-fast);
}

.dropdown-item:hover {
    background-color: var(--color-gray-100);
}
```

---

## 6. Forms (forms.css)

```css
.form-group {
    margin-bottom: var(--spacing-4);
}

.form-label {
    display: block;
    font-size: var(--font-size-sm);
    font-weight: var(--font-weight-medium);
    color: var(--color-gray-700);
    margin-bottom: var(--spacing-2);
}

.form-label-required::after {
    content: " *";
    color: var(--color-error);
}

.form-input,
.form-textarea,
.form-select {
    width: 100%;
    padding: var(--spacing-2) var(--spacing-3);
    font-size: var(--font-size-base);
    border: 1px solid var(--color-gray-300);
    border-radius: var(--radius-md);
    background-color: var(--color-white);
    transition: border-color var(--transition-fast), box-shadow var(--transition-fast);
}

.form-input:focus,
.form-textarea:focus,
.form-select:focus {
    outline: none;
    border-color: var(--color-primary);
    box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.1);
}

.form-input.error,
.form-textarea.error,
.form-select.error {
    border-color: var(--color-error);
}

.form-input.error:focus,
.form-textarea.error:focus,
.form-select.error:focus {
    box-shadow: 0 0 0 3px rgba(239, 68, 68, 0.1);
}

.form-textarea {
    resize: vertical;
    min-height: 100px;
}

.form-error {
    display: block;
    font-size: var(--font-size-sm);
    color: var(--color-error);
    margin-top: var(--spacing-1);
}

.form-help {
    display: block;
    font-size: var(--font-size-sm);
    color: var(--color-gray-600);
    margin-top: var(--spacing-1);
}

.form-checkbox,
.form-radio {
    width: 1rem;
    height: 1rem;
    accent-color: var(--color-primary);
}

.form-file {
    display: block;
    width: 100%;
}

.file-upload-area {
    border: 2px dashed var(--color-gray-300);
    border-radius: var(--radius-lg);
    padding: var(--spacing-8);
    text-align: center;
    cursor: pointer;
    transition: border-color var(--transition-fast), background-color var(--transition-fast);
}

.file-upload-area:hover {
    border-color: var(--color-primary);
    background-color: var(--color-gray-50);
}

.file-upload-area.dragover {
    border-color: var(--color-primary);
    background-color: var(--color-primary-light);
}
```

---

## 7. Utilities (utilities.css)

```css
/* Spacing */
.m-0 { margin: 0; }
.m-auto { margin: auto; }
.mt-4 { margin-top: var(--spacing-4); }
.mb-4 { margin-bottom: var(--spacing-4); }
.ml-4 { margin-left: var(--spacing-4); }
.mr-4 { margin-right: var(--spacing-4); }

.p-4 { padding: var(--spacing-4); }
.pt-4 { padding-top: var(--spacing-4); }
.pb-4 { padding-bottom: var(--spacing-4); }
.pl-4 { padding-left: var(--spacing-4); }
.pr-4 { padding-right: var(--spacing-4); }

/* Text */
.text-center { text-align: center; }
.text-left { text-align: left; }
.text-right { text-align: right; }

.text-sm { font-size: var(--font-size-sm); }
.text-base { font-size: var(--font-size-base); }
.text-lg { font-size: var(--font-size-lg); }
.text-xl { font-size: var(--font-size-xl); }

.font-normal { font-weight: var(--font-weight-normal); }
.font-medium { font-weight: var(--font-weight-medium); }
.font-semibold { font-weight: var(--font-weight-semibold); }
.font-bold { font-weight: var(--font-weight-bold); }

/* Colors */
.text-primary { color: var(--color-primary); }
.text-gray { color: var(--color-gray-600); }
.text-error { color: var(--color-error); }
.text-success { color: var(--color-success); }

.bg-white { background-color: var(--color-white); }
.bg-gray-50 { background-color: var(--color-gray-50); }
.bg-gray-100 { background-color: var(--color-gray-100); }

/* Display */
.block { display: block; }
.inline-block { display: inline-block; }
.hidden { display: none; }

/* Width */
.w-full { width: 100%; }
.w-auto { width: auto; }

/* Rounded */
.rounded { border-radius: var(--radius-md); }
.rounded-lg { border-radius: var(--radius-lg); }
.rounded-full { border-radius: var(--radius-full); }

/* Shadow */
.shadow { box-shadow: var(--shadow-md); }
.shadow-lg { box-shadow: var(--shadow-lg); }

/* Cursor */
.cursor-pointer { cursor: pointer; }
.cursor-not-allowed { cursor: not-allowed; }

/* Overflow */
.overflow-hidden { overflow: hidden; }
.overflow-auto { overflow: auto; }
```

---

## 8. Responsive (responsive.css)

```css
/* Mobile First Approach */

/* Tablets (768px and up) */
@media (min-width: 768px) {
    .container {
        padding: 0 var(--spacing-6);
    }
    
    .grid-md-cols-2 {
        grid-template-columns: repeat(2, 1fr);
    }
    
    .grid-md-cols-3 {
        grid-template-columns: repeat(3, 1fr);
    }
    
    .sidebar {
        width: 300px;
    }
}

/* Desktop (1024px and up) */
@media (min-width: 1024px) {
    .grid-lg-cols-3 {
        grid-template-columns: repeat(3, 1fr);
    }
    
    .grid-lg-cols-4 {
        grid-template-columns: repeat(4, 1fr);
    }
}

/* Large Desktop (1280px and up) */
@media (min-width: 1280px) {
    .container {
        padding: 0 var(--spacing-8);
    }
}

/* Mobile specific */
@media (max-width: 767px) {
    .hide-mobile {
        display: none;
    }
    
    .modal {
        width: 95%;
    }
    
    .sidebar {
        position: fixed;
        left: -250px;
        height: 100vh;
        transition: left var(--transition-base);
        z-index: var(--z-fixed);
    }
    
    .sidebar.active {
        left: 0;
    }
}
```

---

## Gebruik in Laravel

### resources/css/app.css
```css
/* Import alle CSS modules */
@import 'variables.css';
@import 'reset.css';
@import 'base.css';
@import 'layout.css';
@import 'components.css';
@import 'forms.css';
@import 'utilities.css';
@import 'responsive.css';
```

### In Blade Template
```html
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>@yield('title') - Documentenkluis</title>
    
    <!-- CSS -->
    @vite(['resources/css/app.css', 'resources/js/app.js'])
</head>
<body>
    <div class="app-layout">
        <header class="app-header">
            <div class="container">
                <!-- Header content -->
            </div>
        </header>
        
        <main class="app-main">
            <div class="container">
                @yield('content')
            </div>
        </main>
        
        <footer class="app-footer">
            <div class="container">
                <!-- Footer content -->
            </div>
        </footer>
    </div>
</body>
</html>
```

---

## Voordelen van deze aanpak

✅ **Volledige controle** over elke CSS regel
✅ **Klein bestand** - alleen wat je gebruikt
✅ **Overzichtelijk** - logische structuur
✅ **Herbruikbaar** - CSS Custom Properties
✅ **Onderhoudbaar** - gescheiden in modules
✅ **Responsive** - mobile-first design
✅ **Modern** - gebruik van CSS Custom Properties
✅ **Geen dependencies** - pure CSS

---

**Let op:** Dit is een basis structuur. Je kunt deze uitbreiden met meer componenten en utilities waar nodig.
