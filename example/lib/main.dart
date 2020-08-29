import 'package:flutter/material.dart';
import 'package:payhere/payhere.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String response = "NOT YET";

  Payhere payhere = new Payhere();

  OneTimePayment otp = new OneTimePayment(
      merchantID: "Merchant ID",
      merchantSecret: "Merchant Secret",
      currency: "LKR",
      totalamount: 100,
      orderID: "XTX",
      itemDesc: "decribe thy item",
      cM1: "CM1",
      cM2: "CM2",
      fName: "FName",
      lName: "LName",
      email: "Email",
      phone: "Phone",
      address: "Address",
      city: "Colombo",
      country: "Sri Lanka",
      //these are optional values as stated in the payhere android sdk.
      deliveryAddress: "deliveryAddress",
      deliveryCity: "deliveryAddress",
      deliveryCountry: "deliveryAddress");

  RecurringPayment recurringPayment = new RecurringPayment(
      "Merchant ID",
      "Merchant Secret",
      "LKR",
      120,
      "o6ae978a1",
      "testsub",
      "cM1",
      "cM2",
      "fName",
      "lName",
      "email",
      "+94771234567",
      "address",
      "Colombo",
      "Sri Lanka",
      "1 Month",
      "Forever",
      2);

  

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Payhere Example App'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              RaisedButton(
                onPressed: () async {
                  
                  String reps = await payhere.makeOneTimePayment(otp);
                  //String reps = await payhere.makeRecurringPayment(recurringPayment);
                  setState(() {
                    response = reps;
                  });
                },
                child: Text("One Time Payment SANDBOX"),
              ),
              Text(response)
            ],
          ),
        ),
      ),
    );
  }
}
