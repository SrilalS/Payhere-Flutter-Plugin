# Payhere Flutter Plugin

A Plugin for Payhere SDK for Flutter.


![Build Version](https://img.shields.io/badge/V-0.7.25-brightgreen)
![Build](https://img.shields.io/badge/Status-Stable-brightgreen)
![Build SDK](https://img.shields.io/badge/FlutterChannel-Stable-blue)
![Payheresdk](https://img.shields.io/badge/PayhereSDK-2.0.24-brightgreen)


<p>
    <img src="https://raw.githubusercontent.com/SrilalS/Payhere-Flutter-Plugin/master/Docs/img/1.png?raw=true" width="190px" height="auto"/>
    <img src="https://raw.githubusercontent.com/SrilalS/Payhere-Flutter-Plugin/master/Docs/img/2.png?raw=true" width="190px" height="auto"/>
    <img src="https://raw.githubusercontent.com/SrilalS/Payhere-Flutter-Plugin/master/Docs/img/3.png?raw=true" width="190px" height="auto"/>
</p>


## Usage
[Get it From Pub.dev](https://pub.dev/packages/payhere)
### Installing
Under dependencies in your pubspec.yaml file:

    payhere: ^0.7.25

# Documentation + How to Use
(This is a subset of Payhere Android SDK Documentation. for more info please read [Payhere Android SDK Documentation](https://support.payhere.lk/api-&-mobile-sdk/payhere-android))

### Payhere Android SDK Dependencies and AndroidManifest.xml

Payhere Android SDK Dependencies are automatically handled by this plugin. 😜.

But, in your `AndroidManifest.xml`, under `application`, add this `tools:replace="android:label"`.  also, in the top section add `xmlns:tools="http://schemas.android.com/tools"`

Example: 

`<manifest xmlns:android="http://schemas.android.com/apk/res/android" `  
` xmlns:tools="http://schemas.android.com/tools"  `  
` package="YOUR PACKAGE NAME WILL AUTOMATICALLY BE HERE."> `  
` <application  android:name="io.flutter.app.FlutterApplication" `  
`  android:label="YOUR ANDROID LAUNCHER LABEL" `  
`  android:icon="@mipmap/ic_launcher" `  
`  tools:replace="android:label">`  

### First initialize a new **Payhere** object,
`Payhere payhere = new  Payhere(); `

`payhere` object has 2 methods. 

 1. `makeOneTimePayment()`
 2. `makeRecurringPayment()` //This is still under testing. may or may not work.

for each object you have to pass the relevent payment object. 

### To make a OneTime Payment

first make a new **OneTimePayment** object. this uses Named Parameters to avoid confusion.

`OneTimePayment otp = new  OneTimePayment(`
`merchantID: "Your Merchant ID", `
`merchantSecret: "Your Merchant Secret", `
`currency: "LKR", ` //Currency code LKR/USD/GBP/EUR/AUD
`totalamount: 100, ` // Final Amount to be charged.
`orderID: "UniqueOrderID", ` // Unique Reference ID
`itemDesc: "decribe thy item", ` // Item description title
`cM1: "CM1", `
`cM2: "CM2", `
`fName: "FName", `
`lName: "LName",`
`email: "Email", `
`phone: "Phone", `
`address: "Address", `
`city: "Colombo", `
`country: "Sri Lanka", `
//following are optional values as stated in the Payhere android SDK. you can avoid setting them if you wanted to.
`deliveryAddress: "deliveryAddress", `
`deliveryCity: "deliveryAddress", `
`deliveryCountry: "deliveryAddress"); `

this OneTimePayment object has 2 Methods.
 1. `setSandBoxMode() `
 2. `setProductionMode() `

 both doesn's take arguments. by default, OneTimePayment is set to SandBox Mode so no need to specifically set it to sandbox. 

### Executing the Payment 
the `payhere` object returns the Result of the Payment as a Properly Formatted JSON String. so, you will need to execute it inside a Async function. 

for example:
`void makepayment() async { `
`String result = await payhere.makeOneTimePayment(otp); `
`}); `
`}, `

the resulting JSON String will have 3 modes.

 1. IF the Payment is A Success
 {"STATUS"="SUCCESS", "PAYMENT_NO"="payment_number_will_be_here", "CODE"=2, "SIGN"="payment_sign_will_be_here", "MESSAGE"="Successfully completed the payment."}
 
 2. IF the Payment encountered an Error
  {"STATUS"="ERROR", "CODE"=-1}
  
 3. IF the Payment is Canceled.
 {"STATUS"="CANCELED", "CODE"=0} 
 **Due to the Nature of the Payhere Android SDK, the Cancel and Error Status are interchagable. meaning, one can mean the other.**

## Payment Status/Result

| Code| Reason|
|--|--|
| 2|  Success|
| -1|  Error|
| 0|  Canceled|

**Error (-1) and Canceled (0) are Same in Behavior.**

## Dev STATUS
| Platform| OneTime Payment| Recurring Payment | Pre-approval Payment
|--|--|--|--|
| Android |  ✔️| 💻 | ❌ | 
| iOS |  ❌| ❌ | ❌ |
| Web |  ❌| ❌ | ❌ |
