// Give the service worker access to Firebase Messaging.
// Note that you can only use Firebase Messaging here. Other Firebase libraries
// are not available in the service worker.
importScripts('https://www.gstatic.com/firebasejs/8.1.2/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/8.1.2/firebase-messaging.js');
importScripts('https://www.gstatic.com/firebasejs/8.1.2/firebase-analytics.js');



let firebaseConfig = {
    apiKey: "AIzaSyDAQr6dk0fo9y7XxuFSjrXV4tijHoCggyk",
    authDomain: "fir-poc-83d41.firebaseapp.com",
    projectId: "fir-poc-83d41",
    storageBucket: "fir-poc-83d41.appspot.com",
    messagingSenderId: "486358425051",
    appId: "1:486358425051:web:b1366ffd272ea3b049dbfe",
    measurementId: "G-GLP10C4BLJ"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);

const messaging = firebase.messaging();

messaging.onBackgroundMessage(function(payload) {
    console.log('[firebase-messaging-sw.js] Received background message ', payload);
    const notificationTitle = 'Background Message Title';
    const notificationOptions = {
        body: '<!DOCTYPE html><html><head><style>body {  background-color: lightblue;}h1 {  color: white;  text-align: center;}p {  font-family: verdana;  font-size: 20px;}</style></head><body><h1>My First CSS Example</h1><p>This is a paragraph.</p></body></html>',
        icon: 'https://cdn3.iconfinder.com/data/icons/google-material-design-icons/48/ic_location_on_48px-512.png'
    };

    self.registration.showNotification(notificationTitle, notificationOptions);
});
