import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
//import router from './router/indexjs'
//import 'bootstrap'; // Import Bootstrap's JavaScript
//import 'bootstrap/dist/css/bootstrap.min.css'; // Import Bootstrap's CSS
const app = createApp(App)

//app.use(router)

app.mount('#app')
const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;

// Example of code to remove (or comment out):
const mediaQuery = window.matchMedia('(prefers-color-scheme: dark)');
mediaQuery.addEventListener('change', (e) => {
  // This is the function that auto-changes the theme
  const newTheme = 'light';
  // ... logic to set theme ...
});