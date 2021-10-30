import 'package:flutter/material.dart';
import 'package:payhere/payhere.dart';

void main() {
  runApp(PayhereExample());
}

class PayhereExample extends StatefulWidget {
  @override
  _PayhereExampleState createState() => _PayhereExampleState();
}

class _PayhereExampleState extends State<PayhereExample> {

  //The default Response
  String response = "Click the Above Button";

  Payhere payhere = new Payhere();
  OneTimePayment oneTimePayment = new OneTimePayment(
      merchantID: "1213586",
      merchantSecret: "48WrQKBwKts4PbEy5gCd878QfqpdU2Zzl4PbG9xxs3d8",
      notifyUrl: "http://example.com/",
      currency: "LKR",
      totalamount: 100,
      orderID: "SomeOrderID",
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
      //these are optional values as stated in the PayHere android sdk.
      deliveryAddress: "deliveryAddress",
      deliveryCity: "deliveryAddress",
      deliveryCountry: "deliveryAddress");
  RecurringPayment recurringPayment = new RecurringPayment(
      "Merchant ID",
      "Merchant Secret",
      "http://example.com/",
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
              ElevatedButton(
                onPressed: () async {
                  String reps = await payhere.makeOneTimePayment(oneTimePayment);
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
