# RecallTracker for Android
Check if there have been any recalls sent out on your vehicle and keep up to date on your vehicle's safety. All you need to do is provide the VIN of your vehicle and this application will take care of the rest. 

![RecallTracker Screenshots](https://i.imgur.com/qZ9cpxW.png)

### Meta
- **State:** Development
- **Point People:** [@jawhnypoh](https://github.com/jawhnypoh), [@SuperCheese21](https://github.com/SuperCheese21), [@xbhouse](https://github.com/xbhouse), [@downerc97](https://github.com/downerc97), [@kaywall19](https://github.com/kaywall19)

## Background
This project was a mobile platform solution using Fortellis' [Open Safety Recall API](https://apidocs.fortellis.io/docs/api/service/safety-recall-api) endpoint for CDK Global's 2019 Hackathon. This application also utilizes the [NHTSA's VIN Decoder API](https://vpic.nhtsa.dot.gov/api/) to gather information on a vehicle based on it's VIN. 

The application also implements a Push Notification service using the [Firebase Cloud Messaging Service](https://firebase.google.com/docs/cloud-messaging/?gclid=CjwKCAjwnf7qBRAtEiwAseBO_CIDgtlq1YgaL8uPxy2DHk66vTekWoPxRuA4lYiXIVS_aN5_B5rVBBoCDjQQAvD_BwE). 
The repository for the backend server can be found in the [safety-recall-server repository](https://github.com/SuperCheese21/safety-recall-server). 

## Installation
Please make sure that you have at least Android Studio (version 3.5) and proper Android SDK versions for your device installed and running correctly on your machine. For more information, please take a look at the [Android Studio documentations](https://developer.android.com/studio/install.html)

Now just clone the project into your local machine and run it! 

**Note:** In order for the Push Notifications to work, you will need to clone from the [safety-recall-server repository](https://github.com/SuperCheese21/safety-recall-server) and set up your own Firebase Cloud Messaging service. 
