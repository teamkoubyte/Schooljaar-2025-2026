const {app, BrowserWindow, ipcMain, dialog, Menu, Notification, globalShortcut} = require('electron')
const path = require('path')
const fs = require('fs')

let win

function createWindow() {
  win = new BrowserWindow({
    width: 1000,
    height: 800,
    webPreferences: {
      nodeIntegration: true,
      contextIsolation: false,
      webviewTag: true
    }
  })

  win.loadFile('index.html')
  
  win.webContents.on('did-attach-webview', (event, webContents) => {
    webContents.setWindowOpenHandler(() => {
      return { action: 'allow' }
    })
  })
  
  registerShortcuts()
  
  const menu = Menu.buildFromTemplate([
    {
      label: 'File',
      submenu: [
        { label: 'Open', click: () => openFileDialog() },
        { label: 'Save', click: () => saveFileDialog() },
        { type: 'separator' },
        { label: 'Exit', click: () => app.quit() }
      ]
    },
    {
      label: 'View',
      submenu: [
        { label: 'Reload', click: () => win.reload() }
      ]
    }
  ])

  Menu.setApplicationMenu(menu)
}

function showNotification(title, body) {
  new Notification({ title, body }).show()
}

function openFileDialog() {
  dialog.showOpenDialog(win, {
    properties: ['openFile'],
    filters: [{ name: 'Text Files', extensions: ['txt'] }]
  }).then(result => {
    if (!result.canceled) {
      fs.readFile(result.filePaths[0], 'utf-8', (err, data) => {
        if (!err) {
          win.webContents.send('fileData', data)
        }
      })
    }
  })
}

function saveFileDialog() {
  dialog.showSaveDialog(win, {
    filters: [{ name: 'Text Files', extensions: ['txt'] }]
  }).then(result => {
    if (!result.canceled) {
      fs.writeFile(result.filePath, 'Saved content', (err) => {
        if (!err) showNotification('Saved', 'File saved')
      })
    }
  })
}

ipcMain.on('openFile', () => openFileDialog())
ipcMain.on('saveFile', () => saveFileDialog())
ipcMain.on('showNotification', (event, title, body) => showNotification(title, body))
ipcMain.on('showMessage', () => {
  dialog.showMessageBox(win, {
    type: 'info',
    message: 'This is a message',
    buttons: ['OK']
  })
})

function registerShortcuts() {
  globalShortcut.register('CommandOrControl+O', () => {
    openFileDialog()
  })
  
  globalShortcut.register('CommandOrControl+S', () => {
    saveFileDialog()
  })
  
  globalShortcut.register('CommandOrControl+N', () => {
    showNotification('Shortcut', 'Notification from Ctrl+N')
  })
}

app.whenReady().then(createWindow)

app.on('will-quit', () => {
  globalShortcut.unregisterAll()
})

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') {
    app.quit()
  }
})

app.on('activate', () => {
  if (BrowserWindow.getAllWindows().length === 0) {
    createWindow()
  }
})
